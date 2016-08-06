package com.delicious.delicious.data.source.restaurant;

import android.support.annotation.NonNull;

import com.delicious.delicious.data.Restaurant;

import java.util.List;

public interface RestaurantDataSource {



    interface GetRestaurantsCallback {

        void onRestaurantsLoaded(List<Restaurant> restaurants);

        void onRestaurantsLoadFailed();
    }

    void getRestaurants(String location, int page,
                        String sort, @NonNull GetRestaurantsCallback callback);
}
