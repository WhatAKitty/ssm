package com.sccbv.system.rolespermissions;

import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 角色权限数据载体类
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Data
@RolesPermissionsValidate(message = "error.RolesPermissionsDTO.create", groups = RolesPermissionsDTO.RolesPermissionsCreateGroup.class)
public class RolesPermissionsDTO {

    @NotEmpty(
            groups = {RolesPermissionsCreateGroup.class, RolesPermissionsUpdateGroup.class},
            message = "error.message.RolesPermissionsDTO.code.empty"
    )
    private String code;

    @NotEmpty(
        groups = {RolesPermissionsCreateGroup.class, RolesPermissionsUpdateGroup.class},
        message = "error.message.RolesPermissionsDTO.name.empty"
    )
    private String name;

    private String remark;

    private Long roleId;

    private List<Long> permissionIdList;

    private Long permissionId;

    /**
     * @date 2018/01/24
     */
    public interface RolesPermissionsCreateGroup {
    }

    /**
     * @date 2018/01/24
     */
    public interface RolesPermissionsUpdateGroup {
    }


}

