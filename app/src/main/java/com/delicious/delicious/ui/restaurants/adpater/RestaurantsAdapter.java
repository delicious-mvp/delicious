package com.delicious.delicious.ui.restaurants.adpater;

import com.delicious.delicious.ui.restaurants.adpater.adapterdelegate.RestaurantAdapterDelegate;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.DisplayItem;
import com.hannesdorfmann.adapterdelegates2.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends ListDelegationAdapter<List<DisplayItem>>
        implements RestaurantsAdapterContract.View, RestaurantsAdapterContract.Model {

    public interface OnItemCLickListener {

        void onItemClicked(int position);
    }

    private RestaurantAdapterDelegate restaurantAdapterDelegate;
    private OnItemCLickListener onRestaurantItemClickListener;

    public RestaurantsAdapter() {
        setItems(new ArrayList<>());

        restaurantAdapterDelegate = new RestaurantAdapterDelegate();
        restaurantAdapterDelegate.setOnItemClickListener(new OnItemCLickListener() {

            @Override
            public void onItemClicked(int position) {
                if (onRestaurantItemClickListener != null) {
                    onRestaurantItemClickListener.onItemClicked(position);
                }
            }
        });

        delegatesManager.addDelegate(restaurantAdapterDelegate);
    }

    public void setOnRestaurantItemClickListener(OnItemCLickListener listener) {
        onRestaurantItemClickListener = listener;
    }

    @Override
    public void addItems(List<DisplayItem> items) {
        getItems().addAll(items);
    }

    @Override
    public DisplayItem getItem(int position) {
        return items.get(position);
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
