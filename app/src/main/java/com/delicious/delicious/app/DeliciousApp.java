package com.delicious.delicious.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.delicious.delicious.Global;
import com.delicious.delicious.fresco.imagepipeline.ImagePipelineConfigFactory;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public class DeliciousApp extends Application {
    private static final String TAG = "DeliciousApp";
    public static Context applicationContext;
    public static volatile Handler applicationHandler = null;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    public void init(){
        applicationContext = getApplicationContext();
        Fresco.initialize(applicationContext, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(applicationContext));
        applicationHandler = new Handler(applicationContext.getMainLooper());
        Global.init();
    }

    public static Context getAppContext() {
        return applicationContext;
    }
}
