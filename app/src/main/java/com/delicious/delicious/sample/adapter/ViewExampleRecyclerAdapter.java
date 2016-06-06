package com.delicious.delicious.sample.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.delicious.delicious.base.adapter.BaseRecyclerAdapter;
import com.delicious.delicious.base.adapter.view.BaseRecyclerView;
import com.delicious.delicious.listener.OnRecyclerItemClickListener;
import com.delicious.delicious.sample.adapter.model.ViewExampleDataModel;
import com.delicious.delicious.sample.adapter.view.ViewExampleRecyclerView;
import com.delicious.delicious.sample.data.SampleItem;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class ViewExampleRecyclerAdapter extends BaseRecyclerAdapter<SampleItem> implements ViewExampleDataModel {

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public ViewExampleRecyclerAdapter(Context context) {
        super(context);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public BaseRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewExampleRecyclerView(parent, this, onRecyclerItemClickListener);
    }

    @Override
    public void add(SampleItem photo) {
        addItem(photo);
    }

    @Override
    public SampleItem getPhotoItem(int position) {
        return getItem(position);
    }
}
