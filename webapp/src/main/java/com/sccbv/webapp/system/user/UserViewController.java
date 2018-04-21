package com.sccbv.webapp.system.user;

import com.sccbv.system.role.Role;
import com.sccbv.system.role.RoleService;
import com.sccbv.system.user.User;
import com.sccbv.system.user.UserService;
import com.sccbv.system.usersroles.UsersRolesService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户资源接口
 *
 * @date 2018/04/15
 * @description
 **/
@Controller
@RequestMapping("/system/users-view")
public class UserViewController {

    private final UserService userService;
    private final RoleService roleService;
    private final UsersRolesService usersRolesService;

    /**
     * 初始化用户Controller层
     *
     * @param userService 用户服务
     */
    @Autowired
    public UserViewController(
        UserService userService,
        RoleService roleService,
        UsersRolesService usersRolesService
    ) {
        this.userService = userService;
        this.usersRolesService = usersRolesService;
        this.roleService = roleService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String indexView() {
        return "pages/system/user/list";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        modelAndView.setViewName("/pages/system/user/view");
        modelAndView.addAllObjects(usersRolesService.byUserId(id, false));
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addView(ModelAndView modelAndView) {
        modelAndView.setViewName("/pages/system/user/replace");
        modelAndView.addObject("roles", roleService.all(false));
        return modelAndView;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        final Map<String, Object> userWithRoles = usersRolesService.byUserId(id, false);
        final User user = (User) userWithRoles.get("user");
        final List<Long> userRoleIds = ((List<Role>) userWithRoles.get("roles"))
            .stream()
            .map(role -> role.getId())
            .collect(Collectors.toList());

        modelAndView.setViewName("/pages/system/user/replace");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userRoles", userRoleIds);
        modelAndView.addObject("roles", roleService.all(false));
        return modelAndView;
    }

}
