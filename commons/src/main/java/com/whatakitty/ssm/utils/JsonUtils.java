/*
 * *****************************************************************************
 *  杭州高网，机密
 *  __________________
 *
 *  [2016] - [2020] 杭州高网信息技术有限公司
 *  版权所有。
 *
 *  注意：此处包含的所有信息均为杭州高网信息技术有限公司的财产。知识和技术理念
 *  包含在内为杭州高网信息技术有限公司所有，可能受中国和国际专利，以及商业秘密
 *  或版权法保护。严格禁止传播此信息或复制此材料，除非事先获得来自杭州高网信
 *  息技术有限公司的书面许可。
 *
 */

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
