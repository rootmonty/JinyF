package com.jiny.jiny;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    //ScrollView scrollView;
    MyScrollView scrollView;
    LinearLayout linearLayout;
    TextView tv1,tv2,tv3,tv4,tv5;
    EditText ed1,ed2,ed3,ed4,ed5;
    int screenHeight;
    int scrollViewHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scrollView = findViewById(R.id.scrollView);
        linearLayout = findViewById(R.id.linearLayout);
        tv1 = findViewById(R.id.tv1);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);

        detectKeyboard(scrollView);

//        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                Log.d("TAG", "onScrollChange: anything happening"+i);
//                Log.d("TAG", "onScrollChange: anything happening -"+i1);
//                Log.d("TAG", "onScrollChange: anything happening - -"+i2);
//                Log.d("TAG", "onScrollChange: anything happening - - - "+i);
//            }
//        });

        scrollViewHeight = scrollView.getHeight();
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("TAG", "onTouch: - Scroll Start");
                    scrollView.startScrollerTask();
                }

                return false;
            }

        });
        scrollView.setOnScrollStoppedListener(new MyScrollView.OnScrollStoppedListener() {

            public void onScrollStopped(){
                int rootheight = scrollView.getScrollY();
                float ed1scrollY = ed1.getY()+(rootheight - scrollViewHeight); // For ScrollView
                float ed2scrollY = ed2.getY()+(rootheight - scrollViewHeight); // For ScrollView
                float ed3scrollY = ed3.getY()+(rootheight - scrollViewHeight); // For ScrollView
                float ed4scrollY = ed4.getY()+(rootheight - scrollViewHeight); // For ScrollView
                float ed5scrollY = ed5.getY()+(rootheight - scrollViewHeight); // For ScrollView
                Log.d("TAG", "onScrollStopped: rootheight"+scrollViewHeight);
                Log.d("TAG", "onScrollChange: ed1-"+ed1scrollY);
                Log.d("TAG", "onScrollChange: ed2 -"+ed2scrollY);
                Log.d("TAG", "onScrollChange: ed3 -"+ed3scrollY);
                Log.d("TAG", "onScrollChange: ed4 -"+ed4scrollY);
                Log.d("TAG", "onScrollChange: ed5 -"+ed5scrollY);
                Log.d("TAG", "onScrollStopped: root height - "+rootheight);

                Log.i("TAG", "stopped");

            }
        });

//        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                int ed1scrollY = ed1.getScrollY(); // For ScrollView
//                int ed2scrollY = ed2.getScrollY(); // For ScrollView
//                int ed3scrollY = ed3.getScrollY(); // For ScrollView
//                int ed4scrollY = ed4.getScrollY(); // For ScrollView
//                int ed5scrollY = ed5.getScrollY(); // For ScrollView
//                Log.d("TAG", "onScrollChange: ed1-"+ed1scrollY);
//                Log.d("TAG", "onScrollChange: ed2 -"+ed2scrollY);
//                Log.d("TAG", "onScrollChange: ed3 -"+ed3scrollY);
//                Log.d("TAG", "onScrollChange: ed4 -"+ed4scrollY);
//                Log.d("TAG", "onScrollChange: ed5 -"+ed5scrollY);
//
//
//                // DO SOMETHING WITH THE SCROLL COORDINATES
//            }
//        });
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void detectKeyboard(View view){
        final View contentView = view;
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                contentView.getWindowVisibleDisplayFrame(r);
                screenHeight = contentView.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;
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
    }
}
