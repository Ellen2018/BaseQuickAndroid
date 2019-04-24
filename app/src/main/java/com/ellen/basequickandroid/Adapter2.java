package com.ellen.basequickandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ellen.basequickandroid.base.adapter.recyclerview.BaseMultipleRecyclerViewAdapter;
import com.ellen.basequickandroid.base.adapter.recyclerview.BaseViewHolder;

public class Adapter2 extends  BaseMultipleRecyclerViewAdapter {


    private final static int TYPE_TOP = 1;
    private final static  int TYPE_CENTER = 2;

    public Adapter2(Context context) {
        super(context);
    }

    @Override
    protected int getMultipleItemViewType(int position) {
        if(position % 5 ==  0){
            return TYPE_TOP;
        }else {
            return TYPE_CENTER;
        }
    }

    @Override
    protected int getItemSize() {
        return 18;
    }

    @Override
    protected BaseViewHolder getNewBaseViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        if(itemType == TYPE_TOP){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_view,null);
            return new TopViewHolder(view);
        }else if(itemType == TYPE_CENTER){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_view1,null);
            return new CenterViewHolder(view);
        }
        return null;
    }

    @Override
    protected void showData(BaseViewHolder baseViewHolder, int position) {
        if(baseViewHolder instanceof TopViewHolder){
            //加载顶部item的数据
            TopViewHolder topViewHolder = (TopViewHolder) baseViewHolder;
            topViewHolder.textView.setText("广告");
        }else if(baseViewHolder instanceof CenterViewHolder){
            //加载中间item的数据
            CenterViewHolder centerViewHolder = (CenterViewHolder) baseViewHolder;
            centerViewHolder.textView.setText("新闻");
        }
    }

    static class TopViewHolder extends BaseViewHolder{

        TextView textView;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }

    static class CenterViewHolder extends BaseViewHolder{
        TextView textView;
        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }

}
