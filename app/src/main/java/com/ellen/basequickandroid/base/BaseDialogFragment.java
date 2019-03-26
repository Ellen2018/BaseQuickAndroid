package com.ellen.basequickandroid.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.initButterKnife(view);
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.unBindButterKnife();
        }
    }

    protected abstract void initData();
    protected abstract void initView();
    protected abstract int setLayout();
    protected abstract Boolean setCancelable();
    protected abstract Boolean setCanceledOnTouchOutside();
    protected abstract Boolean setWinowTransparent();

    //支持ButterKnife的接口
    public interface ButterKnifeInterface {
        void initButterKnife(View view);
        void unBindButterKnife();
    }

}
