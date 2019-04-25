package com.ellen.basequickandroid.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    //管理Activity的
    private static List<Activity> activityList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatus();
        setContentView(setLayoutId());
        //是否支持ButterKnife接口
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.initButterKnife();
        }
        initView();
        initData();
        //横竖屏设置
        if(isSetVerticalScreen() != null){
            if(isSetVerticalScreen()){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }
        activityList.add(this);
    }

    @Override
    public void finish() {
        super.finish();
    }

    //设置状态栏
    protected abstract void setStatus();
    //设置布局id
    protected abstract int setLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    //Activity销毁时回调
    protected abstract void destory();
    //设置横竖屏,null->跟随系统,true->横屏,false->竖屏
    protected abstract Boolean isSetVerticalScreen();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityList.remove(this);
        destory();
    }

    public void quitApp(){
        for(Activity activity:activityList){
            activity.finish();
        }
    }

    //支持ButterKnife的接口
    public interface ButterKnifeInterface {
        void initButterKnife();
    }

}
