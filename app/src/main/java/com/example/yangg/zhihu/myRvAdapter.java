package com.example.yangg.zhihu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangg on 2017/7/5.
 */

class myRvAdapter extends RecyclerView.Adapter {
    private List<String> item = new ArrayList<>();
    public myRvAdapter(List<String> dates) {
        this.item = dates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv.setText(item.get(position));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
