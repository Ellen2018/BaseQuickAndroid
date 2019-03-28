package com.ellen.basequickandroid.base;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.lang.ref.WeakReference;

/**
 * 适配8.0 & 自定义通知 & 适配各种型号手机
 */
public abstract class BaseNotification {

    private NotificationManager notificationManager;
    private WeakReference<Context> contextWeakReference;
    private Notification notification;

    //通知id
    private int notificationId;
    //点击通知栏的意图
    private Intent notificationIntent;
    //通知cancel事件
    private OnNotificationCancelListener onNotificationCancelListener;
    //是否通知显示了
    private boolean isShowNotificaiton = false;

    public boolean isShowNotificaiton(){
        return isShowNotificaiton;
    }

    public OnNotificationCancelListener getOnNotificationCancelListener() {
        return onNotificationCancelListener;
    }

    public void setOnNotificationCancelListener(OnNotificationCancelListener onNotificationCancelListener) {
        this.onNotificationCancelListener = onNotificationCancelListener;
    }

    public Context getContext(){
        return contextWeakReference.get();
    }

    public Intent getNotificationIntent() {
        return notificationIntent;
    }

    public void setNotificationIntent(Intent notificationIntent) {
        this.notificationIntent = notificationIntent;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public BaseNotification(Context context) {
        contextWeakReference = new WeakReference<>(context);
        notificationManager = (NotificationManager) contextWeakReference.get().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationId = setNotificationId();
        notificationIntent = setNotificationIntent();
    }

    public void showNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //适配8.0以上的通知
            NotificationChannel channel = new NotificationChannel(String.valueOf(notificationId), setChannelName(), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            Notification.Builder build = new Notification.Builder(contextWeakReference.get(), String.valueOf(notificationId));
            build.setSmallIcon(setIconResourecId())
                    .setTicker(setTicker())
                    .setContentTitle(setTitle())
                    .setContentText(titleContent());
            if (isAutoCancel() != null) {
                if(isAutoCancel()) {
                    build.setAutoCancel(true);
                }
            }
            notification = build.build();
        } else {
            //8.0以下执行这里
            notification = new Notification();
            notification.tickerText = setTicker();
            if (isAutoCancel() != null) {
                if (isAutoCancel()) {
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                }
            }
            notification.icon = setIconResourecId();
            isShowNotificaiton = true;
        }
        RemoteViews remoteViews = new RemoteViews(contextWeakReference.get().getPackageName(), setNotificationLayoutId());
        initView(remoteViews);
        notification.contentView = remoteViews;
        //封装一个Intent
        PendingIntent resultPendingIntent = PendingIntent.getActivity(contextWeakReference.get(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置通知主题的意图
        notification.contentIntent = resultPendingIntent;
        notificationManager.notify(notificationId, notification);
    }

    public void cancelNotification(){
        cancelBefore();
        notificationManager.cancel(notificationId);
        if(onNotificationCancelListener != null){
            onNotificationCancelListener.cancel();
        }
        isShowNotificaiton = false;
    }

    protected abstract int setNotificationLayoutId();
    protected abstract String setTicker();
    protected abstract void initView(RemoteViews remoteViews);
    protected abstract int setIconResourecId();
    protected abstract Boolean isAutoCancel();
    protected abstract String setChannelName();
    protected abstract String setTitle();
    protected abstract String titleContent();
    protected abstract int setNotificationId();
    protected abstract Intent setNotificationIntent();
    protected abstract void cancelBefore();

    public interface OnNotificationCancelListener{
        void cancel();
    }

}
