package com.sccbv.webapp.system.role;

import com.sccbv.system.role.Role;
import com.sccbv.system.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 初始化角色Controller层
     *
     * @param roleService 角色服务
     */
    @Autowired
    public RoleViewController(RoleService roleService) {
        this.roleService = roleService;
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
    public String addView() {
        return "pages/system/role/replace";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Role role = roleService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/role/replace");
        modelAndView.addObject("role", role);
        return modelAndView;
    }

}
