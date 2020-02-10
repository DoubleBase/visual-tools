package com.muskmelon.modules.redis.service.impl;

import com.muskmelon.modules.redis.service.RedisService;
import com.muskmelon.modules.redis.util.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-11 0:02
 * @since 1.0
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Override
    public void connect(String ip, int port, String password) {
        RedisUtil.connect(ip, port, password);
    }

    @Override
    public void close() {
        RedisUtil.close();
    }

    @Override
    public void set(String key, String value, int expire) {
        if (0 == expire) {
            RedisUtil.set(key, value);
        } else {
            RedisUtil.set(key, value, expire);
        }

    }

    @Override
    public String get(String key) {
        return RedisUtil.get(key);
    }

    @Override
    public void delete(String key) {
        RedisUtil.deleteNode(key);
    }
}
