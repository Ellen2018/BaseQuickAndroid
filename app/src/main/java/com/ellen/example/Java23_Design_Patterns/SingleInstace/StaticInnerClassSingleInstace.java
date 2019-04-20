package com.ellen.example.Java23_Design_Patterns.SingleInstace;

/**
 * 静态内部类的方式,线程安全的
 */
public class StaticInnerClassSingleInstace {


    // 私有构造
    private StaticInnerClassSingleInstace() {}

    // 静态内部类
    private static class InnerObject{
        private static StaticInnerClassSingleInstace single = new StaticInnerClassSingleInstace();
    }

    public static StaticInnerClassSingleInstace getInstance() {
        return InnerObject.single;
    }


}
