package com.delicious.delicious.search.presenter;

import android.text.TextUtils;
import com.delicious.delicious.Global;
import com.delicious.delicious.base.presenter.AbstractPresenter;
import com.delicious.delicious.network.data.ImageItem;
import com.delicious.delicious.network.data.ImageResult;
import com.delicious.delicious.search.adapterdelegates.model.SearchDataModel;
import com.delicious.delicious.search.adapterdelegates.view.SearchAdapterView;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public class ViewSearchPresenter extends AbstractPresenter<ViewSearchContract.View> implements ViewSearchContract.Presenter{
    private SearchDataModel mSearchDataModel;
    private PublishSubject<String> searchSubject;
    private Subscription searchSubscription;
    private SearchAdapterView mSearchAdapterView;
    private int pageCnt = 1;
    public ViewSearchPresenter(ViewSearchContract.View view) {
        super(view);
        searchSubject = PublishSubject.create();
        initSubscription();
    }

    @Override
    public void setDataView(SearchAdapterView searchAdapterView) {
        mSearchAdapterView = searchAdapterView;
    }

    @Override
    public SearchAdapterView getDataView() {
        return mSearchAdapterView;
    }

    @Override
    public void setDataModel(SearchDataModel searchDataModel) {
        this.mSearchDataModel = searchDataModel;
    }

    @Override
    public void inputSearchText(String searchText) {
        if (!searchSubscription.isUnsubscribed()) {
            searchSubscription.unsubscribe();
        }
        mSearchDataModel.clear();
        initSubscription();
        if (!TextUtils.isEmpty(searchText)) {
            searchSubject.onNext(searchText);
        } else {
            getView().refresh();
        }
    }

    @Override
    public void unSubscribeSearch() {
        searchSubscription.unsubscribe();
    }

    @Override
    public void onItemClick(int position) {
        getView().goToDetailView(position);
    }

    @Override
    public void start() {

    }
    private void initSubscription(){
        searchSubscription = searchSubject
                .onBackpressureBuffer()
                .throttleWithTimeout(200, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(this::loadSearchResult, throwable -> {

                });
    }
    private void loadSearchResult(String text) {
        Global.getRequestService().getSearch(text,pageCnt)
                .map(searchChannel -> searchChannel.getChannel())
                .filter(imageResult -> imageResult != null && imageResult.getResult() > 0)
                .flatMap((Func1<ImageResult, Observable<ImageItem>>) imageResult -> Observable.from(imageResult.getItem()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imageItem -> mSearchDataModel.addItem(imageItem), throwable -> {

                }, () -> getDataView().refresh());
    }
}
