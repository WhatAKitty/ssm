package com.sccbv.system.permission;

import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统权限资源接口
 *
 * @author yuhailun
 * @date 2018/01/08
 * @description
 **/
@Controller
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    private final RestWrapper restWrapper;

    /**
     * 初始化权限Controller层
     */
    public PermissionController() {
        this.restWrapper = RestWrapper
            .create("id", "code", "name", "category")
            .addHandler("id", id -> String.valueOf(id));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable,
                         PermissionDTO permissionDTO,
                         @RequestParam(defaultValue = "false") Boolean isPage) {
        return restWrapper.wrap(permissionService.pageByDTO(pageable, permissionDTO, isPage, false));
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
        return restWrapper.wrap(permissionService.update(permissionDTO, permissionId));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{permissionId}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Long permissionId) {
        return restWrapper.wrap(permissionService.destroy(permissionId));
    }

}
