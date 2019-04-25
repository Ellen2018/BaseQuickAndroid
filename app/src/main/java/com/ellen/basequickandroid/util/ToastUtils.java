package com.ellen.basequickandroid.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void toast(Context context,String contetnt){
        Toast.makeText(context,contetnt,Toast.LENGTH_SHORT).show();
    }

}
