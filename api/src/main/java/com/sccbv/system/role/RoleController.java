package com.sccbv.system.role;

import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestPageWrapper;
import com.whatakitty.ssm.wrapper.RestWrapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable,
                         @Validated RoleDTO roleDTO,
                         @RequestParam(defaultValue = "true", required = false) boolean isPage) {
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
