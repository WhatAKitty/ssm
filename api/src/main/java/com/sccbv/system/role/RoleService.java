package com.sccbv.system.role;

import com.sccbv.system.permission.Permission;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.db.mybatis.SDelEntity;
import com.whatakitty.ssm.service.BusinessService;
import java.util.Date;
import java.util.List;
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

        Example example = Example.builder(Role.class).build();
        example.and().andEqualTo("name", roleDTO.getName());

        Asserts.isFalse(super.existsByExample(example, false), 400, "角色名称已存在");

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

        Example example = Example.builder(Role.class).build();
        example.and().andEqualTo("name", roleDTO.getName());
        example.and().andNotEqualTo("id", roleId);

        Asserts.isFalse(super.existsByExample(example, false), 400, "角色名称已存在");

        return update(roleId, roleDTO, false, date);
    }
}
