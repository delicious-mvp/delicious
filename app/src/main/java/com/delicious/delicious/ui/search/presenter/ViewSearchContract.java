package com.delicious.delicious.ui.search.presenter;

import com.delicious.delicious.base.presenter.BasePresenter;
import com.delicious.delicious.base.presenter.BaseView;
import com.delicious.delicious.ui.search.adapterdelegates.model.SearchDataModel;
import com.delicious.delicious.ui.search.adapterdelegates.view.SearchAdapterView;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public interface ViewSearchContract {
    interface View extends BaseView<Presenter>{
        void refresh();
        void goToDetailView(int postion);
    }
    interface Presenter extends BasePresenter{
        void setDataView(SearchAdapterView searchAdapterView);
        SearchAdapterView getDataView();
        void setDataModel(SearchDataModel searchDataModel);
        void inputSearchText(String searchText);
        void unSubscribeSearch();
        void onItemClick(int position);
    }
}
