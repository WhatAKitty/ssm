package com.sccbv.demo;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图书实体
 *
 * @author whatakitty
 * @date 2018/04/05
 */
@Data
@NoArgsConstructor
public class Book extends SDelEntity {

    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书数量
     */
    private int number;

}
