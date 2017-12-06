package com.jiny.jiny;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
       // setContentView(R.layout.activity_main);
        webView = new WebView(this);
        linearLayout = findViewById(R.id.linearLayout);
        //webView = findViewById(R.id.webView);
        setContentView(webView);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("TAG", "run: thread running ");;
//            }
//        }, 2000);
        detectKeyboard(webView);

        // detectKeyboard(webView);
        webView.loadUrl(url);
       // setContentView(mWebview);



        //if there is a textview,pass in the location array
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

    public void detectKeyboard(View view){
        final View contentView = view;
        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        Log.d("TAG", "run: thread running ");
                        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {

                                Rect r = new Rect();
                                contentView.getWindowVisibleDisplayFrame(r);
                                screenHeight = contentView.getRootView().getHeight();

                                // r.bottom is the position above soft keypad or device button.
                                // if keypad is shown, the r.bottom is smaller than that before.
                                keypadHeight = screenHeight - r.bottom;
                                Log.wtf("KeyPadHeight",keypadHeight + "");
                                Log.wtf("ScreenHeight",screenHeight + "");
                                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                                    Log.wtf("Keyboard","Shown");
                                }
                                else {
                                    Log.wtf("Keyboard","Not Shown");
                                }
                            }
                        });
                    };
                });
            }
        }, 0, 2000);

    }

}
