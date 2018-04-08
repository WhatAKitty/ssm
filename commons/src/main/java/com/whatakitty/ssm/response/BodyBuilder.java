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

package com.whatakitty.ssm.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.ObjectError;

/**
 * Body Builder
 *
 * @author xuqiang
 * @date 2018/1/13
 */
public class BodyBuilder {

    private Map<String, Object> content = new HashMap<>();

    private BodyBuilder() {
    }

    /**
     * 构建一个BodyBuilder构造器
     *
     * @return 构造器
     */
    public static BodyBuilder create() {
        return new BodyBuilder();
    }

    /**
     * 在内容里增加参数
     *
     * @param key   参数标签
     * @param value 参数值
     * @return 构造器
     */
    public BodyBuilder addParam(String key, Object value) {
        content.put(key, value);
        return this;
    }

    /**
     * 在内容里增加多个参数
     *
     * @param params 参数map对象
     * @return 构造器
     */
    public BodyBuilder setParams(Map<String, Object> params) {
        content.putAll(params);
        return this;
    }

    /**
     * 增加验证返回错误结果
     *
     * @param error 错误对象
     * @return 构造器
     */
    public BodyBuilder addObjectError(ObjectError error) {
        List<String> errorList = getErrors();
        errorList.add(error.getDefaultMessage());
        return this;
    }

    /**
     * 增加单个错误消息
     *
     * @param message 错误消息
     * @return 构造器
     */
    public BodyBuilder addError(String message) {
        List<String> errorList = getErrors();
        errorList.add(message);
        return this;
    }

    /**
     * 设置多个错误对象
     *
     * @param errors 错误对象列表
     * @return 构造器
     */
    public BodyBuilder setErrors(List<ObjectError> errors) {
        List<String> errorList = getErrors();
        errors.stream().forEach(error -> {
            errorList.add(error.getDefaultMessage());
        });
        return this;
    }

    /**
     * 构建内容对象
     *
     * @return 内容map
     */
    public Map<String, Object> build() {
        content.put("timestamp", System.currentTimeMillis());
        if (getErrors().size() < 2) {
            content.put("message", getErrors().size() > 0 ? getErrors().get(0) : "");
        }
        return content;
    }

    private List<String> getErrors() {
        List<String> errors = (List<String>) content.get("message");
        if (errors == null) {
            errors = new ArrayList<>();
            content.put("message", errors);
        }
        return errors;
    }

}
