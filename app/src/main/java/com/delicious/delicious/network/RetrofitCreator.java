package com.delicious.delicious.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chonamdu on 2016. 6. 5..
 */

public class RetrofitCreator {
    public static Retrofit createRetrofit() {

        return new Retrofit.Builder()
                .baseUrl("https://apis.daum.net/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }
}
