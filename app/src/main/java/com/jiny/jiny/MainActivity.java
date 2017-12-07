package com.jiny.jiny;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    WebView webView;
    String url = "http://www.flipkart.com";
    boolean flag;
    int screenHeight;
    int keypadHeight;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // webView = new WebView(this);
        //linearLayout = findViewById(R.id.linearLayout);

        webView = findViewById(R.id.webView);
        webView.loadUrl(url);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSavePassword(true);
        webView.setKeepScreenOn(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setSaveFormData(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView,String url){
                detectKeyboard();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView,String url){
                Log.d("TAG", "shouldOverrideUrlLoading:  URL Changed ");
                return false;
            }

            @Override
            public void onPageStarted(WebView webView, String url, Bitmap bitmap){
                Log.d("TAG", "onPageStarted: webview started "+url);
            }
        });



    }

//    public void keyboardViewCheck(View view,int location[]) {
//
//        if (location != null) {
//            int yv = location[1];
//            int effectiveScreenArea = screenHeight - keypadHeight;
//            if (yv > effectiveScreenArea) {
//                Toast.makeText(getApplicationContext(), view.getTransitionName() + " is below", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getApplicationContext(), view.getTransitionName() + " is above", Toast.LENGTH_LONG).show();
//            }
//        } else {
//            Log.d("TAG", "keyboardViewCheck: location is null ");
//        }
//    }

    public void detectKeyboard(){
        //hardcode the parameter here to check

        final int[] location = new int[2];
        location[0] = 300;
        location[1] = 800;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                keypadHeight = screenHeight - rect.height();

                Log.d("ScreenHeight",screenHeight+"");
                Log.d("Rect Height: ",rect.height()+"");

                if(keypadHeight > 100){
                    Log.d("WEBVIEW", "keybaord visible : "+true);
                    Intent serviceIntent = new Intent(getApplicationContext(),KeyboardService.class);
                    serviceIntent.putExtra("flag", "show");
                    if(location[1] < (screenHeight - keypadHeight)){
                        serviceIntent.putExtra("value","above");
                    }else{
                        serviceIntent.putExtra("value","below");
                    }
                    getApplicationContext().startService(serviceIntent);
                }else{
                    Log.d("WEBVIEW", "keybaord hidden : "+false);
                    Intent serviceIntent = new Intent(getApplicationContext(),KeyboardService.class);
                    serviceIntent.putExtra("flag", "hide");
                    if(location[1] < (screenHeight - keypadHeight)){
                        serviceIntent.putExtra("value","above");
                    }else{
                        serviceIntent.putExtra("value","below");
                    }
                    getApplicationContext().startService(serviceIntent);
                }

            }
        },0,2000);

    }

    /*public void detectKeyboard(){
       // final View contentView = view;
        linearLayout.getRootView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                                Rect r = new Rect();
                                linearLayout.getWindowVisibleDisplayFrame(r);
                                screenHeight = linearLayout.getRootView().getHeight();

                                // r.bottom is the position above soft keypad or device button.
                                // if keypad is shown, the r.bottom is smaller than that before.
                                keypadHeight = screenHeight - r.bottom;
                                Log.wtf("KeyPadHeight",keypadHeight + "");
                                Log.wtf("ScreenHeight",screenHeight + "");
                                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                                    //lets assume we have a point in hand,to check whether it is above or not
                                    Log.wtf("Keyboard","Shown");
//                                    int y = new Random().nextInt() % 500;
//                                    if(y < (screenHeight - keypadHeight)){
//                                        Log.d("Point Check", "run: Point is above-> point"+y);
//                                    }else{
//                                        Log.d("Point Check", "run: Point is below->point "+y);
//                                    }
                                }
                                else {
//                                    int y = new Random().nextInt() % 500;
//                                    if(y < (screenHeight - keypadHeight)){
//                                        Log.d("Point Check", "run: Point is above -> "+y);
//                                    }else{
//                                        Log.d("Point Check", "run: Point is below -> "+y);
//                                    }
                                    Log.wtf("Keyboard","Not Shown");
                                }
                        };
        });
    }
*/

}
