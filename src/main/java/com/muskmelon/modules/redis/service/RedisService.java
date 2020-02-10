package com.muskmelon.modules.redis.service;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-11 0:01
 * @since 1.0
 */
public interface RedisService {

    /**
     * 连接redis服务
     *
     * @param ip
     * @param port
     * @param password
     */
    void connect(String ip, int port, String password);

    /**
     * 关闭redis服务
     */
    void close();

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    void set(String key, String value, int expire);

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 删除缓存
     * @param key
     */
    void delete(String key);

}
