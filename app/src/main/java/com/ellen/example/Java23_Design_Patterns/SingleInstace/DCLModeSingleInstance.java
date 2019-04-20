package com.ellen.example.Java23_Design_Patterns.SingleInstace;

/**
 * 双重检测锁机制，线程安全的
 */
public class DCLModeSingleInstance {

    private static volatile DCLModeSingleInstance singleInstance = null;

    private DCLModeSingleInstance(){

    }

    public static DCLModeSingleInstance getInstance(){
        if(singleInstance == null){
            synchronized (DCLModeSingleInstance.class){
                if(singleInstance == null){
                    singleInstance = new DCLModeSingleInstance();
                }
            }
        }
        return singleInstance;
    }

}
