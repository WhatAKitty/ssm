package com.sccbv.system.user;

import com.sccbv.system.role.RoleService;
import com.sccbv.system.usersroles.UsersRolesService;
import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestPageWrapper;
import com.whatakitty.ssm.wrapper.RestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户资源接口
 *
 * @date 2018/04/15
 * @description
 **/
@Controller
@RequestMapping("/system/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UsersRolesService usersRolesService;
    private final RestWrapper restWrapper;

    /**
     * 初始化用户Controller层
     *
     * @param userService 用户服务
     */
    @Autowired
    public UserController(
        UserService userService,
        RoleService roleService,
        UsersRolesService usersRolesService
    ) {
        this.userService = userService;
        this.usersRolesService = usersRolesService;
        this.roleService = roleService;
        this.restWrapper = RestWrapper
            .create("id", "username", "password", "name", "isExpired", "isLocked", "isEnabled")
            .addHandler("id", String::valueOf);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object list(Pageable pageable,
                       UserDTO userDTO,
                       @RequestParam(defaultValue = "true") Boolean isPage) {
        return RestPageWrapper.wrap(userService.search(pageable, userDTO, isPage, false), restWrapper);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Object detail(@PathVariable("userId") Long userId) {
        return restWrapper.wrap(userService.byPrimaryKey(userId, false));
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@Validated({UserDTO.UserCreateGroup.class}) @RequestBody UserDTO userDTO) {
        return restWrapper.wrap(userService.create(userDTO));
    }


    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object update(@Validated({UserDTO.UserUpdateGroup.class}) @RequestBody UserDTO userDTO,
                         @PathVariable Long userId) {
        return restWrapper.wrap(userService.update(userId, userDTO));
    }


    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object delete(@PathVariable Long userId) {
        return restWrapper.wrap(userService.destroy(userId));
    }

}
