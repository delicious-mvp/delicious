package com.delicious.delicious.network;

import com.delicious.delicious.BuildConfig;
import com.delicious.delicious.network.data.SearchChannel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chonamdu on 2016. 6. 5..
 */

public interface RequestService {
    @GET("image?output=json&apikey=" + BuildConfig.DAUM_API_KEY)
    Observable<SearchChannel> getSearch(@Query("q") String seachText, @Query("pageno") int pageNo);
}
