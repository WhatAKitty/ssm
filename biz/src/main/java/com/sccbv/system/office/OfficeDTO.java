package com.sccbv.system.office;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 部门数据传输载体
 *
 * @date 2018/04/29
 * @description
 **/
@Data
public class OfficeDTO {

    private Long id;

    // TODO 需要删除不需要的字段并且添加对应的验证条件
    @NotEmpty(
        groups = {OfficeCreateGroup.class, OfficeUpdateGroup.class},
        message = "{error.message.office.name.empty}"
    )
    private String name;

    @NotNull(
        groups = {OfficeCreateGroup.class, OfficeUpdateGroup.class},
        message = "{error.message.office.parentId.null}"
    )
    private Long parentId;

    private String remark;


    /**
     * 部门创建验证组
     */
    public interface OfficeCreateGroup {}

    /**
     * 部门更新验证组
     */
    public interface OfficeUpdateGroup {}

}
