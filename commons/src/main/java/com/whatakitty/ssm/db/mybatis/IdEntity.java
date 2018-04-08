package com.whatakitty.ssm.db.mybatis;

import java.io.Serializable;
import javax.persistence.Id;
import lombok.Data;

/**
 * 基础entity
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Data
public abstract class IdEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    public BaseEntity clone(Object source) {
        throw new RuntimeException("Please implements this method first.");
    }
}
