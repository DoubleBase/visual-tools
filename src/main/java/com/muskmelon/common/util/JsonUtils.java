package com.muskmelon.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

/**
 * @author muskmelon
 * @description json转化工具
 * @date 2020-2-9 20:46
 * @since 1.0
 */
public class JsonUtils {

    public static String object2Json(Object object) {
        if (null == object) {
            return Strings.EMPTY;
        }
        return JSONObject.toJSONString(object);
    }

    public static <T> T json2Object(String json, Class<T> cls) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSONObject.parseObject(json, cls);
    }

}
