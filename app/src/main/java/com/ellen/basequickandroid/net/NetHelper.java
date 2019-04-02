package com.ellen.basequickandroid.net;

/**
 * 网络请求三步骤抽象封装
 *
 * 1.请求前：
 * 1.1 检测url是否规范 & 是否存在
 * 1.2 检测网络状态
 *   1.2.1 无网状态 ->提升用户，显示无网View,提供Retry机制
 *   1.2.2 流量状态 ->如果是视频音频，会提示用户，否则不会
 *   1.2.3 WiFi状态 ->不提示用户
 *
 * 2.请求中:
 * 2.1 如何抽象使用的库(OkHttp,Retrofit,Volley)?
 * 2.2 超时设置与处理(Callback接口)
 * 2.3 外码处理(分为细化模式&粗糙模式&自定义模式)
 *
 * 3.请求后：
 * 3.1 规定内码含义
 * 3.2 内码处理回调(便于修改 & 维护)
 *
 */
public abstract class NetHelper {

    //请求的网络地址
    private String url;
    private long connectTimeout = 0;
    private long readTimeout = 0;

    public NetHelper(String url,long connectTimeout,long readTimeout){
        this.url = url;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    //完成访问网络，并请求Json / XML数据
    public abstract String startRequest(String url,long connectTimeout,long readTimeout);

}
