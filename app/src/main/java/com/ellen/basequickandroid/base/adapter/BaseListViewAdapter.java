package com.ellen.basequickandroid.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class BaseListViewAdapter<T,E extends BaseListViewAdapter.ViewHolder> extends BaseAdapter {

    private WeakReference<Context> contextWeakReference;
    private List<T> dataList;

    public WeakReference<Context> getContextWeakReference() {
        return contextWeakReference;
    }

    public void setContextWeakReference(WeakReference<Context> contextWeakReference) {
        this.contextWeakReference = contextWeakReference;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public BaseListViewAdapter(Context context, List<T> dataList){
        this.contextWeakReference = new WeakReference<>(context);
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(contextWeakReference.get()).inflate(getItemLayoutId(),null);
            viewHolder = getNewViewHolder();
            initViewHolder((E)viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        showData((E)viewHolder,position);
        return convertView;
    }

    protected abstract void showData(E viewHolder,int position);
    protected abstract void initViewHolder(E viewHolder,View view);
    protected abstract int getItemLayoutId();
    protected abstract ViewHolder getNewViewHolder();

    public static class ViewHolder{}

}
