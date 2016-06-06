package com.delicious.delicious;

import com.delicious.delicious.app.DeliciousApp;
import com.delicious.delicious.network.RequestService;
import com.delicious.delicious.network.RetrofitCreator;

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
}
