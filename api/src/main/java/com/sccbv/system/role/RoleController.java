package com.sccbv.system.role;

import com.sccbv.system.permission.Permission;
import com.whatakitty.ssm.dto.Pageable;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色资源接口
 *
 * @author xuqiang
 * @date 2018/01/11
 * @description
 **/
@Controller
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable,
                         @RequestBody(required = false) @Validated RoleDTO roleDTO,
                         @RequestParam(defaultValue = "false", required = false) boolean isPage) {
        return roleService.pageByDTO(pageable, roleDTO, isPage, false);
    }


    @ResponseBody
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Role detail(@PathVariable Long roleId) {
        return roleService.byPrimaryKey(roleId, false);
    }


    @ResponseBody
    @RequestMapping(value = "/{roleId}/permissions", method = RequestMethod.GET)
    public List<Permission> permissions(@PathVariable Long roleId) {
        return roleService.permissions(roleId, false);
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void create(
        @Validated(RoleDTO.RoleCreateGroup.class) @RequestBody RoleDTO roleDTO
    ) {
        roleService.create(roleDTO, new Date());
    }


    @ResponseBody
    @RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
    public void update(
        @PathVariable Long roleId,
        @Validated(RoleDTO.RoleUpdateGroup.class) @RequestBody RoleDTO roleDTO
    ) {
        roleService.update(roleId, roleDTO, new Date());
    }


    @ResponseBody
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public void destroy(@PathVariable Long roleId) {
        roleService.destroy(roleId, false);
    }


}
