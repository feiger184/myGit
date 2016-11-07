package com.feicui.gaonews.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.feicui.gaonews.R;

/*
* WebView界面
* */
public class NewsActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar pb_newsload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        webView = (WebView) findViewById(R.id.webView);
        pb_newsload = (ProgressBar) findViewById(R.id.pb_pb_newsload);
        String link = getIntent().getStringExtra("link");

        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb_newsload.setProgress(newProgress);

                if (pb_newsload.getProgress() == 100) {
                    pb_newsload.setVisibility(view.GONE);
                }
            }
        });
    }
}
