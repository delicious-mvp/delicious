package com.delicious.delicious.ui.sample.adapter.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.delicious.delicious.R;
import com.delicious.delicious.base.adapter.view.BaseRecyclerView;
import com.delicious.delicious.widget.recyclerview.OnRecyclerItemClickListener;
import com.delicious.delicious.ui.sample.adapter.ViewExampleRecyclerAdapter;
import com.delicious.delicious.ui.sample.data.SampleItem;

import butterknife.BindView;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class ViewExampleRecyclerView extends BaseRecyclerView<ViewExampleRecyclerAdapter, SampleItem> {

    @BindView(R.id.image)
    ImageView imageView;

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public ViewExampleRecyclerView(ViewGroup parent, ViewExampleRecyclerAdapter adapter, OnRecyclerItemClickListener onRecyclerItemClickListener) {
        super(R.layout.item_photo_view, parent, adapter);

        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public void onViewHolder(@Nullable SampleItem item, final int position) {
        if (item != null) {

            imageView.setImageResource(item.drawableRes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerItemClickListener != null) {
                        onRecyclerItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
