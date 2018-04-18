package com.sccbv.system.permission;

import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestPageWrapper;
import com.whatakitty.ssm.wrapper.RestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统权限资源接口
 *
 * @author yuhailun
 * @date 2018/01/08
 * @description
 **/
@Controller
@RequestMapping("/system/permissions")
public class PermissionController {

    private final PermissionService permissionService;
    private final RestWrapper restWrapper;

    /**
     * 初始化权限Controller层
     *
     * @param permissionService 权限服务
     */
    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
        this.restWrapper = RestWrapper
            .create("id", "code", "name", "category", "remark")
            .addHandler("id", String::valueOf);
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String indexView() {
        return "pages/system/permission/list";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Permission permission = permissionService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/permission/view");
        modelAndView.addObject("permission", permission);
        return modelAndView;
    }


    @RequestMapping(value = "/add/view", method = RequestMethod.GET)
    public String addView() {
        return "pages/system/permission/replace";
    }


    @RequestMapping(value = "/edit/view/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Permission permission = permissionService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/permission/replace");
        modelAndView.addObject("permission", permission);
        return modelAndView;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable,
                         PermissionDTO permissionDTO,
                         @RequestParam(defaultValue = "true") Boolean isPage) {
        return RestPageWrapper.wrap(permissionService.search(pageable, permissionDTO, isPage, false), restWrapper);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Object create(@Validated({PermissionDTO.PermissionCreateGroup.class}) @RequestBody PermissionDTO permissionDTO) {
        return restWrapper.wrap(permissionService.create(permissionDTO));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{permissionId}", method = RequestMethod.PUT)
    public Object update(@Validated({PermissionDTO.PermissionUpdateGroup.class}) @RequestBody PermissionDTO permissionDTO,
                         @PathVariable Long permissionId) {
        return restWrapper.wrap(permissionService.update(permissionId, permissionDTO));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{permissionId}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Long permissionId) {
        return restWrapper.wrap(permissionService.destroy(permissionId));
    }

}
