package com.delicious.delicious.data.source.shops;

import android.support.annotation.NonNull;

import com.delicious.delicious.data.Shop;

import java.util.List;

public class ShopsRepository implements ShopsDataSource {

    private static ShopsRepository sShopsRepository;

    private ShopsDataSource shopsDataSource;

    public ShopsRepository(@NonNull ShopsDataSource shopsDataSource) {
        this.shopsDataSource = shopsDataSource;
    }

    public static ShopsRepository getInstance() {
        if (sShopsRepository == null) {
            sShopsRepository = new ShopsRepository(
                    ShopsRemoteDataSource.getInstance()
            );
        }
        return sShopsRepository;
    }

    @Override
    public void getShops(@NonNull GetShopsCallback callback) {
        shopsDataSource.getShops(new GetShopsCallback() {

            @Override
            public void onShopsLoaded(List<Shop> shops) {
                callback.onShopsLoaded(shops);
            }

            @Override
            public void onShopsLoadFailed() {
                callback.onShopsLoadFailed();
            }
        });
    }
}
