package com.delicious.delicious.base.repository.observer;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class RepositoryObserver {
    static RepositoryObserver instance;

    private PublishSubject<TransactionData> observer;
    private Map<Object, CompositeSubscription> updatables;

    public RepositoryObserver() {
        observer = PublishSubject.create();
        updatables = new ConcurrentHashMap<>();
    }

    synchronized public static RepositoryObserver getInstance() {
        if (instance == null) {
            instance = new RepositoryObserver();
        }
        return instance;
    }

    synchronized public <T> void register(Class<T> klass, Object object, final Updatable<T> updatable) {
        CompositeSubscription compositeSubscription;
        if (updatables.containsKey(object)) {
            compositeSubscription = updatables.get(object);
        } else {
            compositeSubscription = new CompositeSubscription();
            updatables.put(object, compositeSubscription);
        }

        Observable<TransactionData<T>> map = observer
                .filter(it -> it.data.getClass() == klass)
                .map(it -> {
                    TransactionData<T> transactionData = new TransactionData<>();
                    transactionData.data = (T) it.data;
                    transactionData.isCreated = it.isCreated;
                    transactionData.isDeleted = it.isDeleted;
                    transactionData.isUpdated = it.isUpdated;
                    return transactionData;
                });
        compositeSubscription.add(updatable.update(map));
    }

    synchronized public void unregister(Object object) {
        if (updatables.containsKey(object)) {
            updatables.get(object).unsubscribe();
            updatables.remove(object);
        }
    }

    synchronized public void update(TransactionData object) {
        observer.onNext(object);
    }

    synchronized public void update(Collection<TransactionData> object) {
        for (TransactionData o : object) {
            observer.onNext(o);
        }
    }
}
