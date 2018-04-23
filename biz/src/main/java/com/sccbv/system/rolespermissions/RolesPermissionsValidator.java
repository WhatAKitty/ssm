package com.sccbv.system.rolespermissions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * 角色权限数据自定义验证
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
public class RolesPermissionsValidator implements ConstraintValidator<RolesPermissionsValidate, RolesPermissionsDTO> {

    @Override
    public void initialize(RolesPermissionsValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(RolesPermissionsDTO rolesPermissionsDTO, ConstraintValidatorContext context) {
        if (rolesPermissionsDTO.getRoleId() == null) {
            if (StringUtils.isBlank(rolesPermissionsDTO.getName())) {
                return false;
            }
        }

        if (rolesPermissionsDTO.getPermissionIdList() == null || rolesPermissionsDTO.getPermissionIdList().isEmpty()) {
            return false;
        }

        return true;
    }
}
