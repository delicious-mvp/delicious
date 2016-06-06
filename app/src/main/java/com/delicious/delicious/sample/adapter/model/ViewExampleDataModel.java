package com.delicious.delicious.sample.adapter.model;


import com.delicious.delicious.sample.data.SampleItem;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface ViewExampleDataModel {

    void add(SampleItem photo);

    SampleItem getPhotoItem(int position);
}
