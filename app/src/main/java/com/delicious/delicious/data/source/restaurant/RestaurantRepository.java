package com.delicious.delicious.data.source.restaurant;

import android.support.annotation.NonNull;

import com.delicious.delicious.data.Restaurant;

import java.util.List;

import rx.Observable;

public class RestaurantRepository implements RestaurantDataSource {

    private static RestaurantRepository sShopsRepository;

    private RestaurantDataSource restaurantDataSource;

    public RestaurantRepository(@NonNull RestaurantDataSource restaurantDataSource) {
        this.restaurantDataSource = restaurantDataSource;
    }

    public static RestaurantRepository getInstance() {
        if (sShopsRepository == null) {
            sShopsRepository = new RestaurantRepository(
                    RestaurantRemoteDataSource.getInstance()
            );
        }
        return sShopsRepository;
    }

    @Override
    public Observable<List<Restaurant>> getRestaurants(String location, int page, String sort) {
        return restaurantDataSource.getRestaurants(location,page,sort);
    }

    @Override
    public Observable<Restaurant> getRestaurant(String id) {
        return restaurantDataSource.getRestaurant(id);
    }

    @Override
    public int getTotalCount() {
        return restaurantDataSource.getTotalCount();
    }

    @Override
    public int currentCount() {
        return restaurantDataSource.currentCount();
    }

}
