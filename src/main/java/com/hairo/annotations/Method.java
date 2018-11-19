package com.hairo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称： JavaReflectTest
 * 作 者   ： Hairo
 * 创建时间: 2018/11/19 14:55
 * 作用描述:
 */
@Retention(RetentionPolicy.RUNTIME)//运行期间可以获取
@Target(ElementType.METHOD)//只能在方法上使用注解
public @interface Method {
    public String value() default "默认值";
    public String name() default "默认值";
}
