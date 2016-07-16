package com.delicious.delicious.data.source.shops;

import android.support.annotation.NonNull;

import com.delicious.delicious.data.Shop;

import java.util.List;

public interface ShopsDataSource {

    interface GetShopsCallback {

        void onShopsLoaded(List<Shop> shops);

        void onShopsLoadFailed();
    }

    void getShops(@NonNull GetShopsCallback callback);
}
