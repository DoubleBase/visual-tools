package com.muskmelon.modules.redis.controller;

import com.muskmelon.common.action.ActionResult;
import com.muskmelon.common.annotation.LoggerOperator;
import com.muskmelon.common.enums.ResultCode;
import com.muskmelon.modules.redis.service.RedisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-10 0:15
 * @since 1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    @LoggerOperator(description = "连接redis服务")
    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public ActionResult<Boolean> connect(String ip, int port, String password) {
        ActionResult<Boolean> result = new ActionResult<>();
        try {
            redisService.connect(ip, port, password);
        } catch (Exception e) {
            result.error(false, ResultCode.REDIS_CONNECT_ERROR);
        }
        return result;
    }

    @LoggerOperator(description = "关闭redis服务")
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public ActionResult<String> close() {
        ActionResult<String> result = new ActionResult<>();
        redisService.close();
        return result;
    }

    @LoggerOperator(description = "获取缓存")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ActionResult<String> getKeyValue(String key) {
        ActionResult<String> result = new ActionResult<>();
        result.setData(redisService.get(key));
        return result;
    }

    @LoggerOperator(description = "设置缓存")
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ActionResult<String> setKeyValue(String key, String value) {
        ActionResult<String> result = new ActionResult<>();
        redisService.set(key, value, 0);
        return result;
    }

    @LoggerOperator(description = "删除缓存")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult<String> setKeyValue(String key) {
        ActionResult<String> result = new ActionResult<>();
        redisService.delete(key);
        return result;
    }


}
