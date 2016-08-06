package com.delicious.delicious.ui.restaurants;

import com.delicious.delicious.base.presenter.AbstractPresenter;
import com.delicious.delicious.data.Restaurant;
import com.delicious.delicious.data.source.restaurant.RestaurantDataSource;
import com.delicious.delicious.data.source.restaurant.RestaurantRepository;
import com.delicious.delicious.ui.restaurants.adpater.RestaurantsAdapterContract;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.DisplayItem;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.RestaurantDisplayItem;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsPresenter extends AbstractPresenter<RestaurantContract.View> implements RestaurantContract.Presenter {

    private RestaurantRepository shopsRepository;
    RestaurantsAdapterContract.Model adapterModel;

    private int page = 0;

    public RestaurantsPresenter(RestaurantContract.View view,
                                RestaurantRepository shopsRepository) {
        super(view);
        this.shopsRepository = shopsRepository;
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
        shopsRepository.getRestaurants(location, ++page, sort, new RestaurantDataSource.GetRestaurantsCallback() {

            @Override
            public void onRestaurantsLoaded(List<Restaurant> restaurants) {
                if (page <= 1) {
                    adapterModel.clearItems();
                }
                adapterModel.addItems(createShopDisplayItem(restaurants));

                getView().showShops();
            }

            @Override
            public void onRestaurantsLoadFailed() {
                getView().showLoadFailure();
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
}
