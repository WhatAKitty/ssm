package com.sccbv.system.usersroles;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 用户角色验证注解
 *
 * @author yuhailun
 * @date 2018/01/25
 * @description
 **/
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = UsersRolesValidator.class)
@Documented
public @interface UsersRolesValidate {

    /**
     * 验证返回信息
     *
     * @return 验证信息
     */
    String message() default "";

    /**
     * 所属验证组
     *
     * @return 验证组类
     */
    Class<?>[] groups() default {};

    /**
     * 数据载体类
     *
     * @return 类列表
     */
    Class<? extends Payload>[] payload() default {};

}
