package com.hairo.reflect.reflecField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 项目名称： JavaReflectTest
 * 作 者   ： Hairo
 * 创建时间: 2018/11/18 23:23
 * 作用描述:
 * 通过反射实现成员变量设值
 */

public class ReflectField {
    private final String classPathName;//类路径
    
    public ReflectField(String classPathName) {
        this.classPathName = classPathName;
    }
    /**
     *赋值成员变量
     */
    public void setField() {
        try {
            Class clazz = Class.forName(this.classPathName);
            //创建类实例
            Object o =  clazz.newInstance();
            //获取指定属性名进行赋值  getField(name);--不能获取私有的属性
            Field field = clazz.getDeclaredField("name");
            //因为属性私有的所以必须开启权限才能操作
            field.setAccessible(true);
            //指定属性名赋值 k-v
            field.set(o, "反射设置私有name属性的值");
            //调用输出方法-输出null为赋值失败
            //指定方法名获取show方法
            Method method =clazz.getMethod("show");
            //调用show方法 o代表那个对象的show方法,
            method.invoke(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定类的所有成员属性
     */
    public void getField(){

        try {
            Class clazz = Class.forName(this.classPathName);
            //clazz.getFields();不包含私有属性
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                //获取私有属性不需要开启权限，修改才需要
                System.out.println(classPathName+"的属性:"+field.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReflectField reflectField = new ReflectField("com.hairo.service.impl.UserServiceImpl");
        reflectField.setField();
        reflectField.getField();
    }
}
