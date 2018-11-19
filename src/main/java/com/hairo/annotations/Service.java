package com.hairo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//运行期间可以获取
@Target(ElementType.TYPE)//只能在类上使用注解
public @interface Service {
    public String value() default "默认值";
    public String name() default "默认值";
}
