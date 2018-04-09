package com.whatakitty.ssm.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @description Json工具类.
 * @author yuhailun
 * @date 2018/1/12
 **/
public class JsonUtils {

    /**
     * 判断是否是json结构字符串
     *
     * @param str 需要判断的字符串
     * @return 是否为json字符串
     */
    public static boolean isJsonStr(String str) {
        try {
            JSONObject.parseObject(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
