package ${packageName}.webapp.${moduleName}.${uncapitalizedClassName};

import ${packageName}.${moduleName}.${uncapitalizedClassName}.${className};
import ${packageName}.${moduleName}.${uncapitalizedClassName}.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ${funcName}视图
 *
 * @author ${author}
 * @date ${date}
 * @description
 **/
@Controller
@RequestMapping("/${moduleName}/${classNames}-view")
public class ${className}ViewController {

    private final ${className}Service ${uncapitalizedClassName}Service;

    /**
     * 初始化权限Controller层
     *
     * @param ${uncapitalizedClassName}Service ${funcName}服务
     */
    @Autowired
    public ${className}ViewController(${className}Service ${uncapitalizedClassName}Service) {
        this.${uncapitalizedClassName}Service = ${uncapitalizedClassName}Service;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String indexView() {
        return "pages/${moduleName}/${uncapitalizedClassName}/list";
    }


    @RequestMapping(value = "/{${uncapitalizedClassName}Id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("${uncapitalizedClassName}Id") Long ${uncapitalizedClassName}Id,
        ModelAndView modelAndView
    ) {
        ${className} ${uncapitalizedClassName} = ${uncapitalizedClassName}Service.byPrimaryKey(${uncapitalizedClassName}Id, false);

        modelAndView.setViewName("/pages/${moduleName}/${uncapitalizedClassName}/view");
        modelAndView.addObject("${uncapitalizedClassName}", ${uncapitalizedClassName});
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView() {
        return "pages/${moduleName}/${uncapitalizedClassName}/replace";
    }


    @RequestMapping(value = "/edit/{${uncapitalizedClassName}Id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("${uncapitalizedClassName}Id") Long ${uncapitalizedClassName}Id,
        ModelAndView modelAndView
    ) {
        ${className} ${uncapitalizedClassName} = ${uncapitalizedClassName}Service.byPrimaryKey(${uncapitalizedClassName}Id, false);

        modelAndView.setViewName("/pages/${moduleName}/${uncapitalizedClassName}/replace");
        modelAndView.addObject("${uncapitalizedClassName}", ${uncapitalizedClassName});
        return modelAndView;
    }

}
