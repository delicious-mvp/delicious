package com.delicious.delicious.ui.restaurants;

import com.delicious.delicious.base.presenter.AbstractPresenter;
import com.delicious.delicious.data.Shop;
import com.delicious.delicious.data.source.shops.ShopsDataSource;
import com.delicious.delicious.data.source.shops.ShopsRepository;
import com.delicious.delicious.ui.restaurants.adpater.RestaurantsAdapterContract;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.DisplayItem;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.RestaurantDisplayItem;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsPresenter extends AbstractPresenter<RestaurantContract.View> implements RestaurantContract.Presenter {

    private ShopsRepository shopsRepository;
    RestaurantsAdapterContract.Model adapterModel;

    public RestaurantsPresenter(RestaurantContract.View view,
                                ShopsRepository shopsRepository) {
        super(view);
        this.shopsRepository = shopsRepository;
    }

    @Override
    public void setAdapterView(RestaurantsAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void start() {
        shopsRepository.getShops(new ShopsDataSource.GetShopsCallback() {

            @Override
            public void onShopsLoaded(List<Shop> shops) {
                adapterModel.clearItems();
                adapterModel.addItems(createShopDisplayItem(shops));

                getView().showShops();
            }

            @Override
            public void onShopsLoadFailed() {
                getView().showLoadFailure();
            }
        });
    }

    private List<DisplayItem> createShopDisplayItem(List<Shop> shops) {
        List<DisplayItem> result = new ArrayList<>();

        for (Shop shop : shops) {
            RestaurantDisplayItem restaurantDisplayItem = new RestaurantDisplayItem();

            restaurantDisplayItem.setTitle(shop.getTitle());
            restaurantDisplayItem.setCategory(shop.getCategory());
            restaurantDisplayItem.setDistance(shop.getDistance());
            restaurantDisplayItem.setThumbnailUrl(shop.getImageUrl());

            boolean hasNewAddress = !Strings.isNullOrEmpty(shop.getNewAddress());
            restaurantDisplayItem.setAddress(hasNewAddress ? shop.getNewAddress() : shop.getOldAddress());

            result.add(restaurantDisplayItem);
        }
        return result;
    }
}
