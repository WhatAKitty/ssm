package com.sccbv.system.permission;

import com.google.common.collect.ImmutableMap;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.service.BusinessService;
import com.whatakitty.ssm.utils.OrderByUtils;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 权限业务层
 *
 * @date 2018/04/17
 * @description
 **/
@Service
public class PermissionService extends BusinessService<Permission, PermissionDTO> {

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    @Autowired
    public PermissionService(PermissionMapper mapper) {
        super(mapper, Permission.class);
    }

    /**
     * 检查权限是否有效
     *
     * @param permissionDTO 权限信息
     * @param excludeId   排除的权限
     */
    public void valid(PermissionDTO permissionDTO, Long excludeId) {
        Example example = Example.builder(Permission.class).build();
        example.and().andEqualTo("code", permissionDTO.getCode());

        if (excludeId != null) {
            example.and().andNotEqualTo("id", excludeId);
        }
        Asserts.isFalse(existsByExample(example, false), 400, "已经存在相同的权限");
    }

    /**
     * 查询权限
     *
     * @param pageable 分页信息
     * @param permissionDTO  条件载体
     * @param isPage   是否允许分页
     * @param force    是否包含被软删除的记录
     * @return 查询结果
     */
    public Object search(Pageable pageable, PermissionDTO permissionDTO, boolean isPage, boolean force) {
        Example example = new Example(Permission.class);
        if (StringUtils.isNotBlank(permissionDTO.getCode())) {
            example.and().andLike("code", permissionDTO.getCode() + "%");
        }
        if (StringUtils.isNotBlank(permissionDTO.getName())) {
            example.and().andLike("name", permissionDTO.getName() + "%");
        }
        if (StringUtils.isNotBlank(permissionDTO.getCategory())) {
            example.and().andLike("category", permissionDTO.getCategory() + "%");
        }
        String orderBy = OrderByUtils.getOrderBy(pageable.getSort(), ImmutableMap.of(
            "id", "id",
            "code", "convert(code using gbk)",
            "name", "convert(name using gbk)",
            "category", "convert(category using gbk)"
        ));
        if (StringUtils.isNotBlank(orderBy)) {
            example.setOrderByClause(orderBy);
        }
        return super.pageByExample(pageable, example, isPage, force);
    }

    /**
     * 创建权限
     *
     * @param permissionDTO 权限信息
     * @return 创建的权限
     */
    public Permission create(PermissionDTO permissionDTO) {
        valid(permissionDTO, null);
        return super.create(permissionDTO, new Date());
    }

    /**
     * 更新权限
     *
     * @param permissionId  权限编号
     * @param permissionDTO 权限信息
     * @return 更新后的权限
     */
    public Permission update(Long permissionId, PermissionDTO permissionDTO) {
        valid(permissionDTO, permissionId);
        return super.update(permissionId, permissionDTO, false);
    }

}
