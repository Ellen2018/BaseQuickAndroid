package com.ellen.basequickandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.initButterKnife(view);
        }
        initView();
        initData();
        return view;
    }

    protected String getTAG(){
        return getClass().getSimpleName();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.unBindButterKnife();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            if(this instanceof LazyLoadInterface){
                LazyLoadInterface lazyLoadInterface = (LazyLoadInterface) this;
                lazyLoadInterface.lazyLoad();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    protected abstract void initData();
    protected abstract void initView();
    protected abstract int setLayout();

    //支持ButterKnife的接口
    public interface ButterKnifeInterface {
        void initButterKnife(View view);
        void unBindButterKnife();
    }

    public interface LazyLoadInterface{
        void lazyLoad();
    }
}
