package com.ellen.basequickandroid.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment extends DialogFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container);
        unbinder = ButterKnife.bind(this, view);
        if(setCancelable() != null) {
            this.setCancelable(setCancelable());
        }
        if(setCanceledOnTouchOutside() != null) {
            getDialog().setCanceledOnTouchOutside(setCanceledOnTouchOutside());
        }
        if(setWinowTransparent() != null && setWinowTransparent()) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        initView();
        initData();
        return view;
    }

    protected abstract void initData();
    protected abstract void initView();
    protected abstract int setLayout();
    protected abstract Boolean setCancelable();
    protected abstract Boolean setCanceledOnTouchOutside();
    protected abstract Boolean setWinowTransparent();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null) {
            unbinder.unbind();
        }
    }
}
