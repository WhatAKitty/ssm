package ${packageName}.${moduleName}.${uncapitalizedClassName};

import com.gnet.commons.wrapper.RestWrapper;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ${funcName}资源接口
 *
 * @date ${date}
 * @description
 **/
@Api(value = "${funcName}管理", consumes = "application/json", produces = "application/json", protocols = "http")
@RestController
@PreAuthorize("#oauth2.hasAnyScope('server', 'ui')")
@RequestMapping(path = "/${classNames}")
public class ${className}Controller {

    private final ${className}Service ${uncapitalizedClassName}Service;
    private final RestWrapper restWrapper;


    /**
     * 初始化${funcName}Controller层
     *
     * @param ${uncapitalizedClassName}Service ${funcName}服务
     */
    public ${className}Controller(${className}Service ${uncapitalizedClassName}Service) {
        this.${uncapitalizedClassName}Service = ${uncapitalizedClassName}Service;
        this.restWrapper = RestWrapper
            .create("id", <#list schema.getKeys() as key><#assign col = schema.get(key) /><#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>"${col.getCamelColumnName()}"<#if key?has_next>, </#if></#if></#list>)
            .addHandler("id", String::valueOf);
    }


    @ApiOperation(value = "search", notes = "查询${funcName}列表")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "${uncapitalizedClassName}DTO", value = "查询参数", dataTypeClass = ${className}DTO.class),
        @ApiImplicitParam(paramType = "query", name = "isPage", value = "是否分页", defaultValue = "false", dataTypeClass = Boolean.class)
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取${funcName}列表成功", response = ${className}.class, responseContainer = "List")
    })
    @RequestMapping(method = RequestMethod.GET)
    public Object search(Pageable pageable,
                         ${className}DTO ${uncapitalizedClassName}DTO,
                         @RequestParam(defaultValue = "false") Boolean isPage) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.pageByDTO(pageable, ${uncapitalizedClassName}DTO, isPage, false));
    }


    @ApiOperation(value = "create", notes = "创建${funcName}")
    @ApiImplicitParam(paramType = "form", name = "${uncapitalizedClassName}DTO", value = "${funcName}数据", dataTypeClass = ${className}DTO.class)
    @ApiResponses({
        @ApiResponse(code = 201, message = "创建${funcName}成功", response = ${className}.class),
        @ApiResponse(code = 500, message = "创建${funcName}失败")
    })
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Object create(@Validated({${className}DTO.${className}CreateGroup.class}) @RequestBody ${className}DTO ${uncapitalizedClassName}DTO) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.create(${uncapitalizedClassName}DTO));
    }


    @ApiOperation(value = "update", notes = "修改${funcName}")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "body", name = "${uncapitalizedClassName}DTO", value = "${funcName}数据", dataTypeClass = ${className}DTO.class),
        @ApiImplicitParam(paramType = "path", name = "${uncapitalizedClassName}Id", value = "${funcName}编号", required = true, dataTypeClass = Long.class)
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "修改${funcName}成功"),
        @ApiResponse(code = 500, message = "修改${funcName}失败")
    })
    @RequestMapping(path = "/{${uncapitalizedClassName}Id}", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Object update(@Validated({${className}DTO.${className}UpdateGroup.class}) @RequestBody ${className}DTO ${uncapitalizedClassName}DTO,
                         @PathVariable Long ${uncapitalizedClassName}Id) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.update(${uncapitalizedClassName}Id, ${uncapitalizedClassName}DTO));
    }


    @ApiOperation(value = "destroy", notes = "删除${funcName}")
    @ApiImplicitParam(paramType = "path", name = "${uncapitalizedClassName}Id", value = "${funcName}编号", required = true, dataTypeClass = Long.class)
    @ApiResponses({
        @ApiResponse(code = 204, message = "删除${funcName}成功"),
        @ApiResponse(code = 500, message = "删除${funcName}失败")
    })
    @RequestMapping(path = "/{${uncapitalizedClassName}Id}", method = RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Object delete(@PathVariable Long ${uncapitalizedClassName}Id) {
        return restWrapper.wrap(${uncapitalizedClassName}Service.destroy(${uncapitalizedClassName}Id));
    }

}
