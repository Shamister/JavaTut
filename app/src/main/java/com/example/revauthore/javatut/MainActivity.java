package com.example.revauthore.javatut;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    String url = "http://intensiveprogramming101.blogspot.com";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get progressBar reference
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // set progressbar to invisible by default
        progressBar.setVisibility(View.INVISIBLE);

        WebView view = (WebView) findViewById(R.id.webView);
        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // keep staying in the program when opening the url
                return false;
            }
        });
        // enable javascript
        view.getSettings().setJavaScriptEnabled(true);
        // maintain the progress bar while loading the webpage
        final Activity mainActivity = this;
        view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(progress);

                mainActivity.setTitle("Loading... (" + progress + "%)");

                // Return the app name after finish loading
                if (progress == 100) {
                    mainActivity.setTitle(R.string.app_name);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        // load the url
        view.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
