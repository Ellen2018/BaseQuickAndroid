package com.ellen.basequickandroid;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.ellen.basequickandroid.base.BaseActivity;
import com.ellen.basequickandroid.base.BaseDialog;
import com.ellen.basequickandroid.dialog.SystemWaitDialog;
import com.ellen.basequickandroid.util.BaseLog;
import com.ellen.basequickandroid.util.ContentProviderUtils;
import com.ellen.basequickandroid.util.ImageChooseUtils;
import com.ellen.basequickandroid.util.PermissionUtils;
import com.ellen.basequickandroid.util.ToastUtils;
import com.ellen.basequickandroid.util.WebViewSetttingUtils;

import java.security.acl.Permission;
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

    private PermissionUtils permissionUtils;

    @Override
    protected void initView() {
        permissionUtils = new PermissionUtils(this,this);
       permissionUtils.startCheckFileReadWritePermission(1, new PermissionUtils.PermissionCallback() {
           @Override
           public void success() {
               List<ContentProviderUtils.Music> musicList = ContentProviderUtils.getMusicPathList(MainActivity.this);
               for(ContentProviderUtils.Music music:musicList){
                   Log.e("音频地址",music.getPath());
               }
           }

           @Override
           public void failure() {

           }
       });

        SystemWaitDialog systemWaitDialog = new SystemWaitDialog(this);
        systemWaitDialog.setOnDismissListener(new BaseDialog.OnDismissListener() {
            @Override
            public void dismiss() {
                ToastUtils.toast(MainActivity.this,"对话框消失了");
            }
        });
        systemWaitDialog.show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
