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

    /**
     * MVP View return
     */
    protected V getView() {
        return view;
    }

    /**
     * View Attach check
     */
    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {
        view = null;
    }
}
