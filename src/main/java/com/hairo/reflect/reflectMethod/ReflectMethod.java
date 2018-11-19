package com.hairo.reflect.reflectMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 项目名称： JavaReflectTest
 * 作 者   ： Hairo
 * 创建时间: 2018/11/19 0:36
 * 作用描述:
 * 反射方法
 */

public class ReflectMethod {
    private final String classPathName;//类路径

    public ReflectMethod(String classPathName) {
        this.classPathName = classPathName;
    }

    /**
     * 反射获取所有方法并执行
     */
    public void getMethod(){
        try {

            Class<?> clazz = Class.forName(classPathName);
            Object o = clazz.newInstance();
            //获取所有方法并执行
            Method[] methods = clazz.getDeclaredMethods();
            //获取所有实例方法
            for (Method method : methods){
                //method.getTypeParameters();获取方法的参数列表---懒得写了
                System.out.println("执行方法:"+method);
                //开启权限去执行方法
                method.setAccessible(true);
                //方法参数为0个的时候执行
                if(method.getTypeParameters().length==0){
                    method.invoke(o);
                    System.out.println("");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        ReflectMethod reflectMethod = new ReflectMethod("com.hairo.service.impl.UserServiceImpl");
        reflectMethod.getMethod();
    }

}
