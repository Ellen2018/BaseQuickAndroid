package com.ellen.basequickandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 *
 * 提示，在使用之前一定要获取文件读写权限。
 *
 * 手机图片选择工具类
 * 1.选择相册图片
 * 2.选择使用相机进行拍摄(适配Android N)
 *
 * 使用前请配置：
 * 【1】在AndroidManifest.xml中配置
 *  <provider
 *             android:name="android.support.v4.content.FileProvider"
 *             android:authorities="tr.mobileapp.imeditor.fileprovider"
 *             android:exported="false"
 *             android:grantUriPermissions="true">
 *             <meta-data
 *                 android:name="android.support.FILE_PROVIDER_PATHS"
 *                 android:resource="@xml/file_paths" />
 * </provider>
 *
 * 【2】在res下新建一个xml文件夹，并且配置一个file_paths.xml文件
 * <?xml version="1.0" encoding="utf-8"?>
 * <paths xmlns:android="http://schemas.android.com/apk/res/android">
 *     <!--"."表示所有路径-->
 *     <external-path name="external_files" path="."/>
 * </paths>
 */
public class ImageChooseUtils {

    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Context> contextWeakReference;
    private ChooseImageCallback chooseImageCallback;
    private int requestCode;
    private String chooseImagePath;
    private boolean isChooseSystemAlbum = false;

    public ImageChooseUtils(Context context,Activity activity,ChooseImageCallback chooseImageCallback){
        activityWeakReference = new WeakReference<>(activity);
        contextWeakReference = new WeakReference<>(context);
        this.chooseImageCallback = chooseImageCallback;
    }

    public void toCameraActivity(String imageName,String authority,int requestCode) {
        this.requestCode = requestCode;
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(contextWeakReference.get().getExternalCacheDir(), imageName + ".png");
        chooseImagePath = file.getAbsolutePath();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(contextWeakReference.get(), authority, file));
        } else {
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        activityWeakReference.get().startActivityForResult(openCameraIntent, requestCode);
    }

    public void toSystemAlbum(int requestCode){
        this.requestCode = requestCode;
        isChooseSystemAlbum = true;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        activityWeakReference.get().startActivityForResult(photoPickerIntent, requestCode);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(this.requestCode == requestCode){
            if(resultCode == Activity.RESULT_OK){
                if(isChooseSystemAlbum){
                    Uri uri = data.getData();
                    chooseImagePath = UriUtils.getRealFilePath(contextWeakReference.get(),uri);
                    chooseImageCallback.successs(chooseImagePath);
                }else {
                    chooseImageCallback.successs(chooseImagePath);
                }
            }else {
                chooseImageCallback.failure();
            }
        }
    }

    public interface ChooseImageCallback{
        void successs(String path);
        void failure();
    }

}
