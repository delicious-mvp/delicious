package com.delicious.delicious.ui.restaurant;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
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
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(view, request.getUrl().toString());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


        });
        webview.loadUrl(url);
    }
}
