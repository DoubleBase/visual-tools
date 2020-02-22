package com.muskmelon.modules.redis.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-9 23:31
 * @since 1.0
 */
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static Jedis jedis;

    /**
     * 连接redis服务
     *
     * @param ip       服务ip
     * @param port     服务端口
     * @param password 密码
     */
    public static boolean connect(String ip, int port, String password) {
        logger.info("******连接redis服务******");
        logger.info("连接信息：ip={},port={}", ip, port);
        jedis = new Jedis(ip, port);
        if (StringUtils.isNotBlank(password)) {
            jedis.auth(password);
        }
        if (Protocol.Keyword.PONG.name().equals(jedis.ping())) {
            logger.info("服务连接成功,连接信息：ip={},port={}", ip, port);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 关闭redis连接
     */
    public static void close() {
        logger.info("******关闭redis连接******");
        try {
            jedis.close();
        } catch (Exception e) {
            logger.error("redis连接关闭异常", e);
        }
    }

    /**
     * 校验是否已连接redis服务
     */
    public static void validate() {
        if (jedis == null) {
            throw new RuntimeException("未连接redis服务");
        }
    }

    /**
     * 设置redis缓存
     *
     * @param key    缓存key
     * @param value  缓存值
     * @param expire 超时时间,单位秒
     * @return
     */
    public static boolean set(String key, String value, int expire) {
        try {
            validate();
            jedis.set(key, value);
            if (0 != expire) {
                jedis.expire(key, expire);
            }
            logger.info("设置缓存成功,key={},value={},超时时间(s)={}", key, value, expire);
            return true;
        } catch (Exception e) {
            logger.error("设置缓存失败,key={},value={},超时时间(s)={}", key, value, expire);
            throw e;
        }
    }

    /**
     * 设置redis缓存
     *
     * @param key   缓存key
     * @param value 缓存值
     * @return
     */
    public static boolean set(String key, String value) {
        try {
            validate();
            jedis.set(key, value);
            logger.info("设置缓存成功,key={},value={},超时时间(s)={}", key, value);
            return true;
        } catch (Exception e) {
            logger.error("设置缓存失败,key={},value={},超时时间(s)={}", key, value);
            throw e;
        }
    }

    /**
     * 查询缓存值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        try {
            validate();
            String value = jedis.get(key);
            logger.info("缓存查询成功,key={},value={}", key, value);
            return value;
        } catch (Exception e) {
            logger.error("缓存查询失败,key={}", key);
            throw e;
        }
    }

    /**
     * 删除redis缓存
     *
     * @param key 缓存key
     */
    public static boolean deleteNode(String key) {
        try {
            jedis.del(key);
            logger.info("删除缓存成功,key={}", key);
            return true;
        } catch (Exception e) {
            logger.info("删除缓存失败,key={}", key);
            throw e;
        }
    }

    public static Jedis getJedis() {
        return jedis;
    }
}
