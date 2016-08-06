package com.delicious.delicious.data.source.restaurant;

import android.support.annotation.NonNull;

import com.delicious.delicious.Constants.Constants;
import com.delicious.delicious.Global;
import com.delicious.delicious.data.Restaurant;
import com.delicious.delicious.data.RestaurantChannel;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RestaurantRemoteDataSource implements RestaurantDataSource {

    private static RestaurantRemoteDataSource sInstance;

    // Cache...
    private List<Restaurant> items = new ArrayList<>();

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
                .filter(restaurantChannel -> restaurantChannel != null && restaurantChannel.getLocalChannel() != null)
                .filter(new Func1<RestaurantChannel, Boolean>() {

                    @Override
                    public Boolean call(RestaurantChannel restaurantChannel) {
                        return restaurantChannel.getLocalChannel().getLocalInfo() != null
                                && items.size() < Integer.parseInt(restaurantChannel.getLocalChannel().getLocalInfo().getTotalCount());
                    }
                })
                .map(restaurantChannel -> restaurantChannel.getLocalChannel())
                .filter(restaurantLocalChannel -> restaurantLocalChannel.getItems() != null && restaurantLocalChannel.getItems().size() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restaurantLocalChannel -> {
                    items.addAll(restaurantLocalChannel.getItems());
                    callback.onRestaurantsLoaded(restaurantLocalChannel.getItems());
                }, throwable -> {
                    callback.onRestaurantsLoadFailed();
                });
    }
}
