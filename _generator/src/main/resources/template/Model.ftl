package ${packageName}.${moduleName}.${uncapitalizedClassName};

import com.whatakitty.ssm.db.mybatis.SDelEntity;
import java.util.Date;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${funcName}实体类
 *
 * @date ${date}
 * @description
 **/
@Data
@Table(name = "${tableName}")
@EqualsAndHashCode(callSuper = true)
public class ${className} extends SDelEntity {

    // TODO 需要删除不需要的字段
    <#list schema.getKeys() as key>
    <#assign col = schema.get(key) />
    <#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>
    /**
     * ${col.getCOLUMN_COMMENT()}
     */
    private ${col.getDATA_TYPE()} ${col.getCamelColumnName()};

    </#if>
    </#list>

}
