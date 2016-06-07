package com.delicious.delicious.search.adapterdelegates.model;

import com.delicious.delicious.network.data.ImageItem;

import java.util.List;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public interface SearchDataModel {
    void addItem(ImageItem imageItem);
    int getSize();
    ImageItem getItem(int position);
    void clear();
}
