package com.ellen.example.Java23_Design_Patterns.SingleInstace;

/**
 * 懒汉单例模式，容易出现线程安全问题
 */
public class LazybonesModeSingleInstace {

    private static LazybonesModeSingleInstace singleInstace = null;

    private LazybonesModeSingleInstace(){}


    public static LazybonesModeSingleInstace getInstace(){
        if(singleInstace == null) {
            //当获取单例的时候才进行创建
            singleInstace = new LazybonesModeSingleInstace();
        }
        return singleInstace;
    }

}
