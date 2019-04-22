package com.ellen.example.Java23_Design_Patterns.SingleInstace;

/**
 * 静态代码块单例模式，线程安全的，还有个枚举的，就不写了
 */
public class StaticCodePieceSingleInstace {

    private static StaticCodePieceSingleInstace singleInstace = null;

    static {
        singleInstace = new StaticCodePieceSingleInstace();
    }

    private StaticCodePieceSingleInstace(){

    }

    public static StaticCodePieceSingleInstace getInstance(){
        return singleInstace;
    }

}
