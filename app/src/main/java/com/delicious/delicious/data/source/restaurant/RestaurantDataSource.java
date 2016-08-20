package com.delicious.delicious.data.source.restaurant;

import com.delicious.delicious.data.Restaurant;
import java.util.List;
import rx.Observable;
public interface RestaurantDataSource {

    Observable<List<Restaurant>> getRestaurants(String location, int page, String sort);
    int getTotalCount();
    int currentCount();
}
