package com.ellen.basequickandroid.base.adapter.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class BaseSingleRecyclerViewAdapter<T,VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private WeakReference<Context> contextWeakReference;
    private List<T> dataList;

    public BaseSingleRecyclerViewAdapter(Context context,List<T> dataList){
        contextWeakReference = new WeakReference<>(context);
        this.dataList = dataList;
    }

    public Context getContext(){
        return contextWeakReference.get();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(getContext()).inflate(getItemLayoutId(),null);
        return getNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {
        showData(vh,position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public List<T> getDataList() {
        return dataList;
    }

    protected abstract int getItemLayoutId();
    protected abstract VH getNewViewHolder(View view);
    protected abstract void showData(VH vh, int position);
}
