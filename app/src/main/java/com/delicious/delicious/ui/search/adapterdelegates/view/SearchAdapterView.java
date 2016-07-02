package com.delicious.delicious.ui.search.adapterdelegates.view;

import com.delicious.delicious.widget.recyclerview.OnRecyclerItemClickListener;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public interface SearchAdapterView {
    void refresh();
    void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener);
}
