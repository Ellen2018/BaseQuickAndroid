package com.ellen.basequickandroid.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * 自定义Toast基类，可支持任意的布局
 */
public abstract class BaseToast {

    private WeakReference<Activity> activityWeakReference;
    private MyBaseToast toast;
    private View toastView;
    private MyBaseToast.CancelListener cancelListener;

    public MyBaseToast.CancelListener getCancelListener() {
        return cancelListener;
    }

    public void setCancelListener(MyBaseToast.CancelListener cancelListener) {
        this.cancelListener = cancelListener;
        toast.setCancelListener(cancelListener);
    }

    public BaseToast(Activity activity){
        activityWeakReference = new WeakReference<>(activity);
        init();
    }

    private void init() {
        toastView = onCreateView();
        if(this instanceof ButterKnifeInterface){
            ButterKnifeInterface butterKnifeInterface = (ButterKnifeInterface) this;
            butterKnifeInterface.initButterKnife(toastView);
        }
        toast = new MyBaseToast(activityWeakReference.get());
        setToastGravity(toast);
        toast.setDuration(getDuration());
        toast.setView(toastView);
    }

    protected abstract View onCreateView();

    public void show(){
        toast.show();
    }

    public Context getContext(){
        return activityWeakReference.get();
    }

    protected abstract int getDuration();
    protected abstract void setToastGravity(Toast toast);

    public interface ButterKnifeInterface{
        void initButterKnife(View view);
        void unBindButterKnife();
    }

    public static class MyBaseToast extends Toast{

        private CancelListener cancelListener;

        public CancelListener getCancelListener() {
            return cancelListener;
        }

        public void setCancelListener(CancelListener cancelListener) {
            this.cancelListener = cancelListener;
        }

        /**
         * Construct an empty Toast object.  You must call {@link #setView} before you
         * can call {@link #show}.
         *
         * @param context The context to use.  Usually your {@link Application}
         *                or {@link Activity} object.
         */
        public MyBaseToast(Context context) {
            super(context);
        }

        @Override
        public void cancel() {
            super.cancel();
            if(cancelListener != null){
                cancelListener.cancel();
            }
        }

        public interface CancelListener{
            void cancel();
        }

    }

}
