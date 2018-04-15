package com.sccbv.system.home;

import com.alibaba.fastjson.JSONObject;
import com.sccbv.system.conf.Conf;
import com.sccbv.system.conf.ConfService;
import com.sccbv.system.menu.Menu;
import com.sccbv.system.menu.MenuService;
import com.sccbv.system.menu.MenuViewUtils;
import com.sccbv.utils.WebSecurityExpressionEvaluator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/07
 * @description
 **/
@Controller
@RequestMapping("/")
public class HomeController {

    private final ConfService confService;
    private final MenuService menuService;
    private final WebSecurityExpressionEvaluator evaluator;

    @Autowired
    public HomeController(
        ConfService confService,
        MenuService menuService,
        WebSecurityExpressionEvaluator webSecurityExpressionEvaluator
    ) {
        this.confService = confService;
        this.menuService = menuService;
        this.evaluator = webSecurityExpressionEvaluator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(
        ModelAndView modelAndView,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        modelAndView.setViewName("index");
        modelAndView.addObject("menu", userMenu(request, response));
        modelAndView.addObject("conf", loadSystemConfs());
        return modelAndView;
    }


    /**
     * 获取用户的菜单
     *
     * @param request  Servlet request
     * @param response Servlet response
     * @return 用户菜单HTML代码
     */
    private String userMenu(HttpServletRequest request, HttpServletResponse response) {
        JSONObject menu = menuService.treeMenuList(new MenuService.TreeMenuFilterCallbck() {

            @Override
            public boolean filter(Menu menu) {
                return !evaluator.evaluate(menu.getSpel(), request, response);
            }

            @Override
            public Object item(JSONObject item) {
                return MenuViewUtils.transform(item);
            }

        });

        return MenuViewUtils.transformArray(menu.getJSONArray("children"));
    }

    /**
     * 载入系统配置
     *
     * @return 系统配置
     */
    private Conf loadSystemConfs() {
        return confService.all(true).get(0);
    }

}
