package com.sccbv.system.usersroles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色关系管理接口
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Controller
@RequestMapping(value = "/system/users-roles")
public class UsersRolesController {

    @Autowired
    private UsersRolesService usersRolesService;


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object get(@PathVariable("userId") Long userId) {
        return usersRolesService.byUserId(userId, false);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Object create(@Validated(UsersRolesDTO.UsersRolesCreateGroup.class) @RequestBody UsersRolesDTO usersRolesDTO) {
        return usersRolesService.create(usersRolesDTO);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Object update(@PathVariable Long userId,
                         @Validated(UsersRolesDTO.UsersRolesUpdateGroup.class) @RequestBody UsersRolesDTO usersRolesDTO) {
        return usersRolesService.update(usersRolesDTO, userId);
    }

}
