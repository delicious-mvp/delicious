package com.delicious.delicious.data.source.restaurant;

import android.support.annotation.NonNull;

import com.delicious.delicious.Constants.Constants;
import com.delicious.delicious.Global;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantRemoteDataSource implements RestaurantDataSource {

    private static RestaurantRemoteDataSource sInstance;

    public RestaurantRemoteDataSource() {
    }

    public static RestaurantRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new RestaurantRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getRestaurants(String location, int page, String sort,
                               @NonNull GetRestaurantsCallback callback) {

        Global.getRequestService().getRestaurants(location, Constants.RADIUS,
                Constants.PAGE_COUNT, page, Constants.IMAGE_FILTER, sort)
                .subscribeOn(Schedulers.io())
                .filter(restaurantChannel -> restaurantChannel != null)
                .map(restaurantChannel -> restaurantChannel.getLocalChannel())
                .filter(restaurantLocalChannel -> restaurantLocalChannel.getItems() != null && restaurantLocalChannel.getItems().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restaurantLocalChannel -> {
                    callback.onRestaurantsLoaded(restaurantLocalChannel.getItems());
                }, throwable -> {
                    callback.onRestaurantsLoadFailed();
                });
    }
}
