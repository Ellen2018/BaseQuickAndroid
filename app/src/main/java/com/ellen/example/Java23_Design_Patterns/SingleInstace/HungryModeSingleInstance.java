package com.ellen.example.Java23_Design_Patterns.SingleInstace;

/**
 * 饿汉式单例模式,线程安全的
 */
public class HungryModeSingleInstance {

    //在项目运行是就存在单例对象
    private static HungryModeSingleInstance singleInstance = new HungryModeSingleInstance();

    private HungryModeSingleInstance(){

    }

    public static HungryModeSingleInstance getInstance(){
        return singleInstance;
    }

}
