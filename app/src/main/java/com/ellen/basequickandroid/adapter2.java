package com.ellen.basequickandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.basequickandroid.base.adapter.recyclerview.BaseMultipleRecyclerViewAdapter;
import com.ellen.basequickandroid.base.adapter.recyclerview.BaseViewHolder;

public class adapter2 extends BaseMultipleRecyclerViewAdapter {

    public adapter2(Context context) {
        super(context);
    }

    @Override
    protected int getMultipleItemViewType(int position) {
        if(position == 0) {
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    protected int getItemSize() {
        return 6;
    }

    @Override
    protected BaseViewHolder getNewBaseViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        if(itemType == 0) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_view, null);
            return new TopViewHolder(view);
        }else {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_view1, null);
            return new CenterViewHolder(view);
        }
    }

    @Override
    protected void showData(BaseViewHolder baseViewHolder, int position) {

    }

    static class TopViewHolder extends BaseViewHolder{
        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class CenterViewHolder extends BaseViewHolder{
        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
