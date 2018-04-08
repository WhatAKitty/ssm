/*
 * *****************************************************************************
 *  杭州高网，机密
 *  __________________
 *
 *  [2016] - [2020] 杭州高网信息技术有限公司
 *  版权所有。
 *
 *  注意：此处包含的所有信息均为杭州高网信息技术有限公司的财产。知识和技术理念
 *  包含在内为杭州高网信息技术有限公司所有，可能受中国和国际专利，以及商业秘密
 *  或版权法保护。严格禁止传播此信息或复制此材料，除非事先获得来自杭州高网信
 *  息技术有限公司的书面许可。
 *
 */

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

        return false;
    }
}
