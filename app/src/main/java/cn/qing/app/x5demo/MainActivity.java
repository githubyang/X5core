package cn.qing.app.x5demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Handler;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import cn.qing.app.x5demo.WebViewJavaScriptFunction;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_URL = "web_url";

    WebView x5WebView;
    ProgressBar progressBar;
    Context context;
    Handler handler;
    private int num=0;
    private String msg="Hello world!";
    private static final int MSG=0;
    private static final int NUM=1;
    private static final int MSG_SUBMIT=2;
    String url = "http://s.m.taobao.com/h5?search-btn=&event_submit_do_new_search_auction=1&_input_charset=utf-8&topSearch=1&atype=b&searchfrom=1&action=home%3Aredirect_app_action&from=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        initArgs();
        initWebView();
        x5WebView.addJavascriptInterface(new WebViewJavaScriptFunction() {

            @Override
            public void onJsFunctionCalled(String tag) {
                // TODO Auto-generated method stub

            }

            ///////////////////////////////////////////////
            //javascript to java methods
            @JavascriptInterface
            public void onSubmit(String s) {
                Log.i("jsToAndroid", "onSubmit happend!");
                MainActivity.this.msg = s;
                Message.obtain(handler, MSG).sendToTarget();//消息机制没写了  太晚了
            }

            @JavascriptInterface
            public void onSubmitNum(String s) {
                Log.i("jsToAndroid", "onSubmitNum happend!");
                MainActivity.this.num = Integer.parseInt(s);
                Message.obtain(handler, NUM).sendToTarget();
            }


            /**
             * java 调用 js方法 并且 传值
             * 步骤：1、调用 js函数  2、js回调一个android方法得到参数  3、js处理函数
             * @return
             */
            @JavascriptInterface
            public String getAndroidMsg() {
                Log.i("jsToAndroid", "onSubmitNum happend!");
                return MainActivity.this.msg;
            }

            @JavascriptInterface
            public String getAndroidNum() {
                Log.i("jsToAndroid", "onSubmitNum happend!");
                return String.valueOf(MainActivity.this.num);
            }


            /**
             * 各种类型的传递
             */
            @JavascriptInterface
            public void getManyValue(String key, String value) {

                Log.i("jsToAndroid", "get key is:" + key + "  value is:" + value);
            }

            /**
             * 关闭当前的窗口
             */
            @JavascriptInterface
            public void closeCurrentWindow() {
                MainActivity.this.finish();
            }
        }, "Android");
    }

    private void initArgs() {
        Intent intent = getIntent();
        if (intent.hasExtra(KEY_URL)) {
            url = intent.getStringExtra(KEY_URL);
        }
    }

    private void initWebView() {
        x5WebView = (WebView) findViewById(R.id.x5WebView);
        //x5WebView.loadUrl(url);
        x5WebView.loadUrl("file:///android_asset/index.html");


        webViewSetting();
        webViewClient();
        webChromeClient();

        x5WebView.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult hitTestResult = x5WebView.getHitTestResult();
                String path = hitTestResult.getExtra();
                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE || hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    Toast.makeText(context, "当前选定的图片的URL是:" + path, Toast.LENGTH_LONG).show();
                }
                return true;
            }

        });

    }

    /**
     * webSetting相关设置
     */
    private void webViewSetting() {
        WebSettings webSetting = x5WebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }


    private void webViewClient() {
        x5WebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (url.indexOf("http:") != -1 || url.indexOf("https:") != -1) {

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra(KEY_URL, url);
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);

            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                Log.i("webViewClient", "onPageStarted");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                Log.i("webViewClient", "onPageFinished");
                MainActivity.this.x5WebView.loadUrl("javascript:alerts(\"原生调用js\")");

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void webChromeClient() {
        x5WebView.setWebChromeClient(new WebChromeClient() {

            View myVideoView;
            View myNormalView;
            IX5WebChromeClient.CustomViewCallback callback;

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                FrameLayout normalView = (FrameLayout) findViewById(R.id.frame_web_video);
                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
                viewGroup.removeView(normalView);
                viewGroup.addView(view);
                myVideoView = view;
                myNormalView = normalView;
                callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(myNormalView);
                }
            }

            @Override
            public void onProgressChanged(WebView webView, int progress) {
                super.onProgressChanged(webView, progress);
                progressBar.setProgress(progress);
                Log.i("webChromeClient", "当前进度：" + progress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (x5WebView != null) {
            x5WebView.destroy();
        }
    }
}
