package com.delicious.delicious.ui.restaurants;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.delicious.delicious.R;
import com.delicious.delicious.base.BaseActivity;
import com.delicious.delicious.data.source.shops.ShopsRepository;
import com.delicious.delicious.util.ActivityUtil;

public class RestaurantsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        RestaurantsFragment restaurantsFragment = RestaurantsFragment.newInstance();

        // Create to presenter
        new RestaurantsPresenter(
                restaurantsFragment,
                ShopsRepository.getInstance()
        );

        ActivityUtil.replaceFragmentToActivity(
                getSupportFragmentManager(),
                restaurantsFragment,
                R.id.fragment_container
        );
    }
}
