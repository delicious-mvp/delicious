package com.delicious.delicious.search.presenter;

import com.delicious.delicious.base.presenter.BasePresenter;
import com.delicious.delicious.base.presenter.BaseView;
import com.delicious.delicious.listener.OnRecyclerItemClickListener;
import com.delicious.delicious.search.adapterdelegates.model.SearchDataModel;
import com.delicious.delicious.search.adapterdelegates.view.SearchAdapterView;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public interface ViewSearchContract {
    interface View extends BaseView<Presenter>{
        void refresh();
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
