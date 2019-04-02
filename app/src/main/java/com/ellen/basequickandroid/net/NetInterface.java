package com.ellen.basequickandroid.net;

public interface NetInterface {

    //网络切换至WiFi模式
    void switchWifi();
    //网络切换至流量模式
    void switchFlow();
    //网络切换至无网状态
    void switchNoNet();

}
