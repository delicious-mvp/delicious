package com.delicious.delicious.network;

import com.delicious.delicious.BuildConfig;
import com.delicious.delicious.data.RestaurantChannel;
import com.delicious.delicious.network.data.SearchChannel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chonamdu on 2016. 6. 5..
 */

public interface RequestService {
    @GET("search/image?output=json&apikey=" + BuildConfig.DAUM_API_KEY)
    Observable<SearchChannel> getSearch(
            @Query("q") String seachText,
            @Query("pageno") int pageNo);

    //https://apis.daum.net/local/v1/search/category.{format}
    @GET("local/v1/search/category.json?apikey=" + BuildConfig.DAUM_API_KEY + "&code=FD6")
    Observable<RestaurantChannel> getRestaurants(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("count") int pageCount,
            @Query("page") int page,
            @Query("image") String imageFilter,
            @Query("sort") String sort
    );
}
