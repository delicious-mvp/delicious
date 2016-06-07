package com.delicious.delicious.search.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.delicious.delicious.Global;
import com.delicious.delicious.base.presenter.AbstractPresenter;
import com.delicious.delicious.network.data.ImageItem;
import com.delicious.delicious.network.data.ImageResult;
import com.delicious.delicious.network.data.SearchChannel;
import com.delicious.delicious.search.adapterdelegates.model.SearchDataModel;
import com.delicious.delicious.search.adapterdelegates.view.SearchAdapterView;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
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

    }

    @Override
    public void start() {

    }
    private void initSubscription(){
        searchSubscription = searchSubject
                .onBackpressureBuffer()
                .throttleWithTimeout(200, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.v("DEBUG230","initSubscription s  : " + s);
                        loadSearchResult(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
    private void loadSearchResult(String text) {
        Global.getRequestService().getSearch(text,pageCnt)
                .map(new Func1<SearchChannel, ImageResult>() {
                    @Override
                    public ImageResult call(SearchChannel searchChannel) {
                        return searchChannel.getChannel();
                    }
                })
                .filter(new Func1<ImageResult, Boolean>() {
                    @Override
                    public Boolean call(ImageResult imageResult) {
                        return imageResult != null && imageResult.getResult() > 0;
                    }})
                .flatMap(new Func1<ImageResult, Observable<ImageItem>>() {
                    @Override
                    public Observable<ImageItem> call(ImageResult imageResult) {
                        return Observable.from(imageResult.getItem());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ImageItem>() {
                    @Override
                    public void call(ImageItem imageItem) {
                        mSearchDataModel.addItem(imageItem);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        getDataView().refresh();
                    }
                });
    }
}
