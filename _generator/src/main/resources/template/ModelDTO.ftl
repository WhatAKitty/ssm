package ${packageName}.${moduleName}.${uncapitalizedClassName};

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ${funcName}数据传输载体
 *
 * @date ${date}
 * @description
 **/
@Data
public class ${className}DTO {

    private Long id;

    <#list schema.getKeys() as key>
    <#assign col = schema.get(key) />
    <#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>
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
