package ${packageName}.${moduleName}.${uncapitalizedClassName};

import com.gnet.commons.db.mybatis.SDelEntity;
import java.util.Date;
import javax.persistence.Table;
import lombok.Data;

/**
 * ${funcName}实体类
 *
 * @date ${date}
 * @description
 **/
@Data
@Table(name = "${tableName}")
public class ${className} extends SDelEntity {

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
