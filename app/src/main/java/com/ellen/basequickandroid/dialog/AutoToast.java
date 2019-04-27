package com.ellen.basequickandroid.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ellen.basequickandroid.R;
import com.ellen.basequickandroid.base.BaseToast;

public class AutoToast extends BaseToast {

    public AutoToast(Activity activity) {
        super(activity);
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.toast_layout,null);
        return view;
    }

    @Override
    protected int getDuration() {
        return Toast.LENGTH_LONG;
    }

    @Override
    protected void setToastGravity(Toast toast) {
        toast.setGravity(Gravity.BOTTOM,0,0);

    }
}
