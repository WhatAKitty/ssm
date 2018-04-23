package com.sccbv.system.user;

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
 * 用户业务层
 *
 * @date 2018/04/12
 * @description
 **/
@Service
public class UserService extends BusinessService<User, UserDTO> {

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    @Autowired
    public UserService(UserMapper mapper) {
        super(mapper, User.class);
    }

    /**
     * 检查用户是否有效
     *
     * @param userDTO   用户信息
     * @param excludeId 排除的用户
     */
    public void valid(UserDTO userDTO, Long excludeId) {
        Example example = Example.builder(User.class).build();
        example.and().andLike("username", userDTO.getUsername());

        if (excludeId != null) {
            example.and().andNotEqualTo("id", excludeId);
        }
        Asserts.isFalse(existsByExample(example, false), 400, "已经存在相同的用户");
    }

    /**
     * 查询用户
     *
     * @param pageable 分页信息
     * @param userDTO  条件载体
     * @param isPage   是否允许分页
     * @param force    是否包含被软删除的记录
     * @return 查询结果
     */
    public Object search(Pageable pageable, UserDTO userDTO, boolean isPage, boolean force) {
        Example example = new Example(User.class);
        if (StringUtils.isNotBlank(userDTO.getName())) {
            example.and().andLike("name", userDTO.getName() + "%");
        }
        if (StringUtils.isNotBlank(userDTO.getUsername())) {
            example.and().andLike("username", userDTO.getUsername() + "%");
        }
        String orderBy = OrderByUtils.getOrderBy(pageable.getSort(), ImmutableMap.of(
            "id", "id",
            "username", "convert(username using gbk)",
            "password", "convert(password using gbk)",
            "name", "convert(name using gbk)"
        ));
        if (StringUtils.isNotBlank(orderBy)) {
            example.setOrderByClause(orderBy);
        }
        return super.pageByExample(pageable, example, isPage, force);
    }

    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 创建的用户
     */
    public User create(UserDTO userDTO) {
        valid(userDTO, null);
        userDTO.setIsExpired(false);
        userDTO.setIsLocked(false);
        return super.create(userDTO, new Date());
    }

    /**
     * 更新用户
     *
     * @param userId  用户编号
     * @param userDTO 用户信息
     * @return 更新后的用户
     */
    public User update(Long userId, UserDTO userDTO) {
        valid(userDTO, userId);
        return super.update(userId, userDTO, false);
    }

}
