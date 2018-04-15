package com.sccbv.system.conf;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 系统配置数据传输载体
 *
 * @date 2018/04/15
 * @description
 **/
@Data
public class ConfDTO {

    private Long id;

    private String theme;

    @NotNull(
        groups = {ConfCreateGroup.class, ConfUpdateGroup.class},
        message = "{error.message.conf.insiteMessager.null}"
    )
    private Boolean insiteMessager;

    @NotNull(
        groups = {ConfCreateGroup.class, ConfUpdateGroup.class},
        message = "{error.message.conf.insiteEmail.null}"
    )
    private Boolean insiteEmail;

    @NotNull(
        groups = {ConfCreateGroup.class, ConfUpdateGroup.class},
        message = "{error.message.conf.alert.null}"
    )
    private Boolean alert;


    /**
     * 系统配置创建验证组
     */
    public interface ConfCreateGroup {}

    /**
     * 系统配置更新验证组
     */
    public interface ConfUpdateGroup {}

}
