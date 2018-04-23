package com.sccbv.system.rolespermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色权限关系管理接口
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Controller
@RequestMapping(value = "/system/roles-permissions")
public class RolesPermissionController {

    @Autowired
    private RolesPermissionsService rolesPermissionsService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Object get(@PathVariable Long roleId) {
        return rolesPermissionsService.byRoleId(roleId);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Object create(@Validated(RolesPermissionsDTO.RolesPermissionsCreateGroup.class) @RequestBody RolesPermissionsDTO rolesPermissionsDTO) {
        return rolesPermissionsService.create(rolesPermissionsDTO);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
    public Object update(@PathVariable Long roleId, @Validated(RolesPermissionsDTO.RolesPermissionsUpdateGroup.class) @RequestBody RolesPermissionsDTO rolesPermissionsDTO) {
        return rolesPermissionsService.update(roleId, rolesPermissionsDTO);
    }

}
