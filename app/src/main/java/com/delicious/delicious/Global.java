package com.delicious.delicious;

import com.delicious.delicious.app.DeliciousApp;
import com.delicious.delicious.network.RequestService;
import com.delicious.delicious.network.RetrofitCreator;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public class Global {
    private static RequestService requestService;

    public static void init() {
        initRequestService();
    }

    private static void initRequestService() {
        requestService = RetrofitCreator.createRetrofit().create(RequestService.class);
    }

    public static RequestService getRequestService() {
        return requestService;
    }

    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0) {
            DeliciousApp.applicationHandler.post(runnable);
        } else {
            DeliciousApp.applicationHandler.postDelayed(runnable, delay);
        }
    }

    public static void clearRunOnUIThread() {
        DeliciousApp.applicationHandler.removeCallbacksAndMessages(null);
    }

    public static void cancelRunOnUIThread(Runnable runnable) {
        DeliciousApp.applicationHandler.removeCallbacks(runnable);
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    /**
     * 예제용
     * */
    public static <T> Observable.Transformer<T, T> applyProgress() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //프로그래스 생성
                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        //프로그래스바 해제
                    }
                });
            }
        };
    }
}
