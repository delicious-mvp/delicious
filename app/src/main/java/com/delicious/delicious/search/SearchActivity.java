package com.delicious.delicious.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.delicious.delicious.R;
import com.delicious.delicious.base.BaseActivity;
import com.delicious.delicious.search.presenter.ViewSearchPresenter;
import com.delicious.delicious.util.ActivityUtil;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public class SearchActivity extends BaseActivity {

    private ViewSearchPresenter viewSearchPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchFragment searchFragment = (SearchFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(searchFragment == null){
            searchFragment = SearchFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),searchFragment,R.id.contentFrame);
        }
        viewSearchPresenter = new ViewSearchPresenter(searchFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (viewSearchPresenter != null) {
            viewSearchPresenter.destroy();
        }
    }
}
