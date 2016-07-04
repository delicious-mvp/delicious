package com.delicious.delicious.ui.sample.adapter.model;


import com.delicious.delicious.ui.sample.data.SampleItem;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface ViewExampleDataModel {

    void add(SampleItem photo);

    SampleItem getPhotoItem(int position);
}
