package com.ellen.basequickandroid.base.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;

public abstract class BaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T extends RecyclerView.ViewHolder>{
        void onItemClick(T t,int position);
    }

}
