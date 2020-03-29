package com.barakeh.corona.scanner;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    NavigationView navigationView;
    ProgressBar bar;
    SwipeRefreshLayout swipe;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        supportRequestWindowFeature (Window.FEATURE_NO_TITLE);
        setContentView (R.layout.activity_main);

        swipe = findViewById (R.id.swipe);

        swipe.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {

                webView.loadUrl ("https://multimedia.scmp.com/widgets/china/wuhanvirus/");

            }
        });

        mFirebaseAnalytics = FirebaseAnalytics.getInstance (this);
        webView = (WebView) findViewById (R.id.wb);
        bar = (ProgressBar) findViewById (R.id.progressBar);
        webView.setWebViewClient (new myWebclient ());
        webView.getSettings ().setJavaScriptEnabled (true);

        webView.loadUrl ("https://multimedia.scmp.com/widgets/china/wuhanvirus/");


        webView.getSettings ().setBuiltInZoomControls (true);
        webView.getSettings ().setUseWideViewPort (true);


        navigationView = findViewById (R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId ()) {
                    case R.id.nav_home:
                        Intent home = new Intent (MainActivity.this, MainActivity.class);
                        startActivity (home);
                        break;

                    case R.id.nav_privacy:
                        Intent intent1 = new Intent (Intent.ACTION_VIEW,
                                Uri.parse ("https://www.privacypolicygenerator.info/live.php?token=P7Kw589VGSkSAGelE0eg6gqn66fLPvd0"));
                        startActivity (intent1);
                        break;
                    case R.id.nav_contact:
                        Intent intent2 = new Intent (Intent.ACTION_VIEW,
                                Uri.parse ("https://wa.me/9647515313382?text=I'm%20interested%20in%20your%20Application"));
                        startActivity (intent2);
                        break;

                    case R.id.nav_about:
                        Intent intent3 = new Intent (MainActivity.this, about.class);
                        startActivity (intent3);
                        break;


                }
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown (int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack ()) {
            webView.goBack ();
            bar.setVisibility (View.VISIBLE);
            return true;
        }
        return super.onKeyDown (keyCode, event);
    }

    public class myWebclient extends WebViewClient {

        @Override
        public void onPageFinished (WebView view, String url) {
            super.onPageFinished (view, url);
            bar.setVisibility (View.GONE);
            swipe.setRefreshing (false);
        }

        @Override
        public void onPageStarted (WebView view, String url, Bitmap favicon) {
            super.onPageStarted (view, url, favicon);
            bar.setVisibility (View.VISIBLE);
        }

    }

}


