package com.delicious.delicious.data.source.restaurant;

import android.support.annotation.NonNull;

import com.delicious.delicious.data.Restaurant;

import java.util.List;

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
    public void getRestaurants(String location, int page,
                               String sort, @NonNull GetRestaurantsCallback callback) {
        restaurantDataSource.getRestaurants(location, page,sort, new GetRestaurantsCallback() {

            @Override
            public void onRestaurantsLoaded(List<Restaurant> restaurants) {
                callback.onRestaurantsLoaded(restaurants);
            }

            @Override
            public void onRestaurantsLoadFailed() {
                callback.onRestaurantsLoadFailed();
            }
        });
    }
}
