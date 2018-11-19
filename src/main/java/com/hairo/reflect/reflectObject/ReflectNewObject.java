package com.hairo.reflect.reflectObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 项目名称： JavaReflectTest
 * 作 者   ： Hairo
 * 创建时间: 2018/11/17 21:12
 * 作用描述:
 * <p>
 * 通过反射实例化对象
 *

 */
@SuppressWarnings("all")
public class ReflectNewObject {

    private final String  calssPathName;
    public ReflectNewObject(String calssPathName){
        this.calssPathName = calssPathName;
    }


    //测试
    public static void main(String[] args) {
        ReflectNewObject newObject = new ReflectNewObject("com.hairo.service.impl.UserServiceImpl");
        //给定类路径进行反射
        System.out.println("\n----------------------------------------------------无参构造反射----------------------------------------------------");
        newObject.NewObject();

        System.out.println("\n----------------------------------------------------带参构造反射----------------------------------------------------");
        newObject.constructorByParameter();

        System.out.println("\n----------------------------------------------------反射类的所有公共构造方法----------------------------------------------------");
        newObject.getPublicConstructors();

        System.out.println("\n----------------------------------------------------反射类的所有公共构造方法(包含私有)----------------------------------------------------");
        newObject.getDeclaredConstructors();

        System.out.println("\n----------------------------------------------------反射类的所有公共属性----------------------------------------------------");
        newObject.getFiFields();

        System.out.println("\n----------------------------------------------------反射类的所有公共属性包含私有----------------------------------------------------");
        newObject.getDeclaredFields();

        System.out.println("\n----------------------------------------------------反射类的所有公共方法包含父类的公共方法----------------------------------------------------");
        newObject.getMethods();

        System.out.println("\n----------------------------------------------------反射类的所有公共方法-----不包含父类----的方法包含私有的----------------------------------------------------");
        newObject.getDeclaredMethod();

        System.out.println("\n----------------------------------------------------反射类的所有注解方法---------------------------------------------------");
        newObject.getAnnotation();
    }


    /**
     * 通过classPath反射创建对象
     *
     * @param classPath 类的完整路径 如：com.包名.包名.包名.类名
     */
    public void NewObject() {
        if (calssPathName == null && calssPathName.equals("")) {
            new NullPointerException();
        }
        try {
            /*
             *根据类对象与给定字符串的类或接口的名称创建对象
             * Class<?> 这个问号指的是你不知道反射后的类型是所以使用泛型
             * 也可以指定类型Class<User>
             *      Class<User> clazz = (Class<User>) Class.forName(classPath);
             *  这样直接固定死类型明显不够灵活
             */
            Class<?> clazz = Class.forName(calssPathName);
            //创建实例，无参构造方法
            Object o = clazz.newInstance();
            System.out.println("通过无参构造方法反射实例化:"+o.toString());//结果:com.hairo.service.impl.UserServiceImpl@1540e19d

        } catch (ClassNotFoundException e) {
            System.out.println(calssPathName + "找不到");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过反射实例化带参构造方法
     */
    public void constructorByParameter(){
        try {

            Class<?>   clazz = Class.forName(calssPathName);
            //获取String类型参数的构造方法
            Constructor<?> constructor = clazz.getConstructor(String.class);
            //反射实例化带String类型参数的构造方法
            Object o = constructor.newInstance("Hairo");
            System.out.println("通过带参构造方法反射实例化:"+o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据类路径反射获取所有公共(public)的构造方法
     * 也可以获取指定的构造方法(public的):
     * getConstructor(类<?>... parameterTypes)返回一个 Constructor指定对象,反映了公共类的构造函数由这个 类对象表示。
     *
     * @param classPath
     */
    public void getPublicConstructors(){
        try {
            Class<?>   clazz = Class.forName(calssPathName);
            Constructor<?>[] constructors = clazz.getConstructors();
            for(Constructor constructor : constructors){
                System.out.println(calssPathName+"类的构造方法:"+constructor.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据类路径反射获取所有构造方法(包含私有)
     * 获取指定的构造方法 (包含私有) :
     * getDeclaredConstructor(类<?>... parameterTypes) 返回一个 Constructor对象,反映了构造函数指定这个 类对象所表示的类或接口。
     *
     * @param classPath
     */
    public void getDeclaredConstructors(){
        try {
            Class<?>   clazz = Class.forName(calssPathName);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for(Constructor constructor : constructors){
                System.out.println(calssPathName+"类的public构造方法:"+constructor.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定类的所有public属性
     * 也可以指定类型获取
     */
    public void getFiFields(){
        try {
            Class<?> clazz = Class.forName(calssPathName);
            Field[] fields = clazz.getFields();
            for(Field field : fields){
                System.out.println(calssPathName+"类的公共属性:"+field.getName()+"  属性类型:"+field.getType());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取指定类的所有属性包括私有
     * 也可以指定类型获取
     */
    public void getDeclaredFields(){
        try {
            Class<?> clazz = Class.forName(calssPathName);
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                System.out.println(calssPathName+"类的属性:"+field.getName()+"  属性类型:"+field.getType());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取指定类的所有public方法包含父类的
     * 也可以指定获取
     */
    public void getMethods(){
        try {
            Class<?> clazz = Class.forName(calssPathName);
            Method[] methods = clazz.getMethods();
            for(Method method : methods){
                System.out.println(calssPathName+"类的公共方法:"+method.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取指定类的所有属性包括私有，
     * 也可以指定获取
     */
    public void getDeclaredMethod(){
        try {
            Class<?> clazz = Class.forName(calssPathName);
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                System.out.println(calssPathName+"类的方法:"+method.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类上的所有注解
     * 可以指定是否存在该注解---getAnnotation(类<A> annotationClass)
     */
    public void getAnnotation(){
        try {
            Class<?> clazz = Class.forName(calssPathName);
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations){
                System.out.println(calssPathName+"类的注解:"+annotations.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *其他的api
     * Calss<?>[] getClasses()
     * 返回一个数组,其中包含 类对象代表所有的公共类和接口这个 类对象所表示的类的成员。
     * Class<?>[] getInterfaces()
     * 决定了接口由这个对象所表示的类或接口实现。
     *int getModifiers()
     * 返回Java语言修饰符的类或接口,编码在一个整数。
     * String getName()
     * 返回的实体的名称(数组类,接口,类、原始类型或空白)由这个 类对象,作为 String。
     * Package getPackage()
     * 这个类的包。
     * ProtectionDomain getProtectionDomain()
     * 返回该类的 ProtectionDomain。
     * URL getResource(String name)
     * 发现与一个给定名称的资源。
     * InputStream getResourceAsStream(String name)
     * 发现与一个给定名称的资源。
     * Object[] getSigners()
     * 这个类的签名者。
     * String getSimpleName()
     * 返回底层类的简单的名称在源代码中给出。
     * Class<? super T> getSuperclass()
     * 返回 类代表实体的超类(类,接口,原始类型或空白)由这 类表示。
     * String getTypeName()
     * 返回一个字符串信息为这种类型的名称。
     * TypeVariable<类<T>>[] getTypeParameters()
     * TypeVariable对象返回一个数组表示泛型声明的变量类型声明由这个 GenericDeclaration对象,按声明顺序。
     * boolean isAnnotation()
     * 返回true,如果这 类对象代表一个注释类型。
     * boolean isAnnotationPresent(类<? extends Annotation> annotationClass)
     * 返回true,如果指定类型的注释出现在这个元素,其他的错误的。
     * boolean isAnonymousClass()
     * 返回 true当且仅当底层类是一个匿名类。
     * boolean isArray()
     * 决定如果这 类对象表示一个数组类。
     * boolean isAssignableFrom(类<?> cls)
     * 确定这个 类对象所表示的类或接口是一样的,或者是一个超类或超接口,所表示的类或接口指定 类参数。
     * boolean isEnum()
     * 返回true当且仅当这个类声明为enum的源代码。
     * boolean isInstance(Object obj)
     * 确定指定的 Object型态与对象由这 类表示。
     * boolean isInterface()
     * 确定指定的 类对象表示一个接口类型。
     * boolean isLocalClass()
     * 返回 true当且仅当底层类是当地的一个类。
     * boolean isMemberClass()
     * 返回 true当且仅当底层类是一个类成员。
     * boolean isPrimitive()
     * 确定如果指定的 类对象代表一个原始类型。
     * boolean isSynthetic()
     * 返回 true如果这类是一个合成类;否则返回 false
     * T newInstance()
     * 创建一个新的这个 类对象所表示的类的实例。
     * String toGenericString()
     * 返回一个字符串描述这个 类,包括修饰符和类型参数信息。
     * String toString()
     * 将对象转换为一个字符串。
     *
     */


}
