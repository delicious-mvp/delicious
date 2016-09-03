package com.delicious.delicious.ui.restaurants.adpater;

import com.delicious.delicious.ui.restaurants.adpater.displayitem.DisplayItem;

import java.util.List;

public interface RestaurantsAdapterContract {

    interface View {

        void refresh();
    }

    interface Model {

        void addItems(List<DisplayItem> item);

        DisplayItem getItem(int position);

        void clearItems();
    }
}
