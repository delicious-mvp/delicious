package com.delicious.delicious.base.presenter;

/**
 * Created by tae-hwan on 6/6/16.
 */
public abstract class AbstractPresenter<V extends BaseView> implements BasePresenter {

    private V view;

    public AbstractPresenter(V view) {
        this.view = view;

        view.setPresenter(this);
    }

    public V getView() {
        return view;
    }
}
