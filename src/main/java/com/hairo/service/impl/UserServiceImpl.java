package com.hairo.service.impl;

import com.hairo.annotations.Method;
import com.hairo.annotations.Resource;
import com.hairo.annotations.Service;
import com.hairo.service.UserService;

//@Service//这是自定义的注解Service,不是spring的
@Service("value 这是自定义的注解Service,不是spring的")
//@Service(name="name 这是自定义的注解Service,不是spring的")
public class UserServiceImpl implements UserService {

    @Resource("自定义value名称")
    public  Integer integer;
    @Resource("自定义name名称")
    private String name ;

    private UserServiceImpl(int i){}
    public UserServiceImpl(){}

    public UserServiceImpl(String name){
        this.name = name;
    }

    @Method("注解在getName（）方法上")
    @Override
    public String getName() {

        return this.name;
    }
    @Method(name="注解在show（）方法上")
    public void show(){
        System.out.println("name:"+name);
    }

    @Method(value="注解在show1（）方法上")
    private void show1(){
        System.out.println("show1方法");
    }
}
