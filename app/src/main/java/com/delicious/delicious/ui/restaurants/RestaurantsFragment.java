package com.delicious.delicious.ui.restaurants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delicious.delicious.R;
import com.delicious.delicious.base.BaseFragment;
import com.delicious.delicious.ui.restaurants.adpater.RestaurantsAdapter;
import com.delicious.delicious.ui.restaurants.adpater.RestaurantsAdapterContract;

import butterknife.BindView;

public class RestaurantsFragment extends BaseFragment<RestaurantContract.Presenter> implements RestaurantContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    RestaurantsAdapterContract.View adapterView;

    public static RestaurantsFragment newInstance() {
        return new RestaurantsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurants, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        if (getPresenter() != null) {
            getPresenter().start();
        }
    }

    private void setupRecyclerView() {
        RestaurantsAdapter adapter = new RestaurantsAdapter();

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        adapterView = adapter;
        getPresenter().setAdapterView(adapter);
    }

    @Override
    public void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().destroy();
        }
        super.onDestroy();
    }

    @Override
    public void showShops() {
        adapterView.refresh();
    }

    @Override
    public void showLoadFailure() {

    }
}
