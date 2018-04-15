package com.sccbv.system.conf;

import com.whatakitty.ssm.db.mybatis.IdEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置实体类
 *
 * @date 2018/04/15
 * @description
 **/
@Data
@Table(name = "sys_conf")
@EqualsAndHashCode(callSuper = true)
public class Conf extends IdEntity {

    /**
     * 主题
     */
    private String theme;

    /**
     * 是否启用站内信
     */
    private Boolean insiteMessager;

    /**
     * 是否启用站内邮件
     */
    private Boolean insiteEmail;

    /**
     * 是否启用通知
     */
    private Boolean alert;


}
