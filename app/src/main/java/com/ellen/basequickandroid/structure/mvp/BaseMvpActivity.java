package com.ellen.basequickandroid.structure.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ellen.basequickandroid.base.BaseActivity;
import com.ellen.basequickandroid.structure.mvp.basemvp.BasePresenter;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected  P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMVP();
    }

    protected abstract void initMVP();
}
