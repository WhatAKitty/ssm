package com.sccbv.system.rolespermissions;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义验证注解
 *
 * @author yuhailun
 * @date 2018/01/24
 * @description
 **/
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = RolesPermissionsValidator.class)
@Documented
public @interface RolesPermissionsValidate {

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
