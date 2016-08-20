package com.delicious.delicious.data.source.restaurant;

import android.util.Log;

import com.delicious.delicious.Constants.Constants;
import com.delicious.delicious.Global;
import com.delicious.delicious.data.Restaurant;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class RestaurantRemoteDataSource implements RestaurantDataSource {
    private int totalCnt = -1;
    private static RestaurantRemoteDataSource sInstance;

    // Cache...
    private List<Restaurant> items = new ArrayList<>();

    public RestaurantRemoteDataSource() {
    }

    synchronized public static RestaurantRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new RestaurantRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public Observable<List<Restaurant>> getRestaurants(String location, int page, String sort) {
        return Global.getRequestService().getRestaurants(location, Constants.RADIUS,
                Constants.PAGE_COUNT, page, Constants.IMAGE_FILTER, sort)
                .filter(restaurantChannel -> restaurantChannel != null && restaurantChannel.getLocalChannel() != null)
                .flatMap(restaurantChannel -> {
                    totalCnt = Integer.parseInt(restaurantChannel.getLocalChannel().getLocalInfo().getTotalCount())  ;
                    items.addAll(restaurantChannel.getLocalChannel().getItems());
                    return Observable.from(restaurantChannel.getLocalChannel().getItems()).toList();
                });
    }

    @Override
    public int getTotalCount() {
        return totalCnt;
    }

    @Override
    public int currentCount() {
        return items.size();
    }
}
