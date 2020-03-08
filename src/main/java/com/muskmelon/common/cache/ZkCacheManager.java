package com.muskmelon.common.cache;

import com.muskmelon.modules.connect.domain.ConnectInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-8 21:31
 * @since 1.0
 */
public class ZkCacheManager {

    private static String CONNECTION_CACHE;

    public static void addCache(String connectInfo) {
        CONNECTION_CACHE = connectInfo;
    }

    public static void removeCache() {
        CONNECTION_CACHE = Strings.EMPTY;
    }

    public static boolean checkExist(String connectString) {
        return StringUtils.isNotBlank(CONNECTION_CACHE) && CONNECTION_CACHE.equals(connectString);
    }

    public static String getConnectionCache() {
        if (StringUtils.isBlank(CONNECTION_CACHE)) {
            return "无";
        }
        return CONNECTION_CACHE;
    }
}
