package com.sccbv.config.security;

import com.sccbv.system.permission.Permission;
import com.sccbv.system.role.Role;
import com.sccbv.system.rolespermissions.RolesPermissionsService;
import com.sccbv.system.user.User;
import com.sccbv.system.user.UserDTO;
import com.sccbv.system.user.UserService;
import com.sccbv.system.usersroles.UsersRolesService;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author xuqiang
 * @date 2018/1/14
 */
@Service
@Slf4j
public class MySQLUserDetailService implements UserDetailsService {

    private final UserService userService;
    private final UsersRolesService usersRolesService;
    private final RolesPermissionsService rolesPermissionsService;

    /**
     * 注入需要的对象
     *
     * @param userService             用户业务操作类
     * @param usersRolesService       用户角色业务操作类
     * @param rolesPermissionsService 角色权限业务操作类
     */
    @Autowired
    public MySQLUserDetailService(
        UserService userService,
        UsersRolesService usersRolesService,
        RolesPermissionsService rolesPermissionsService
    ) {
        this.userService = userService;
        this.usersRolesService = usersRolesService;
        this.rolesPermissionsService = rolesPermissionsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setIsEnabled(true);
        userDTO.setIsExpired(false);
        userDTO.setIsLocked(false);

        User user = userService.oneByDTO(userDTO, false);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // get roles and permissions
        Map<String, Object> userWithRoles = usersRolesService.byUserId(user.getId(), false);
        List<Role> roles = List.class.cast(userWithRoles.get("roles"));
        if (roles.isEmpty()) {
            return toOAuth2User(user, Collections.emptyList());
        }

        // fetch permissions
        final List<Long> roleIds = roles.parallelStream().map(role -> role.getId()).collect(Collectors.toList());
        final List<Permission> permissions = roleIds.isEmpty() ? new ArrayList<>() : rolesPermissionsService.byRoleIds(roleIds);

        final List<GrantedAuthority> authorities = new ArrayList<>();
        roles.parallelStream().forEach(role -> authorities.add(toGrantedRole(role)));
        permissions.parallelStream().forEach(permission -> authorities.add(toGrantedPermission(permission)));

        log.info("login success {}", userDTO);
        return toOAuth2User(user, authorities);
    }

    private GrantedAuthority toGrantedRole(Role role) {
        String code = String.format("ROLE_%s", role.getCode().toUpperCase());
        return new DetailedGrantedAuthority(code, role.getName(), null, role.getRemark());
    }

    private GrantedAuthority toGrantedPermission(Permission permission) {
        return new DetailedGrantedAuthority(
            permission.getCode(),
            permission.getName(),
            permission.getCategory(),
            permission.getRemark()
        );
    }

    /**
     * 转化为OAuth2类型的User
     *
     * @param authorities 授权
     * @return OAuth2类型User
     */
    private UserDetails toOAuth2User(User user, Collection<? extends GrantedAuthority> authorities) {
        return new ExtendUser(
            user.getName(),
            user.getUsername(),
            user.getPassword(),
            user.getIsEnabled(),
            !user.getIsExpired(),
            true,
            !user.getIsLocked(),
            authorities
        );
    }

}
