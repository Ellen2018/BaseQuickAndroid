package com.ellen.basequickandroid.util;

import android.webkit.WebView;

public class WebViewSetttingUtils {

    public static void loadUrl(WebView webView,String url){
        //通过WebView的WebSetting类设置能够执行JavaScript的脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

}
