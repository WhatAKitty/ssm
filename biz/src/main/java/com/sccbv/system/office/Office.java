package com.sccbv.system.office;

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import java.util.Date;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门实体类
 *
 * @date 2018/04/29
 * @description
 **/
@Data
@Table(name = "sys_office")
@EqualsAndHashCode(callSuper = true)
public class Office extends SDelEntity {

    // TODO 需要删除不需要的字段
    /**
     * 公司/部门名
     */
    private String name;

    /**
     * 上级公司/部门编号
     */
    private Long parentId;

    /**
     * 备注
     */
    private String remark;


}
