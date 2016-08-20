package com.delicious.delicious.ui.restaurants;

import android.util.Log;

import com.delicious.delicious.base.presenter.AbstractPresenter;
import com.delicious.delicious.data.Restaurant;
import com.delicious.delicious.data.source.restaurant.RestaurantRepository;
import com.delicious.delicious.ui.restaurants.adpater.RestaurantsAdapterContract;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.DisplayItem;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.RestaurantDisplayItem;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RestaurantsPresenter extends AbstractPresenter<RestaurantContract.View> implements RestaurantContract.Presenter {
    private boolean isLoading;
    private RestaurantRepository restaurantRepository;
    RestaurantsAdapterContract.Model adapterModel;
    private int page = 0;

    public RestaurantsPresenter(RestaurantContract.View view,
                                RestaurantRepository restaurantRepository) {
        super(view);
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void setAdapterView(RestaurantsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void start() {
        page = 0;
        loadRestaurants("37.476585,126.981858", "0");
    }

    @Override
    public void loadRestaurants(String location, String sort) {
        restaurantRepository.getRestaurants(location,++page,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        isLoading = true;
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        isLoading = false;
                    }
                })
                .subscribe(new Action1<List<Restaurant>>() {
                    @Override
                    public void call(List<Restaurant> restaurants) {
                        if (page <= 1) {
                            adapterModel.clearItems();
                        }
                        adapterModel.addItems(createShopDisplayItem(restaurants));
                        getView().showShops();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showLoadFailure();
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });
    }

    private List<DisplayItem> createShopDisplayItem(List<Restaurant> restaurants) {
        List<DisplayItem> result = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantDisplayItem restaurantDisplayItem = new RestaurantDisplayItem();

            restaurantDisplayItem.setTitle(restaurant.getTitle());
            restaurantDisplayItem.setCategory(restaurant.getCategory());
            restaurantDisplayItem.setDistance(restaurant.getDistance());
            restaurantDisplayItem.setThumbnailUrl(restaurant.getImageUrl());

            boolean hasNewAddress = !Strings.isNullOrEmpty(restaurant.getNewAddress());
            restaurantDisplayItem.setAddress(hasNewAddress ? restaurant.getNewAddress() : restaurant.getOldAddress());

            result.add(restaurantDisplayItem);
        }
        return result;
    }
    @Override
    public boolean hasMoreNext(){
        return restaurantRepository.getTotalCount() != restaurantRepository.currentCount();
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }
}
