package ${packageName}.${moduleName}.${uncapitalizedClassName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * ${funcName}数据传输载体
 *
 * @date ${date}
 * @description
 **/
@ApiModel(value = "${funcName}", description = "用于传递${funcName}数据的数据载体")
@Data
public class ${className}DTO {

    @ApiModelProperty(value = "${funcName}编号", notes = "${funcName}编号", dataType = "Long")
    private Long id;

    <#list schema.getKeys() as key>
    <#assign col = schema.get(key) />
    <#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>
    @ApiModelProperty(value = "${col.COLUMN_COMMENT}", notes = "${col.COLUMN_COMMENT}", dataType = "${col.DATA_TYPE}")
    <#if !col.isNullable()>
    <#if col.DATA_TYPE == "String">@NotEmpty<#else>@NotNull</#if>(
        groups = {${className}CreateGroup.class, ${className}UpdateGroup.class},
        message = "{error.message.${uncapitalizedClassName}.${col.getCamelColumnName()}.<#if col.DATA_TYPE == "String">empty<#else>null</#if>}"
    )
    </#if>
    private ${col.DATA_TYPE} ${col.getCamelColumnName()};

    </#if>
    </#list>

    /**
     * ${funcName}创建验证组
     */
    public interface ${className}CreateGroup {}

    /**
     * ${funcName}更新验证组
     */
    public interface ${className}UpdateGroup {}

}
