package org.king2.aoppractice.annotation;

import java.lang.annotation.*;

/**
 * 描述: 定义权限访问注解
 * @author 刘梓江
 * @Date 2021/2/25 16:34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value= ElementType.METHOD)
public @interface Permissions {

    //将权限请求指定给目标模块信息
    String[] targetModule() default { };
}
