package ${packageName}.${moduleName}.${uncapitalizedClassName};

import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.service.BusinessService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

/**
 * ${funcName}业务层
 *
 * @date ${date}
 * @description
 **/
@Service
public class ${className}Service extends BusinessService<${className}, ${className}DTO> {

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    @Autowired
    public ${className}Service(${className}Mapper mapper) {
        super(mapper, ${className}.class);
    }

    /**
     * 检查${funcName}是否有效
     *
     * @param ${uncapitalizedClassName}DTO ${funcName}信息
     * @param excludeId   排除的${funcName}
     */
    public void valid(${className}DTO ${uncapitalizedClassName}DTO, Long excludeId) {
        Example example = Example.builder(${className}.class).build();
        // TODO 待完成的验证条件

        if (excludeId != null) {
            example.and().andNotEqualTo("id", excludeId);
        }
        Asserts.isFalse(existsByExample(example, false), 400, "已经存在相同的${funcName}");
    }

    /**
     * 创建${funcName}
     *
     * @param ${uncapitalizedClassName}DTO ${funcName}信息
     * @return 创建的${funcName}
     */
    public ${className} create(${className}DTO ${uncapitalizedClassName}DTO) {
        valid(${uncapitalizedClassName}DTO, null);
        return super.create(${uncapitalizedClassName}DTO, new Date());
    }

    /**
     * 更新${funcName}
     *
     * @param ${uncapitalizedClassName}Id  ${funcName}编号
     * @param ${uncapitalizedClassName}DTO ${funcName}信息
     * @return 更新后的${funcName}
     */
    public ${className} update(Long ${uncapitalizedClassName}Id, ${className}DTO ${uncapitalizedClassName}DTO) {
        valid(${uncapitalizedClassName}DTO, ${uncapitalizedClassName}Id);
        return super.update(${uncapitalizedClassName}Id, ${uncapitalizedClassName}DTO, false);
    }

}
