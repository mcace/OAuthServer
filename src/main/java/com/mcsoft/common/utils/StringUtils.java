package com.mcsoft.common.utils;

import java.util.UUID;

/**
 * 字符串工具
 *
 * @author : Mc
 * @date : 2017/4/11 15:02
 */
public class StringUtils {
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean hasEmpty(Object... strArray) {
        for (Object str : strArray) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    //生成不带'-'的32位全小写UUID，当参数固定时，生成的UUID也是固定的，无参时则是随机生成
    public static String generateUUIDWithoutCross(String... params) {
        return generateUUID(params).replace("-", "");
    }

    //生成带'-'的36位全小写UUID，当参数固定时，生成的UUID也是固定的，无参时则是随机生成
    public static String generateUUID(String... params) {
        StringBuilder fromStringBuilder = new StringBuilder();
        if (0 == params.length) {
            return UUID.randomUUID().toString();
        }

        fromStringBuilder.append(params[0]);
        for (int i = 1; i < params.length; i++) {
            String param = params[i];
            fromStringBuilder.append("-");
            fromStringBuilder.append(param);
        }

        return UUID.nameUUIDFromBytes(fromStringBuilder.toString().getBytes()).toString().toLowerCase();
    }
}
