package com.sccbv.system.role;

import com.google.common.collect.ImmutableMap;
import com.sccbv.system.permission.Permission;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.db.mybatis.SDelEntity;
import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.service.BusinessService;
import com.whatakitty.ssm.utils.OrderByUtils;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 角色业务层
 *
 * @author xuqiang
 * @date 2018/01/09
 * @description
 **/
@Service
public class RoleService extends BusinessService<Role, RoleDTO> {

    private final RoleMapper roleMapper;

    /**
     * 角色业务层构造器
     *
     * @param roleMapper 角色数据库操作类
     */
    @Autowired
    public RoleService(RoleMapper roleMapper) {
        super(roleMapper, Role.class);
        this.roleMapper = roleMapper;
    }

    /**
     * 检查角色是否有效
     *
     * @param roleDTO 角色信息
     * @param excludeId   排除的角色
     */
    public void valid(RoleDTO roleDTO, Long excludeId) {
        Example example = Example.builder(Role.class).build();
        Example.Criteria ors = example.createCriteria()
            .orEqualTo("code", roleDTO.getCode())
            .orEqualTo("name", roleDTO.getName());
        example.and(ors);

        if (excludeId != null) {
            example.and().andNotEqualTo("id", excludeId);
        }
        Asserts.isFalse(existsByExample(example, false), 400, "已经存在相同的角色");
    }

    /**
     * 查询角色
     *
     * @param pageable 分页信息
     * @param roleDTO  条件载体
     * @param isPage   是否允许分页
     * @param force    是否包含被软删除的记录
     * @return 查询结果
     */
    public Object search(Pageable pageable, RoleDTO roleDTO, boolean isPage, boolean force) {
        Example example = new Example(Role.class);
        if (StringUtils.isNotBlank(roleDTO.getCode())) {
            example.and().andEqualTo("code", roleDTO.getCode());
        }
        if (StringUtils.isNotBlank(roleDTO.getName())) {
            example.and().andLike("name", roleDTO.getName() + "%");
        }
        String orderBy = OrderByUtils.getOrderBy(pageable.getSort(), ImmutableMap.of(
            "id", "id",
            "code", "convert(code using gbk)",
            "name", "convert(name using gbk)"
        ));
        if (StringUtils.isNotBlank(orderBy)) {
            example.setOrderByClause(orderBy);
        }
        return super.pageByExample(pageable, example, isPage, force);
    }

    /**
     * 查询某角色的所有权限
     *
     * @param roleId 角色编号
     * @param force  是否强制查询已被标记软删除的记录
     * @return 查询结果
     */
    public List<Permission> permissions(Long roleId, boolean force) {
        // check roleId exists
        Asserts.isFalse(existsByPrimaryKey(roleId, force), 404, String.format("编号为%s的角色不存在", roleId));

        return roleMapper.permissions(roleId, force ? null : SDelEntity.DEL_FALSE);
    }

    /**
     * 创建角色
     *
     * @param roleDTO 角色信息载体
     * @param date    时间
     * @return 是否创建成功
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    @Override
    public Role create(RoleDTO roleDTO, Date date) {
        valid(roleDTO, null);
        return super.create(roleDTO, date);
    }

    /**
     * 修改角色信息
     *
     * @param roleDTO 角色信息载体
     * @param roleId  角色编号
     * @param date    时间
     * @return 是否创建成功
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Role update(Long roleId, RoleDTO roleDTO, Date date) {
        valid(roleDTO, roleId);
        return update(roleId, roleDTO, false, date);
    }
}
