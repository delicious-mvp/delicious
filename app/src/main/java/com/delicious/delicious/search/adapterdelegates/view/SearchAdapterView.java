package com.delicious.delicious.search.adapterdelegates.view;

import com.delicious.delicious.listener.OnRecyclerItemClickListener;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public interface SearchAdapterView {
    void refresh();
    void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener);
}
