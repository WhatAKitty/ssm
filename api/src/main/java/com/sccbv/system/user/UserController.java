package com.sccbv.system.user;

import com.whatakitty.ssm.dto.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * OAuth2授权中心用户信息
 *
 * @author xuqiang
 * @author yuhailun
 * @date 2018/1/14
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#oauth2.hasScope('ui')")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Object getUser(Authentication authentication) {
        return authentication.getPrincipal();
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable, UserDTO userDTO, @RequestParam(required = false, defaultValue = "false") Boolean isPage) {
        return userService.pageByDTO(pageable, userDTO, isPage, false);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Object create(@Validated(UserDTO.UserCreateGroup.class) @RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public void update(@PathVariable Long userId,
                       @Validated({UserDTO.UserUpdateGroup.class}) @RequestBody UserDTO userDTO) {
        userService.update(userId, userDTO);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long userId) {
        userService.destroy(userId, false);
    }

}
