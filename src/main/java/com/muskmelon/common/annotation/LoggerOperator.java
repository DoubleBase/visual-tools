package com.muskmelon.common.annotation;

import org.apache.logging.log4j.util.Strings;

import java.lang.annotation.*;

/**
 * @author muskmelon
 * @description 操作日志注解
 * @date 2020-2-9 16:03
 * @since 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LoggerOperator {

    /**
     * 操作业务描述
     * @return
     */
    String description() default Strings.EMPTY;

}
