package com.ellen.basequickandroid.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private WeakReference<Context> contextWeakReference;
    private List<T> dataList;

    public BaseRecyclerViewAdapter(Context context, List<T> dataList) {
        contextWeakReference = new WeakReference<>(context);
        this.dataList = dataList;
    }

    public Context getContext(){
        return contextWeakReference.get();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        if (this instanceof MultipleMode) {
            //多种item模式
            MultipleMode multipleMode = (MultipleMode) this;
            return (VH) multipleMode.getNewMultipleModeViewHolder(viewGroup,itemType);
        } else {
            //单种item模式
            SingleMode singleMode = (SingleMode) this;
            View view = LayoutInflater.from(getContext()).inflate(singleMode.getItemLayoutId(),null);
            return (VH) singleMode.getNewSingleModeViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(this instanceof MultipleMode){
            MultipleMode multipleMode = (MultipleMode) this;
            return multipleMode.getItemViewType(position);
        }else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {
        if(this instanceof MultipleMode){
           //多种item模式
            MultipleMode multipleMode = (MultipleMode) this;
            multipleMode.showData(vh,position);
        }else {
           //单种item模式
            SingleMode singleMode = (SingleMode) this;
            singleMode.showData(vh,position);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //单种Item模式
    public interface SingleMode<VH extends RecyclerView.ViewHolder>{
        //获取ViewHolder时回调
        RecyclerView.ViewHolder getNewSingleModeViewHolder(View view);
        //获取item layout id 的时候回调
        int getItemLayoutId();
        //显示数据的时候回调
        void showData(VH vh,int position);
    }

    //多种Item模式
    public interface MultipleMode<VH extends RecyclerView.ViewHolder>{
       //决定什么位置返回什么类型的item
       int getItemViewType(int position);
       RecyclerView.ViewHolder getNewMultipleModeViewHolder(@NonNull ViewGroup viewGroup, int itemType);
       //显示数据的时候回调
       void showData(VH vh,int position);
    }

}
