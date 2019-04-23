package com.ellen.basequickandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ellen.basequickandroid.base.adapter.recyclerview.BaseSingleRecyclerViewAdapter;
import com.ellen.basequickandroid.base.adapter.recyclerview.BaseViewHolder;

import java.util.List;

public class Adapter1 extends BaseSingleRecyclerViewAdapter<String, Adapter1.Adapter1ViewHolder> {


    public Adapter1(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_recycler_view;
    }

    @Override
    protected Adapter1ViewHolder getNewViewHolder(View view) {
        return new Adapter1ViewHolder(view);
    }

    @Override
    protected void showData(Adapter1ViewHolder adapter1ViewHolder, int position) {
          adapter1ViewHolder.tv.setText(getDataList().get(position));
    }

    public static class Adapter1ViewHolder extends BaseViewHolder{

        TextView tv;

        public Adapter1ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
