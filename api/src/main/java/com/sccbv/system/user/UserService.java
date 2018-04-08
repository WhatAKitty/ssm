package com.sccbv.system.user;

import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.service.BusinessService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户业务层
 *
 * @author xuqiang
 * @date 2018/1/14
 */
@Service
public class UserService extends BusinessService<User, UserDTO> {

    @Autowired
    public UserService(UserMapper userMapper) {
        super(userMapper, User.class);
    }

    /**
     * 检查用户名是否存在
     *
     * @param userDTO 用户数据
     */
    public void checkExist(UserDTO userDTO, Long userId) {
        Example example = new Example(User.class);
        example.and().andEqualTo("username", userDTO.getUsername());

        if (userId != null) {
            example.and().andNotEqualTo("id", userId);
        }

        Asserts.isFalse(super.existsByExample(example, false), 400, "用户名已存在");
    }

    /**
     * 创建用户
     *
     * @return 用户信息
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Object create(UserDTO userDTO) {
        checkExist(userDTO, null);
        return super.create(userDTO, new Date());
    }

    /**
     * 修改用户
     *
     * @param userId  用户编号
     * @param userDTO 用户信息
     * @return 用户信息
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Object update(Long userId, UserDTO userDTO) {
        return super.update(userId, userDTO, false, new Date(), "username", "password");
    }

}
