package com.ellen.basequickandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.ellen.basequickandroid.base.BaseActivity;
import com.ellen.basequickandroid.util.BaseLog;
import com.ellen.basequickandroid.util.WebViewSetttingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BaseActivity.ButterKnifeInterface {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void setStatus() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //加载百度首页
        WebViewSetttingUtils.loadUrl(webView,"https://www.baidu.com/");
        BaseLog.d("ss","dsadasd");
    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return null;
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }
}
