package com.ellen.basequickandroid.util;

import android.util.Log;

public class BaseLog {

    private static boolean isLog = false;

    public static boolean isIsLog() {
        return isLog;
    }

    public static void setIsLog(boolean isLog) {
        BaseLog.isLog = isLog;
    }

    public static void  e(String tag,String cotent){
        if(isIsLog()){
            Log.e(tag,cotent);
        }
    }

    public static void d(String tag,String content){
        if(isIsLog()){
            Log.d(tag,content);
        }
    }

    public static void i(String tag,String content){
        if(isIsLog()){
            Log.i(tag,content);
        }
    }

    public static void v(String tag,String content){
        if(isIsLog()){
            Log.v(tag,content);
        }
    }

    public static void w(String tag,String content){
        if(isIsLog()){
            Log.w(tag,content);
        }
    }

}
