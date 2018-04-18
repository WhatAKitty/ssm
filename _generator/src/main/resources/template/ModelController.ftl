package ${packageName}.${moduleName}.${uncapitalizedClassName};

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
 * ${funcName}资源接口
 *
 * @date ${date}
 * @description
 **/
@Controller
@RequestMapping("/${moduleName}/${classNames}")
public class ${className}Controller {

    private final ${className}Service ${uncapitalizedClassName}Service;
    private final RestWrapper restWrapper;

    /**
     * 初始化${funcName}Controller层
     *
     * @param ${uncapitalizedClassName}Service ${funcName}服务
     */
    @Autowired
    public ${className}Controller(${className}Service ${uncapitalizedClassName}Service) {
        this.${uncapitalizedClassName}Service = ${uncapitalizedClassName}Service;
        // TODO 需要删除不想渲染给客户端的字段
        this.restWrapper = RestWrapper
            .create("id", <#list schema.getKeys() as key><#assign col = schema.get(key) /><#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>"${col.getCamelColumnName()}"<#if key?has_next>, </#if></#if></#list>)
            .addHandler("id", String::valueOf);
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String indexView() {
        return "pages/${moduleName}/${uncapitalizedClassName}/list";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        ${className} ${uncapitalizedClassName} = ${uncapitalizedClassName}Service.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/${moduleName}/${uncapitalizedClassName}/view");
        modelAndView.addObject("${uncapitalizedClassName}", ${uncapitalizedClassName});
        return modelAndView;
    }


    @RequestMapping(value = "/add/view", method = RequestMethod.GET)
    public String addView() {
        return "pages/${moduleName}/${uncapitalizedClassName}/replace";
    }


    @RequestMapping(value = "/edit/view/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        ${className} ${uncapitalizedClassName} = ${uncapitalizedClassName}Service.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/${moduleName}/${uncapitalizedClassName}/replace");
        modelAndView.addObject("${uncapitalizedClassName}", ${uncapitalizedClassName});
        return modelAndView;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object list(Pageable pageable,
                         ${className}DTO ${uncapitalizedClassName}DTO,
                         @RequestParam(defaultValue = "false") Boolean isPage) {
        return RestPageWrapper.wrap(${uncapitalizedClassName}Service.search(pageable, ${uncapitalizedClassName}DTO, isPage, false), restWrapper);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{${uncapitalizedClassName}Id}", method = RequestMethod.GET)
    public Object detail(@PathVariable("${uncapitalizedClassName}Id") Long ${uncapitalizedClassName}Id) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.byPrimaryKey(${uncapitalizedClassName}Id, false));
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@Validated({${className}DTO.${className}CreateGroup.class}) @RequestBody ${className}DTO ${uncapitalizedClassName}DTO) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.create(${uncapitalizedClassName}DTO));
    }


    @ResponseBody
    @RequestMapping(value = "/{${uncapitalizedClassName}Id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object update(@Validated({${className}DTO.${className}UpdateGroup.class}) @RequestBody ${className}DTO ${uncapitalizedClassName}DTO,
                         @PathVariable Long ${uncapitalizedClassName}Id) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.update(${uncapitalizedClassName}Id, ${uncapitalizedClassName}DTO));
    }


    @ResponseBody
    @RequestMapping(value = "/{${uncapitalizedClassName}Id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object delete(@PathVariable Long ${uncapitalizedClassName}Id) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.destroy(${uncapitalizedClassName}Id));
    }

}
