package com.delicious.delicious.ui.restaurant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.delicious.delicious.R;
import com.delicious.delicious.base.BaseActivity;

import butterknife.BindView;

public class RestaurantActivity extends BaseActivity {

    public static final String EXTRA_RESTAURANT_URL = "com.delicious.delicious.ui.restaurant.RestaurantActivity.RESTAURANT_URL";

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        String url = getIntent().getStringExtra(EXTRA_RESTAURANT_URL);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(view.getUrl());
                return true;
            }
        });
        webview.loadUrl(url);
    }
}
