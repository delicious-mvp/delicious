package com.delicious.delicious.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.delicious.delicious.R;
import com.delicious.delicious.base.BaseActivity;
import com.delicious.delicious.sample.presenter.ViewExamplePresenter;
import com.delicious.delicious.util.ActivityUtil;

/**
 * Created by tae-hwan on 6/6/16.
 */
public class ViewExampleActivity extends BaseActivity {

    private ViewExamplePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_example);

        ViewExampleFragment fragment = (ViewExampleFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = ViewExampleFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        presenter = new ViewExamplePresenter(fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.detachView();
        }
    }
}
