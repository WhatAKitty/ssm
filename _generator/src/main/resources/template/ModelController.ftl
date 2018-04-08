package ${packageName}.${moduleName}.${uncapitalizedClassName};

import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ${funcName}资源接口
 *
 * @date ${date}
 * @description
 **/
@Controller
@RequestMapping("/${classNames}")
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
        this.restWrapper = RestWrapper
            .create("id", <#list schema.getKeys() as key><#assign col = schema.get(key) /><#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>"${col.getCamelColumnName()}"<#if key?has_next>, </#if></#if></#list>)
            .addHandler("id", String::valueOf);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable,
                         ${className}DTO ${uncapitalizedClassName}DTO,
                         @RequestParam(defaultValue = "false") Boolean isPage) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.pageByDTO(pageable, ${uncapitalizedClassName}DTO, isPage, false));
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
