package com.delicious.delicious.ui.searchdetail.presenter;

import com.delicious.delicious.base.presenter.BasePresenter;
import com.delicious.delicious.base.presenter.BaseView;
import com.delicious.delicious.network.data.ImageItem;

/**
 * Created by chonamdu on 2016. 6. 16..
 */

public interface ViewSearchDetailContract {
    interface View extends BaseView<Presenter>{
        void setTitle(String title);
    }
    interface Presenter extends BasePresenter{
        void setDataMode(ImageItem imageItem);

    }
}
