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

package com.whatakitty.ssm.asserts;

import com.whatakitty.ssm.exception.BusinessException;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * 自定义断言
 *
 * @author yuhailun
 * @date 2018/01/25
 * @description
 **/
public abstract class Asserts {

    /**
     * 断言一个布尔表达式为true
     *
     * @param expression 布尔表达式
     * @param code       http状态码
     * @param message    异常信息
     */
    public static void isTrue(boolean expression, Integer code, String message) {
        if (!expression) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个布尔表达式为false
     *
     * @param expression 布尔表达式
     * @param code       http状态码
     * @param message    异常信息
     */
    public static void isFalse(boolean expression, Integer code, String message) {
        if (expression) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个对象为null
     *
     * @param object  对象
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void isNull(Object object, Integer code, String message) {
        if (object != null) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个对象不为null
     *
     * @param object  对象
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void notNull(Object object, Integer code, String message) {
        if (object == null) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个字符串为null 或 为空 或空字符串
     *
     * @param text    字符串
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void isBlank(String text, Integer code, String message) {
        if (StringUtils.isNotBlank(text)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个字符串不为null 或 为空 或空字符串
     *
     * @param text    字符串
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void notBlank(String text, Integer code, String message) {
        if (StringUtils.isBlank(text)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个字符串为null 或 为空
     *
     * @param text    字符串
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void isEmpty(String text, Integer code, String message) {
        if (StringUtils.isNotEmpty(text)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个字符串不为null 或 为空
     *
     * @param text    字符串
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void notEmpty(String text, Integer code, String message) {
        if (StringUtils.isEmpty(text)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个数组不为null 或 空数组
     *
     * @param array   数组
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void notEmpty(Object[] array, Integer code, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个集合不为null 或 空集合
     *
     * @param collection 集合
     * @param code       http状态码
     * @param message    异常信息
     */
    public static void notEmpty(Collection<?> collection, Integer code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个哈希不为null 或 空哈希
     *
     * @param map     哈希
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void notEmpty(Map<?, ?> map, Integer code, String message) {
        if (MapUtils.isEmpty(map)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言一个数组不含有null对象
     *
     * @param array   数组
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void noNullElements(Object[] array, Integer code, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new BusinessException(code, message);
                }
            }
        }
    }

    /**
     * 断言一个对象是这个类的实例
     *
     * @param type    父类
     * @param obj     对象
     * @param code    http状态码
     * @param message 异常信息
     */
    public static void isInstanceOf(Class<?> type, Object obj, Integer code, String message) {
        if (type == null || obj == null || !type.isInstance(obj)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 断言这个超类是这个子类的父类
     *
     * @param superType 超类
     * @param subType   子类
     * @param code      http状态码
     * @param message   异常信息
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, Integer code, String message) {
        if (superType == null || subType == null || !superType.isAssignableFrom(subType)) {
            throw new BusinessException(code, message);
        }
    }

}
