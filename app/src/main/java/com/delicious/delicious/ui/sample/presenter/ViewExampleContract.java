package com.delicious.delicious.ui.sample.presenter;

import android.support.annotation.DrawableRes;

import com.delicious.delicious.base.presenter.BasePresenter;
import com.delicious.delicious.base.presenter.BaseView;
import com.delicious.delicious.ui.sample.adapter.model.ViewExampleDataModel;

/**
 * Created by tae-hwan on 6/6/16.
 */
public interface ViewExampleContract {

    interface View extends BaseView<Presenter> {

        void refresh();

        void showBottomSheet(@DrawableRes int drawableRes);

        void showFloatingActionButton();

        void hideFloatingActionButton();
    }

    interface Presenter extends BasePresenter {

        void setDataModel(ViewExampleDataModel dataModel);

        void updateData();

        void onItemClick(int position);

        void onSlide(float slideOffset);
    }
}
