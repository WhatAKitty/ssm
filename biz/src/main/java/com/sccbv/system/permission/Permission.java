package com.sccbv.system.permission;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限实体类
 *
 * @author yuhailun
 * @date 2018/01/08
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_permission")
public class Permission extends SDelEntity {

    /**
     * 权限代码
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限分类
     */
    private String category;

    /**
     * 备注
     */
    private String remark;

}
