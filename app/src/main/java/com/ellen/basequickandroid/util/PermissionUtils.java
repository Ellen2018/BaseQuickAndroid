package com.ellen.basequickandroid.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//权限申请工具类
public class PermissionUtils {

    private int requestCode;
    private List<String> permissionList;
    private WeakReference<Activity> weakReferenceActivity;
    private WeakReference<Context> weakReferenceContext;
    private PermissionCallback permissionCallback;

    public PermissionUtils(Activity activity, Context context){
        weakReferenceActivity = new WeakReference<>(activity);
        weakReferenceContext = new WeakReference<>(context);
    }

    /**
     *
     * @param permissionString
     * @return false：具有该权限
     */
    private boolean checkPermission(String permissionString){
        boolean falg = false;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(weakReferenceContext.get(), permissionString) != PackageManager.PERMISSION_GRANTED) {
               falg = true;
            }
        }else {
            falg = false;
        }
        return falg;
    }

    public void checkPermissions(String[] permissionArray,int requestCode,PermissionCallback permissionCallback){
        this.permissionList = Arrays.asList(permissionArray);
        this.requestCode = requestCode;
        this.permissionCallback = permissionCallback;
        boolean falg = false;
        for(String permissionString:permissionList){
            falg = checkPermission(permissionString);
            if(falg){
                break;
            }
        }

        //申请权限
        if(falg) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                weakReferenceActivity.get().requestPermissions(permissionArray,requestCode);
            }
        }else {
            this.permissionCallback.success();
        }
    }

    public void checkPermissions(List<String> permissionList,int requestCode,PermissionCallback permissionCallback){
        this.permissionList = permissionList;
        this.requestCode = requestCode;
        this.permissionCallback = permissionCallback;
        boolean falg = false;
        for(String permissionString:permissionList){
            falg = checkPermission(permissionString);
            if(falg){
                break;
            }
        }

        //申请权限
        if(falg) {
            String[] permissionArray = new String[permissionList.size()];
            permissionList.toArray(permissionArray);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                weakReferenceActivity.get().requestPermissions(permissionArray,requestCode);
            }
        }else {
            this.permissionCallback.success();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if(this.requestCode == requestCode){
           int sum = 0;
           for (int i = 0; i < permissions.length; i++) {
               if(grantResults[i] == 0){
                   sum++;
               }
           }
           if(sum == this.permissionList.size()){
               //成功
               permissionCallback.success();
           }else {
               //失败
               permissionCallback.failure();
           }
       }
    }

    //检测文件读写权限
    public void startCheckFileReadWritePermission(int requestCode,PermissionCallback permissionCallback){
        List<String> permissionList = new ArrayList<>();
        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        checkPermissions(permissionList,requestCode,permissionCallback);
    }


    public interface PermissionCallback{
        void success();
        void failure();
    }

}
