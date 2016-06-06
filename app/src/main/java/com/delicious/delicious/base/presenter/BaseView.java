package com.delicious.delicious.base.presenter;

/**
 * Created by tae-hwan on 6/6/16.
 */
public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgress();

    void hideProgress();
}
