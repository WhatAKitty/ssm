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
