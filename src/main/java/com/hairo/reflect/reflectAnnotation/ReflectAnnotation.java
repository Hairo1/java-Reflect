package com.hairo.reflect.reflectAnnotation;

import com.hairo.annotations.Resource;
import com.hairo.annotations.Service;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 项目名称： JavaReflectTest
 * 作 者   ： Hairo
 * 创建时间: 2018/11/19 14:41
 * 作用描述:
 * 反射获取类,属性，方法的注解
 * 自定义注解作为示例
 */

@SuppressWarnings("all")//该注解在运行期间没有的,所以获取不到
public class ReflectAnnotation {

    private final String classPathName;//类路径

    public ReflectAnnotation(String classPathName) {
        this.classPathName = classPathName;
    }


    /**
     * 获取类注解
     */
    public void getClassAnnotation(){
        try {
            Class clazz = Class.forName(classPathName);
            //获取类上的所有运行期间有效的注解
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations){
                System.out.println(classPathName+"类存在注解:"+annotation);
                //判断类上是否存在某个注解
                System.out.println("是否存在@Service注解:"+clazz.isAnnotationPresent(Service.class));
                if(clazz.isAnnotationPresent(Service.class)){
                    //转换为该注解的类型
                    Service service = (Service) annotation;
                    //获取注解@Service  name设置的值
                    System.out.println("@Service的name值:"+service.name());
                    //获取注解@Service  value设置的值
                    System.out.println("@Service的value值:"+service.value());
                }
                //获取该注解的值

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性注解
     */
    public void getFieldAnnotation(){
        try {
            Class clazz = Class.forName(classPathName);
            //获取所有属性，包括private修饰的属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){

                //System.out.println(field.getAnnotation(Resource.class));
                //判断属性是否存在@Resource
                if(field.isAnnotationPresent(Resource.class)){
                    Resource resource = field.getAnnotation(Resource.class);
                    System.out.println(resource.name());
                    System.out.println(resource.value());
                    //这里就可以实现如何按指定名称/类名小写开头/类型。。。注入
                    //@service 没有指定
                    if(resource.name().equals("default")){
                        //默认按类型或者按类名小写开头注入
                     }else{
                        //@service("xxxx");
                        //默认按指定名称注入。。。。。
                    }

                }else{
                    //判断其他注解如:@Autowired主动注入
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取方法注解
     */
    public void getMethodAnnotation(){
        try {
            Class clazz = Class.forName(classPathName);
            //获取所有方法，包括private修饰的方法
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                System.out.println(method+"方法的注解"+method.getAnnotation(com.hairo.annotations.Method.class));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        ReflectAnnotation reflectAnnotation = new ReflectAnnotation("com.hairo.service.impl.UserServiceImpl");
        System.out.println("**************************分割线****************************");
        reflectAnnotation.getClassAnnotation();
        System.out.println("\n**************************分割线****************************");
        reflectAnnotation.getFieldAnnotation();
        System.out.println("\n**************************分割线****************************");
        reflectAnnotation.getMethodAnnotation();
    }
}
