package com.delicious.delicious.ui.restaurants.adpater;

import com.delicious.delicious.ui.restaurants.adpater.adapterdelegate.RestaurantAdapterDelegate;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.DisplayItem;
import com.hannesdorfmann.adapterdelegates2.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends ListDelegationAdapter<List<DisplayItem>>
        implements RestaurantsAdapterContract.View, RestaurantsAdapterContract.Model {

    public RestaurantsAdapter() {
        setItems(new ArrayList<>());

        delegatesManager.addDelegate(new RestaurantAdapterDelegate());
    }

    @Override
    public void addItems(List<DisplayItem> items) {
        getItems().addAll(items);
    }

    @Override
    public void clearItems() {
        getItems().clear();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }
}
