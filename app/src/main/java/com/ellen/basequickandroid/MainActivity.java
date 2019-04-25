package com.ellen.basequickandroid;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.ellen.basequickandroid.base.BaseActivity;
import com.ellen.basequickandroid.util.BaseLog;
import com.ellen.basequickandroid.util.ImageChooseUtils;
import com.ellen.basequickandroid.util.WebViewSetttingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BaseActivity.ButterKnifeInterface {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ImageChooseUtils imageChooseUtils;

    @OnClick(R.id.bt1)
    void onClick(View view){
        imageChooseUtils = new ImageChooseUtils(this, this, new ImageChooseUtils.ChooseImageCallback() {
            @Override
            public void successs(String path) {
                Log.e("选择的图片地址是",path);
            }

            @Override
            public void failure() {

            }
        });
        imageChooseUtils.toSystemAlbum(1);
    }

    @Override
    protected void setStatus() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,5);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
        stringList.add("8");
        stringList.add("9");
        stringList.add("10");
        stringList.add("11");
        stringList.add("12");
        recyclerView.setAdapter(new Adapter2(MainActivity.this));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageChooseUtils.onActivityResult(requestCode,resultCode,data);
    }
}
