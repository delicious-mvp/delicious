package com.delicious.delicious.search.adapterdelegates;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.delicious.delicious.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public class SearchViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_search_result_thumb)
    SimpleDraweeView item_thumb;
    @BindView(R.id.item_search_result_title)
    TextView item_title;
    public SearchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
