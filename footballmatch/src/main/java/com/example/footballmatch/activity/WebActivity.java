package com.example.footballmatch.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.footballmatch.R;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView)findViewById(R.id.web_view);
        Intent intent = getIntent();
        String address = intent.getStringExtra("web");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(address);
    }

//    @Override
//    public void onBackPressed() {
//        if(webView.canGoBack()){
//            webView.goBack();
//        }else {
//            this.finish();
//        }
//
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String aa = webView.getUrl();
        if (webView.canGoBack()&&event.getKeyCode()==KeyEvent.KEYCODE_BACK
                &&event.getRepeatCount()==0){
            webView.goBack();
            String bb = webView.getUrl();
            if (aa==bb){
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
