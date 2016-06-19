package cn.qing.app.x5demo;

/**
 * Created by dell on 2016/6/19.
 */


        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;

/**
 9 *
 10 * @{#} SplashActivity.java Create on 2013-5-2 下午9:10:01
 11 *
 12 * class desc:   启动画面
 13 *
 14 * <p>Copyright: Copyright(c) 2013 </p>
 15 * @Version 1.0
 16 * @Author <a href="mailto:gaolei_xj@163.com">Leo</a>
 17 *
 18 *
 19 */
        public class SplashActivity extends AppCompatActivity {

                //延迟3秒
               private static final long SPLASH_DELAY_MILLIS = 6000;

                @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
               setContentView(R.layout.splash);

                // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
               new Handler().postDelayed(new Runnable() {
                      public void run() {
                                goHome();
                            }
                    }, SPLASH_DELAY_MILLIS);
            }

                private void goHome() {
               Intent intent = new Intent(SplashActivity.this, IndexActivity.class);
              SplashActivity.this.startActivity(intent);
               SplashActivity.this.finish();
    }
}
