package com.delicious.delicious.base.repository.observer;


import rx.Observable;
import rx.Subscription;

public interface Updatable<DATA> {
    Subscription update(Observable<TransactionData<DATA>> t);
}
