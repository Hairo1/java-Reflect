package com.hairo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//运行期间可以获取
@Target(ElementType.FIELD)//只能在成员属性上使用注解
public @interface Resource {

    public String value() default "default";
    public String name() default "default";
}
