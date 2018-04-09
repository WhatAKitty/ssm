package com.sccbv.demo;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import java.util.Date;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图书实体类
 *
 * @date 2018/04/09
 * @description
 **/
@Data
@Table(name = "book")
@EqualsAndHashCode(callSuper = true)
public class Book extends SDelEntity {

    /**
     * 图书名称
     */
    private String name;

    /**
     * 馆藏数量
     */
    private Integer number;


}
