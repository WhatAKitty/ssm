package com.sccbv.webapp.system.role;

import com.sccbv.system.permission.Permission;
import com.sccbv.system.permission.PermissionService;
import com.sccbv.system.role.Role;
import com.sccbv.system.role.RoleService;
import com.sccbv.system.rolespermissions.RolesPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色资源接口
 *
 * @author xuqiang
 * @date 2018/01/11
 * @description
 **/
@Controller
@RequestMapping(value = "/system/roles-view")
public class RoleViewController {

    private final RoleService roleService;
    private final PermissionService permissionService;
    private final RolesPermissionsService rolesPermissionsService;

    /**
     * 初始化角色Controller层
     *
     * @param roleService 角色服务
     */
    @Autowired
    public RoleViewController(
            RoleService roleService,
            PermissionService permissionService,
            RolesPermissionsService rolesPermissionsService
    ) {
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.rolesPermissionsService = rolesPermissionsService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String indexView() {
        return "pages/system/role/list";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Role role = roleService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/role/view");
        modelAndView.addObject("role", role);
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addView(ModelAndView modelAndView) {
        final Map<String, List<Permission>> groupedPermissions = permissionService.all(false)
                .stream()
                .collect(Collectors.groupingBy(Permission::getCategory));

        modelAndView.setViewName("pages/system/role/replace");
        modelAndView.addObject("groupedPermissions", groupedPermissions);
        return modelAndView;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        final Map<String, Object> roleWithPermissions = rolesPermissionsService.byRoleId(id);
        final Role role = (Role) roleWithPermissions.get("role");
        final Map<String, List<Permission>> groupedPermissions = permissionService.all(false)
                .stream()
                .collect(Collectors.groupingBy(Permission::getCategory));
        final List<Long> rolePermissions = ((List<Permission>) roleWithPermissions.get("permissions"))
                .stream()
                .map(permission -> permission.getId())
                .collect(Collectors.toList());

        modelAndView.setViewName("/pages/system/role/replace");
        modelAndView.addObject("role", role);
        modelAndView.addObject("groupedPermissions", groupedPermissions);
        modelAndView.addObject("rolePermissions", rolePermissions);
        return modelAndView;
    }

}
