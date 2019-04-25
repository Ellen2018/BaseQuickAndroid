package com.ellen.basequickandroid.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ellen.basequickandroid.R;
import com.ellen.basequickandroid.base.BaseDialog;

public class SystemWaitDialog extends BaseDialog implements BaseDialog.ButterKnifeInterface {

    public SystemWaitDialog(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_wait,null);
        return view;
    }

    @Override
    protected void showBefore() {

    }

    @Override
    protected void onResume() {

    }

    @Override
    protected void dissmissBefore() {

    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean setCancelable() {
        return null;
    }

    @Override
    protected Boolean setCanceledOnTouchOutside() {
        return null;
    }

    @Override
    public void initButterKnife(View view) {

    }

    @Override
    public void unBindButterKnife() {

    }
}
