package com.sccbv.system.user;

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
 * 用户资源接口
 *
 * @date 2018/04/15
 * @description
 **/
@Controller
@RequestMapping("/system/users")
public class UserController {

    private final UserService userService;
    private final RestWrapper restWrapper;

    /**
     * 初始化用户Controller层
     *
     * @param userService 用户服务
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        // TODO 需要删除不想渲染给客户端的字段
        this.restWrapper = RestWrapper
            .create("id", "username", "password", "name", "isExpired", "isLocked", "isEnabled")
            .addHandler("id", String::valueOf);
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String indexView() {
        return "pages/system/user/list";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        User user = userService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/user/view");
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @RequestMapping(value = "/add/view", method = RequestMethod.GET)
    public String addView() {
        return "pages/system/user/replace";
    }


    @RequestMapping(value = "/edit/view/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        User user = userService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/system/user/replace");
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object list(Pageable pageable,
                         UserDTO userDTO,
                         @RequestParam(defaultValue = "false") Boolean isPage) {
        return RestPageWrapper.wrap(userService.search(pageable, userDTO, isPage, false), restWrapper);
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
