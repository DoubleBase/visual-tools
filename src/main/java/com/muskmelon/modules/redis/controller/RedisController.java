package com.muskmelon.modules.redis.controller;

import com.muskmelon.common.action.ActionResult;
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

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public ActionResult<String> connect(String ip, int port, String password) {
        ActionResult<String> result = new ActionResult<>();
        redisService.connect(ip, port, password);
        return result;
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public ActionResult<String> connect() {
        ActionResult<String> result = new ActionResult<>();
        redisService.close();
        return result;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ActionResult<String> getKeyValue(String key) {
        ActionResult<String> result = new ActionResult<>();
        result.setData(redisService.get(key));
        return result;
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ActionResult<String> setKeyValue(String key, String value, int expire) {
        ActionResult<String> result = new ActionResult<>();
        redisService.set(key, value, expire);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult<String> setKeyValue(String key) {
        ActionResult<String> result = new ActionResult<>();
        redisService.delete(key);
        return result;
    }


}
