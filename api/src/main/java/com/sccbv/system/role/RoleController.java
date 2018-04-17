package com.sccbv.system.role;

import com.sccbv.system.permission.Permission;
import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestPageWrapper;
import com.whatakitty.ssm.wrapper.RestWrapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 角色资源接口
 *
 * @author xuqiang
 * @date 2018/01/11
 * @description
 **/
@Controller
@RequestMapping(value = "/system/roles")
public class RoleController {

    private final RoleService roleService;
    private final RestWrapper restWrapper;

    /**
     * 初始化角色Controller层
     *
     * @param roleService 角色服务
     */
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
        this.restWrapper = RestWrapper
            .create("id", "code", "name")
            .addHandler("id", String::valueOf);
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String indexView() {
        return "pages/system/role/list";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Role role = roleService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/role/view");
        modelAndView.addObject("role", role);
        return modelAndView;
    }


    @RequestMapping(value = "/add/view", method = RequestMethod.GET)
    public String addView() {
        return "pages/system/role/replace";
    }


    @RequestMapping(value = "/edit/view/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Role role = roleService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/role/replace");
        modelAndView.addObject("role", role);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable,
                         @Validated RoleDTO roleDTO,
                         @RequestParam(defaultValue = "false", required = false) boolean isPage) {
        return RestPageWrapper.wrap(roleService.search(pageable, roleDTO, isPage, false), restWrapper);
    }


    @ResponseBody
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Object detail(@PathVariable Long roleId) {
        return restWrapper.wrap(roleService.byPrimaryKey(roleId, false));
    }


    @ResponseBody
    @RequestMapping(value = "/{roleId}/permissions", method = RequestMethod.GET)
    public Object permissions(@PathVariable Long roleId) {
        return restWrapper.wrap(roleService.permissions(roleId, false));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Object create(
        @Validated(RoleDTO.RoleCreateGroup.class) RoleDTO roleDTO
    ) {
        return restWrapper.wrap(roleService.create(roleDTO, new Date()));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
    public Object update(
        @PathVariable Long roleId,
        @Validated(RoleDTO.RoleUpdateGroup.class) RoleDTO roleDTO
    ) {
        return restWrapper.wrap(roleService.update(roleId, roleDTO, new Date()));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public Object destroy(@PathVariable Long roleId) {
        return restWrapper.wrap(roleService.destroy(roleId, false));
    }


}
