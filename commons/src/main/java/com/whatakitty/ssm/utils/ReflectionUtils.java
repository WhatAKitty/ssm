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

import lombok.extern.slf4j.Slf4j;

/**
 * 反射工具类
 *
 * @author yuhailun
 * @date 2018/02/06
 * @description
 **/
@Slf4j
public class ReflectionUtils {

    /**
     * 创建实例
     *
     * @param sourceClazz 原类型
     * @param targetClazz 目标类型
     * @param <T>         泛型
     * @return 目标队形
     */
    public static <T> T createInstance(Class sourceClazz, Class<T> targetClazz) {
        try {
            Object instance = sourceClazz.newInstance();
            if (targetClazz.isInstance(instance)) {
                return (T) instance;
            }
            return null;
        } catch (InstantiationException | IllegalAccessException e) {
            if (log.isErrorEnabled()) {
                log.error("Class newInstance failed", e);
            }
            return null;
        }
    }

}
