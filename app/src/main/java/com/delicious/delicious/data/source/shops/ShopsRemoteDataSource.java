package com.delicious.delicious.data.source.shops;

import android.support.annotation.NonNull;

import com.delicious.delicious.data.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopsRemoteDataSource implements ShopsDataSource {

    private static ShopsRemoteDataSource sInstance;

    public ShopsRemoteDataSource() {
    }

    public static ShopsRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new ShopsRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getShops(@NonNull GetShopsCallback callback) {
        List<Shop> fakeShops = new ArrayList<>();

        Shop shop = new Shop();
        shop.setId("26344965");
        shop.setTitle("고베그릴");
        shop.setCategory("음식점 > 한식 > 육류,고기");
        shop.setDistance("225");
        shop.setImageUrl("http://cfile190.uf.daum.net/image/276EEE4254852ADA365553");
        shop.setOldAddress("서울 관악구 남현동 1060-10");
        shop.setNewAddress("서울 관악구 남부순환로 2082-25");
        fakeShops.add(shop);

        callback.onShopsLoaded(fakeShops);
    }
}
