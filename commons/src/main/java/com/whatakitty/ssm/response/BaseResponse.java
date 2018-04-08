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

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author xuqiang
 * @date 2017/1/13
 */
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse implements Serializable {

    private boolean success;
    private Object content;

    /**
     * 成功
     *
     * @return 响应实体
     */
    public static BaseResponse success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param obj 成功附带的对象参数
     * @return 响应实体
     */
    public static BaseResponse success(Object obj) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.content = obj;
        baseResponse.success = true;
        return baseResponse;
    }

    /**
     * 失败
     *
     * @param obj 失败信息或者对象
     * @return 响应实体
     */
    public static BaseResponse error(Object obj) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.content = obj;
        baseResponse.success = false;
        return baseResponse;
    }

    /**
     * 是否成功
     *
     * @return 是否成功
     */
    public boolean isSucc() {
        return success;
    }

    /**
     * 获取附带的参数对象内容
     *
     * @param <T> 参数类型
     * @return 参数实例
     */
    public <T> T getContent() {
        return (T) content;
    }

}
