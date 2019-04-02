package com.ellen.basequickandroid.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.ref.WeakReference;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_WIFI;
import static android.net.ConnectivityManager.TYPE_MOBILE;

/**
 * 此类仅仅是提供网络监听的一种状态变化：WiFi状态 & 流量状态 & 无网状态
 */
public class NetManager {

    private WeakReference<Context> contextWeakReference;
    private NetworkChangeReceiver networkChangeReceiver;
    private final String NET_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private NetInterface netInterface;

    public NetManager(Context context){
        contextWeakReference = new WeakReference<>(context);
    }

    public void registerNetChangeReceiver(NetInterface netInterface){
        this.netInterface = netInterface;
        //开启广播的监听
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NET_ACTION);
        networkChangeReceiver = new NetworkChangeReceiver(this);
        contextWeakReference.get().registerReceiver(networkChangeReceiver, intentFilter);
    }

    public void unregisterNetChangerReceiver(){
        contextWeakReference.get().unregisterReceiver(networkChangeReceiver);
    }

    private static class NetworkChangeReceiver extends BroadcastReceiver {

        private WeakReference<NetManager> netManagerWeakReference;

        public NetworkChangeReceiver(NetManager netManager){
            netManagerWeakReference = new WeakReference<>(netManager);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) netManagerWeakReference.get()
                    .contextWeakReference.get().getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                switch (networkInfo.getType()) {
                    case TYPE_MOBILE:
                        netManagerWeakReference.get().netInterface.switchFlow();
                        break;
                    case TYPE_WIFI:
                        netManagerWeakReference.get().netInterface.switchWifi();
                        break;
                    default:
                        break;
                }
            } else {
                netManagerWeakReference.get().netInterface.switchNoNet();
            }
        }
    }

}
