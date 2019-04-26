package com.ellen.example.LibraryUseDemo.RxJava;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;
import static java.lang.Thread.sleep;

public class RxJavaDemo {



//        Observable.create(new ObservableOnSubscribe<Drawable>() {
//            @Override
//            public void subscribe(ObservableEmitter<Drawable> emitter) throws Exception {
//                for (int i=0;i<drawableRes.length;i++){
//                    Drawable drawable=getResources().getDrawable(drawableRes[i]);
//                    //第6个图片延时3秒后架子
//                    if (i==5){
//                        sleep(3000);
//                    }
//                    //复制第7张图片到sd卡
//                    if (i==6){
//                        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
//                        saveBitmap(bitmap,"test.png", Bitmap.CompressFormat.PNG);
//                    }
//                    //上传到网络
//                    if (i==7){
//                        updateIcon(drawable);
//                    }
//                    emitter.onNext(drawable);
//                }
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Drawable>() {
//                    @Override
//                    public void accept(Drawable drawable) throws Exception {
//                        //回调后在UI界面上展示出来
//                    }
//                });

}
