package com.muskmelon.common.aspect;

import com.muskmelon.common.annotation.LoggerOperator;
import com.muskmelon.common.util.JsonUtils;
import com.muskmelon.modules.system.domain.OperatorLog;
import com.muskmelon.modules.system.service.OperatorService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-9 16:06
 * @since 1.0
 */
@Aspect
@Component
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    private ThreadLocal<OperatorLog> logThreadLocal = new NamedThreadLocal<>("log threadLocal");

    @Autowired
    private HttpServletRequest request;

    @Resource
    private OperatorService operatorService;

    @Pointcut("@annotation(com.muskmelon.common.annotation.LoggerOperator)")
    public void controllerAspect() {

    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        OperatorLog log = new OperatorLog();
        log.setCreateTime(Timestamp.from(Instant.now()));
        logThreadLocal.set(log);
    }

    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        String type = "info";
        String remoteAddr = request.getRemoteAddr();
        String requestUri = request.getRequestURI();

        String method = request.getMethod();
        Map<String, String[]> paramMap = request.getParameterMap();

        String title = getControllerMethodDesc(joinPoint);

        OperatorLog log = logThreadLocal.get();
        if (null != log) {
            log.setType(type);
            log.setTitle(title);
            log.setContent("");
            log.setMethod(method);
            log.setRemoteAddr(remoteAddr);
            log.setRequestUri(requestUri);
            log.setParamFromMap(paramMap);
            log.setCostTime(Instant.now().toEpochMilli() - log.getCreateTime().getTime());
            log.setUserName("admin");
            operatorService.addOperatorLog(log);
        }

    }

    /**
     * 返回值切面
     */
    @AfterReturning(pointcut = "controllerAspect()", returning = "res")
    public void doAfterReturning(Object res) {
        OperatorLog log = logThreadLocal.get();
        try {
            if (null != log) {
                log.setResult(JsonUtils.object2Json(res));
                operatorService.updateOperatorLog(log);
            }
        } catch (Exception e) {
            logger.error("执行doAfterReturning发生异常", e);
        } finally {
            logThreadLocal.remove();
        }
    }

    /**
     * 抛出异常切面
     *
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(Throwable e) {
        OperatorLog log = logThreadLocal.get();
        try {
            if (null != log) {
                log.setType("error");
                log.setException(e.toString());
                operatorService.updateOperatorLog(log);
            }
        } catch (Exception e1) {
            logger.error("执行doAfterThrowing发生异常", e1);
        } finally {
            logThreadLocal.remove();
        }
    }

    private String getControllerMethodDesc(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LoggerOperator loggerOperator = method.getAnnotation(LoggerOperator.class);
        return loggerOperator.description();
    }

}
