package com.sccbv.webapp.system.permission;

import com.sccbv.system.permission.Permission;
import com.sccbv.system.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统权限资源接口
 *
 * @author yuhailun
 * @date 2018/01/08
 * @description
 **/
@Controller
@RequestMapping("/system/permissions-view")
public class PermissionViewController {

    private final PermissionService permissionService;

    /**
     * 初始化权限Controller层
     *
     * @param permissionService 权限服务
     */
    @Autowired
    public PermissionViewController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String indexView() {
        return "pages/system/permission/list";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Permission permission = permissionService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/permission/view");
        modelAndView.addObject("permission", permission);
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView() {
        return "pages/system/permission/replace";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Permission permission = permissionService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/permission/replace");
        modelAndView.addObject("permission", permission);
        return modelAndView;
    }

}
