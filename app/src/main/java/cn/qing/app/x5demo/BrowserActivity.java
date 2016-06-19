package cn.qing.app.x5demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
/**
 * Created by dell on 2016/6/18.
 */
public class BrowserActivity extends AppCompatActivity {
    public WebView x5WebView;

    public BrowserActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    //开启硬件加速
    public void isHardwareAccelerated(){
        x5WebView.getView().isHardwareAccelerated();
    }
    //X5设置
    public void x5WebViewSetting(){}

    public void x5WebViewClient(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (x5WebView != null) {
            x5WebView.destroy();
        }
    }

    public void addJavascriptInterface(WebViewJavaScriptFunction webViewJavaScriptFunction, String android) {
    }

    public void loadUrl(String s) {
    }

    public void getSettings(){}
}
