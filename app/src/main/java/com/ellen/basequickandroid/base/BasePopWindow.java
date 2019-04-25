package com.ellen.basequickandroid.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.lang.ref.WeakReference;

public abstract class BasePopwindow {

    private PopupWindow popupWindow;
    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Context> contextWeakReference;
    //dismiss事件触发
    private OnDismissListener onDismissListener;

    public BasePopwindow(Activity activity, Context context){
        activityWeakReference = new WeakReference<>(activity);
        contextWeakReference = new WeakReference<>(context);
        View view = onCreateView();
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.initButterKnife(view);
        }
        popupWindow = new PopupWindow(view,setWidth(),setHeight(),isGetFocus());

        // 设置PopupWindow的背景
        if(isSetBackgroundDrawable()!= null && isSetBackgroundDrawable()) {
            popupWindow.setBackgroundDrawable(setBackgroundDrawable());
        }
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(isResponseOutsideTouchable());
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(isResponseTouchable());
        //设置其它，例如：可以通过这个方法完成虚拟键盘适配等
        setOtherSetting(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(onDismissListener != null){
                    onDismissListener.dissmiss();
                }
            }
        });
    }

    protected void onResume(){}

    protected void onstart(){}

    private void showBefore(){
        if(isSetShowBackgroundBlack()){
            WindowManager.LayoutParams lp = activityWeakReference.get().getWindow().getAttributes();
            lp.alpha = 0.3f;
            activityWeakReference.get().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            activityWeakReference.get().getWindow().setAttributes(lp);
        }
        onstart();
    }

    //显示在目标View的下方
    protected void showAsDropDown(View parentView){
        showBefore();
        popupWindow.showAsDropDown(parentView);
        onResume();
    }

    //显示在目标View的下方,并且加上偏移量
    public void showAsDropDown(View parentView,int xoff,int yoff){
        showBefore();
        popupWindow.showAsDropDown(parentView,xoff,yoff);
        onResume();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showAsDropDown(View parentView, int xoff, int yoff, int gravity){
        showBefore();
        popupWindow.showAsDropDown(parentView,xoff,yoff,gravity);
        onResume();
    }

    public void showAtLocation(View parentView,int gravity,int xoff,int yoff){
        showBefore();
        popupWindow.showAtLocation(parentView,gravity,xoff,yoff);
        onResume();
    }

    public void dismiss(){
        dismissBefore();
        //解除绑定
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.unBindButterKnife();
        }
        //如果设置了背景暗化，那么这里去除背景暗化
        if(isSetShowBackgroundBlack()){
            //去掉暗色背景
            WindowManager.LayoutParams lp = activityWeakReference.get().getWindow().getAttributes();
            lp.alpha = 1.0f;
            activityWeakReference.get().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            activityWeakReference.get().getWindow().setAttributes(lp);
        }
        popupWindow.dismiss();
    }

    //创建Popwindow的View
    protected abstract View onCreateView();
    //设置宽度
    protected abstract int setWidth();
    //设置高度
    protected abstract int setHeight();
    //是否获取焦点
    protected abstract boolean isGetFocus();
    //是否设置背景Drawable
    protected abstract Boolean isSetBackgroundDrawable();
    //设置背景Drawable
    protected abstract Drawable setBackgroundDrawable();
    //是否相应外部点击事件
    protected abstract boolean isResponseOutsideTouchable();
    //是否相应内部点击事件
    protected abstract boolean isResponseTouchable();
    //设置其它
    protected abstract void setOtherSetting(PopupWindow popupWindow);
    //设置背景暗化
    protected abstract Boolean isSetShowBackgroundBlack();
    //dismiss之前调用
    protected abstract void dismissBefore();

    public Activity getActivity(){
        return activityWeakReference.get();
    }

    public Context getContext(){
        return contextWeakReference.get();
    }

    public OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public interface ButterKnifeInterface{
        void initButterKnife(View view);
        void unBindButterKnife();
    }

    public interface OnDismissListener{
        void dissmiss();
    }

}
