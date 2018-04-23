package com.sccbv.system.usersroles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户角色数据验证
 *
 * @author yuhailun
 * @author xuqiang
 * @date 2018/01/24
 * @description
 **/
public class UsersRolesValidator implements ConstraintValidator<UsersRolesValidate, UsersRolesDTO> {


    @Override
    public void initialize(UsersRolesValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(UsersRolesDTO usersRolesDTO, ConstraintValidatorContext context) {
        if (usersRolesDTO.getUserId() != null) {
            return true;
        }

        if (StringUtils.isBlank(usersRolesDTO.getUsername())
            || StringUtils.isBlank(usersRolesDTO.getPassword())
            || StringUtils.isBlank(usersRolesDTO.getName())
            || usersRolesDTO.getIsEnabled() == null) {
            return false;
        }

        return true;
    }
}
