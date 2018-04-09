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
