package com.delicious.delicious.ui.search.adapterdelegates.model;

import com.delicious.delicious.network.data.ImageItem;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public interface SearchDataModel {
    void addItem(ImageItem imageItem);
    int getSize();
    ImageItem getItem(int position);
    void clear();
}
