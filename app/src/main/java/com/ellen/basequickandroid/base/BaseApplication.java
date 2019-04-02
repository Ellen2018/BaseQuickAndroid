package com.ellen.basequickandroid.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 为什么要基化此类？因为需要进行一些应用全局配置
 * 例如：统计应用的Activity工作时间,Activity的启动次数等等
 */
public abstract class BaseApplication extends Application {

    private AppManager appManager;

    private AppActivityLifecycleCallbacks appActivityLifecycleCallbacks;
    public static WeakReference<BaseApplication> baseApplicationWeakReference;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化库 & SDK
        baseApplicationWeakReference = new WeakReference<>(this);
        initLibraySetting();
        if (isListenerActivity()) {
            appManager = new AppManager();
            super.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }else {
            if(appActivityLifecycleCallbacks != null){
                super.registerActivityLifecycleCallbacks(appActivityLifecycleCallbacks);
            }
        }
    }

    public AppManager getAppManager(){
        return baseApplicationWeakReference.get().appManager;
    }

    public ActivityManger getActivityManager(Activity activity){
        if(isListenerActivity()) {
            return getAppManager().appManagerMap.get(activity.getPackageName() + activity.getClass().getName());
        }else {
            Log.e("您没有开启Activity监控","ok");
            return null;
        }
    }

    //获取某个Activity的工作记录
    public Map<String, List<Long>> getActivityWorkRecord(Activity activity){
        if(isListenerActivity()){
            ActivityManger activityManger = getActivityManager(activity);
            if(activityManger != null) {
                return activityManger.timeMap;
            }else {
                return null;
            }
        } else {
            Log.e("您没有开启Activity监控","ok");
            return null;
        }
    }

    //是否监听用户Activity相关行为
    protected abstract Boolean isListenerActivity();
    protected abstract void initLibraySetting();

    public void registerAppActivityLifecycleCallbacks(AppActivityLifecycleCallbacks appActivityLifecycleCallbacks) {
        this.appActivityLifecycleCallbacks = appActivityLifecycleCallbacks;
    }

    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
              statistics(activity.getPackageName(),activity.getClass().getName(),ActivityManger.ACTIVITY_ON_CREATE,System.currentTimeMillis());
              if(appActivityLifecycleCallbacks != null) {
                  appActivityLifecycleCallbacks.onActivityCreated(activity,savedInstanceState);
              }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            statistics(activity.getPackageName(),activity.getClass().getName(),ActivityManger.ACTIVITY_ON_START,System.currentTimeMillis());
            if(appActivityLifecycleCallbacks != null) {
                appActivityLifecycleCallbacks.onActivityStarted(activity);
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            statistics(activity.getPackageName(),activity.getClass().getName(),ActivityManger.ACTIVITY_ON_RESUME,System.currentTimeMillis());
            if(appActivityLifecycleCallbacks != null) {
                appActivityLifecycleCallbacks.onActivityResumed(activity);
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            statistics(activity.getPackageName(),activity.getClass().getName(),ActivityManger.ACTIVITY_ON_PAUSE,System.currentTimeMillis());
            if(appActivityLifecycleCallbacks != null) {
                appActivityLifecycleCallbacks.onActivityPaused(activity);
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            statistics(activity.getPackageName(),activity.getClass().getName(),ActivityManger.ACTIVITY_ON_STOP,System.currentTimeMillis());
            if(appActivityLifecycleCallbacks != null) {
                appActivityLifecycleCallbacks.onActivityStopped(activity);
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            statistics(activity.getPackageName(),activity.getClass().getName(),ActivityManger.ACTIVITY_ON_DESTORY,System.currentTimeMillis());
            if(appActivityLifecycleCallbacks != null) {
                appActivityLifecycleCallbacks.onActivityDestroyed(activity);
            }
        }

        private void statistics(String packageName,String className,String lifeName,long time){
            boolean isHaveThisActivity = appManager.appManagerMap.containsKey(packageName+className);
            if(isHaveThisActivity){
                ActivityManger activityManger = appManager.appManagerMap.get(packageName+className);
                activityManger.statistics(lifeName,time);
            }else {
                ActivityManger activityManger = new ActivityManger(packageName,className);
                activityManger.statistics(lifeName,time);
                appManager.appManagerMap.put(packageName+className,activityManger);
            }
        }
    };

    public static class AppManager {

        private Map<String, ActivityManger> appManagerMap;

        public Map<String, ActivityManger> getAppManagerMap() {
            return appManagerMap;
        }

        public void setAppManagerMap(Map<String, ActivityManger> appManagerMap) {
            this.appManagerMap = appManagerMap;
        }

        public AppManager(){
            appManagerMap = new HashMap<>();
        }

    }

    public static class ActivityManger {

        public static final String ACTIVITY_ON_CREATE = "activity_on_create";
        public static final String ACTIVITY_ON_START = "activity_on_start";
        public static final String ACTIVITY_ON_RESUME = "activity_on_resume";
        public static final String ACTIVITY_ON_PAUSE = "activity_on_pause";
        public static final String ACTIVITY_ON_STOP = "activity_on_stop";
        public static final String ACTIVITY_ON_RESTART = "activity_on_restart";
        public static final String ACTIVITY_ON_DESTORY = "activity_on_destory";

        public ActivityManger(String packageName,String name) {
            this.name = name;
            this.packageName = packageName;
        }

        //名字
        private String name;
        //包名
        private String packageName;
        //执行onCreate的次数
        private int createTimes;
        //执行onStart的次数
        private int startTimes;
        //执行onResume的次数
        private int resumeTimes;
        //执行onPause的次数
        private int pauseTimes;
        //执行onStop的次数
        private int stopTimes;
        //执行onRestart的次数
        private int restartTimes;
        //执行onDestory的次数
        private int destoryTimes;
        //统计函数调用的时间轴
        private Map<String, List<Long>> timeMap;
        //记录工作时间
        private long workTime = 0;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public int getCreateTimes() {
            return createTimes;
        }

        public void setCreateTimes(int createTimes) {
            this.createTimes = createTimes;
        }

        public int getStartTimes() {
            return startTimes;
        }

        public void setStartTimes(int startTimes) {
            this.startTimes = startTimes;
        }

        public int getResumeTimes() {
            return resumeTimes;
        }

        public void setResumeTimes(int resumeTimes) {
            this.resumeTimes = resumeTimes;
        }

        public int getPauseTimes() {
            return pauseTimes;
        }

        public void setPauseTimes(int pauseTimes) {
            this.pauseTimes = pauseTimes;
        }

        public int getStopTimes() {
            return stopTimes;
        }

        public void setStopTimes(int stopTimes) {
            this.stopTimes = stopTimes;
        }

        public int getRestartTimes() {
            return restartTimes;
        }

        public void setRestartTimes(int restartTimes) {
            this.restartTimes = restartTimes;
        }

        public int getDestoryTimes() {
            return destoryTimes;
        }

        public void setDestoryTimes(int destoryTimes) {
            this.destoryTimes = destoryTimes;
        }

        public Map<String, List<Long>> getTimeMap() {
            return timeMap;
        }

        public void setTimeMap(Map<String, List<Long>> timeMap) {
            this.timeMap = timeMap;
        }

        public void statistics(String lifeName, long time){
            if(timeMap == null){
                timeMap = new HashMap<>();
            }
            switch (lifeName){
                case ACTIVITY_ON_CREATE:
                    createTimes++;
                    break;
                case ACTIVITY_ON_START:
                    startTimes++;
                    break;
                case ACTIVITY_ON_RESUME:
                    resumeTimes++;
                    break;
                case ACTIVITY_ON_PAUSE:
                    pauseTimes++;
                    break;
                case ACTIVITY_ON_STOP:
                    stopTimes++;
                    break;
                case ACTIVITY_ON_RESTART:
                    restartTimes++;
                    break;
                case ACTIVITY_ON_DESTORY:
                    destoryTimes++;
                    break;
            }
            if(timeMap.get(lifeName) == null){
                ArrayList<Long> timeArrayList = new ArrayList<>();
                timeArrayList.add(time);
                timeMap.put(lifeName,timeArrayList);
            }else {
                List<Long> timeArrayList = timeMap.get(lifeName);
                timeArrayList.add(time);
            }
            Log.e("用户操作",packageName+"_"+name+"-->"+lifeName+":"+time);
        }

    }

    public interface AppActivityLifecycleCallbacks extends ActivityLifecycleCallbacks{};
}
