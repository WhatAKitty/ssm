/*
 * *****************************************************************************
 *  杭州高网，机密
 *  __________________
 *
 *  [2016] - [2017] 杭州高网信息技术有限公司
 *  版权所有。
 *
 *  注意：此处包含的所有信息均为杭州高网信息技术有限公司的财产。知识和技术理念
 *  包含在内为杭州高网信息技术有限公司所有，可能受中国和国际专利，以及商业秘密
 *  或版权法保护。严格禁止传播此信息或复制此材料，除非事先获得来自杭州高网信
 *  息技术有限公司的书面许可。
 *
 */

package com.whatakitty.ssm.enums;

/**
 * 性别枚举
 *
 * @author yuhailun
 * @date 2018/03/06
 * @description
 **/
public enum Sex implements ValueEnum {

    /**
     * 未知性别
     */
    UNKNOW(0),

    /**
     * 男性
     */
    MALE(1),

    /**
     * 女性
     */
    FEMALE(2);

    /**
     * 对应值
     */
    private int value;

    Sex(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
