package com.whatakitty.ssm.db.mybatis;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础Entity类<br/>
 * <p><b>提供UUID及其GETTER和SETTER方法</b><br/></p>
 * <font color="red"><b>注意： 如果主键类型非UUID策略，则BaseEntity不适用，请自行构建Entity</b></font>
 *
 * @author xuqiang
 * @version 1.0
 * @date 2016/9/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends IdEntity {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

}
