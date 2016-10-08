package com.atman.jishang.utils;

import com.corelib.util.StringUtils;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.regex.Pattern;

/**
 * @author 尹东东
 * @version 0.0.1
 * @date 2016/3/8 10:29
 */
public class GsonUtils {

    private final static Pattern jsonObjer = Pattern
            .compile("^(\\{)");

    private final static Pattern jsonArrayer = Pattern
            .compile("^(\\[)");

    private final static Pattern jsoner = Pattern
            .compile("^(\\{)|(\\[)");

    public static boolean isJsonObj(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        return jsonObjer.matcher(StringUtils.trim(str)).matches();
    }

    public static boolean isJsonArray(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        return jsonArrayer.matcher(StringUtils.trim(str)).matches();
    }

    public static boolean isJson(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        return jsoner.matcher(StringUtils.trim(str)).matches();
    }

    public static <T> T parse(String json,Class<T> clazz) {
        Gson mGson = new Gson();
        return mGson.fromJson(json, clazz);
    }

    public static <T> T parse(String json,Type typeOfT) {
        Gson mGson = new Gson();
        return mGson.fromJson(json, typeOfT);
    }

    public static String toJSON(Object src) {
        Gson mGson = new Gson();
        return mGson.toJson(src);
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }
}
