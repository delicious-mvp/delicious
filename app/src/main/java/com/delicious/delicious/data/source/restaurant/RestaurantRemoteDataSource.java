package com.delicious.delicious.data.source.restaurant;

import com.delicious.delicious.Constants.Constants;
import com.delicious.delicious.Global;
import com.delicious.delicious.data.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RestaurantRemoteDataSource implements RestaurantDataSource {

    private int totalCnt = -1;

    // Cache...
    private List<Restaurant> items = new ArrayList<>();
    private Map<String, Restaurant> restaurantMap = new HashMap<>();

    private static RestaurantRemoteDataSource sInstance;

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
        if (items.size() >= page * Constants.PAGE_COUNT) {
            return Observable.just(items)
                    .flatMap(new Func1<List<Restaurant>, Observable<List<Restaurant>>>() {

                        @Override
                        public Observable<List<Restaurant>> call(List<Restaurant> restaurants) {
                            int start = (page - 1) * Constants.PAGE_COUNT;
                            int end = page * Constants.PAGE_COUNT;

                            return Observable.just(restaurants.subList(start, end));
                        }
                    });
        }

        return Global.getRequestService().getRestaurants(location, Constants.RADIUS,
                Constants.PAGE_COUNT, page, Constants.IMAGE_FILTER, sort)
                .filter(restaurantChannel -> restaurantChannel != null && restaurantChannel.getLocalChannel() != null)
                .flatMap(restaurantChannel -> {
                    totalCnt = Integer.parseInt(restaurantChannel.getLocalChannel().getLocalInfo().getTotalCount());
                    items.addAll(restaurantChannel.getLocalChannel().getItems());

                    return Observable.from(restaurantChannel.getLocalChannel().getItems())
                            .doOnNext(new Action1<Restaurant>() {

                                @Override
                                public void call(Restaurant restaurant) {
                                    restaurantMap.put(restaurant.getId(), restaurant);
                                }
                            })
                            .toList();
                });
    }

    public Observable<Restaurant> getRestaurant(String restaurantId) {
        return Observable.just(restaurantMap.get(restaurantId));
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
