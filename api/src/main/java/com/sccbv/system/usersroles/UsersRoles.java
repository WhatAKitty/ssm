package com.sccbv.system.usersroles;

import com.whatakitty.ssm.db.mybatis.IdEntity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关系类
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_users_roles")
public class UsersRoles extends IdEntity {

    private Long userId;

    private Long roleId;

}
