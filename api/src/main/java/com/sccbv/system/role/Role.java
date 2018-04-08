package com.sccbv.system.role;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据载体
 *
 * @author xuqiang
 * @date 2018/01/09
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_role")
public class Role extends SDelEntity {

    /**
     * 编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色备注
     */
    private String remark;

}
