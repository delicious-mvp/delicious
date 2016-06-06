package com.delicious.delicious;

import android.content.Context;

import com.delicious.delicious.network.RequestService;
import com.delicious.delicious.network.RetrofitCreator;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public class Global {
    private static RequestService requestService;
    public static void init(){
        initRequestService();
    }
    private static void initRequestService(){
        requestService = RetrofitCreator.createRetrofit().create(RequestService.class);
    }
    public static RequestService getRequestService(){
        return  requestService;
    }

}
