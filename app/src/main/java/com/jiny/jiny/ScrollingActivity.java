package com.jiny.jiny;

import android.content.Intent;
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
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    //ScrollView scrollView;
    MyScrollView scrollView;
    LinearLayout linearLayout;
    TextView tv1,tv2,tv3,tv4,tv5;
    EditText ed1,ed2,ed3,ed4,ed5;
    int screenHeight;
    int scrollViewHeight;
    boolean flag;
    int keypadHeight;
    int rootheight;
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

        //detectKeyboard(scrollView);

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

            public void onScrollStopped() {
                rootheight = scrollView.getScrollY();
                //viewHideShow();

            }
        });


        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for all children lying below
                //find the coordinates of next views,the middle point

                int[] loc2 = new int[2];
                ed2.getLocationOnScreen(loc2);
                int h2 = loc2[1];
                int[] loc3 = new int[2];
                ed3.getLocationOnScreen(loc3);
                int h3 = loc3[1];
                int[] loc4 = new int[2];
                ed4.getLocationOnScreen(loc4);
                int h4 = loc4[1];
                int[] loc5 = new int[2];
                ed5.getLocationOnScreen(loc5);
                int h5 = loc5[1];

                if (flag) {
                    boolean diff2 = (h2 >(screenHeight-keypadHeight));
                    boolean diff3 = (h3 > (screenHeight-keypadHeight));
                    boolean diff4 = (h4 > (screenHeight-keypadHeight));
                    boolean diff5 = (h5 > (screenHeight-keypadHeight));

                    if (diff2) {
                        Toast.makeText(getApplicationContext(), "2 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "2 is above", Toast.LENGTH_LONG).show();
                    }
                    if (diff3) {
                        Toast.makeText(getApplicationContext(), "3 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "3 is above", Toast.LENGTH_LONG).show();
                    }
                    if (diff4) {
                        Toast.makeText(getApplicationContext(), "4 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "4 is above", Toast.LENGTH_LONG).show();
                    }
                    if (diff5) {
                        Toast.makeText(getApplicationContext(), "5 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "5 is above", Toast.LENGTH_LONG).show();
                    }
                }
                Log.d("TAG", "onScrollStopped: rootheight" + scrollViewHeight);
                Log.d("TAG", "onScrollChange: ed2 -" + h2);
                Log.d("TAG", "onScrollChange: ed3 -" + h3);
                Log.d("TAG", "onScrollChange: ed4 -" + h4);
                Log.d("TAG", "onScrollChange: ed5 -" + h5);
                Log.d("TAG", "onScrollStopped: root height - " + rootheight);

            }
        });

        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for all children lying below
                //find the coordinates of next views,the middle point

                int[] loc1 = new int[2];
                ed5.getLocationOnScreen(loc1);
                int h = loc1[1];

                if (flag) {
                    boolean diff5 = (h > keypadHeight+100);

                    if (diff5) {
                        Toast.makeText(getApplicationContext(), "5 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "5 is above", Toast.LENGTH_LONG).show();
                    }
                }
                Log.d("TAG", "onScrollStopped: rootheight" + scrollViewHeight);
                Log.d("TAG", "onScrollChange: ed5 -" + h);
                Log.d("TAG", "onScrollStopped: root height - " + rootheight);

            }
        });

        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for all children lying below
                //find the coordinates of next views,the middle point


                int[] loc1 = new int[2];
                ed3.getLocationOnScreen(loc1);
                int h3 = loc1[1];
                int[] loc2 = new int[2];
                ed4.getLocationOnScreen(loc2);
                int h4 = loc2[1];
                int[] loc3 = new int[2];
                ed5.getLocationOnScreen(loc3);
                int h5 = loc3[1];
                if (flag) {
                    boolean diff3 = (h3 > (screenHeight-keypadHeight)) ;
                    boolean diff4 = (h4 > (screenHeight-keypadHeight));
                    boolean diff5 = (h5 > (screenHeight-keypadHeight));

                    if (diff3) {
                        Toast.makeText(getApplicationContext(), "3 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "3 is above", Toast.LENGTH_LONG).show();
                    }
                    if (diff4) {
                        Toast.makeText(getApplicationContext(), "4 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "4 is above", Toast.LENGTH_LONG).show();
                    }
                    if (diff5) {
                        Toast.makeText(getApplicationContext(), "5 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "5 is above", Toast.LENGTH_LONG).show();
                    }
                }
                Log.d("TAG", "onScrollStopped: rootheight" + scrollViewHeight);
                Log.d("TAG", "onScrollChange: ed3 -" + h3);
                Log.d("TAG", "onScrollChange: ed4 -" + h4);
                Log.d("TAG", "onScrollChange: ed5 -" + h5);
                Log.d("TAG", "onScrollStopped: root height - " + rootheight);

            }
        });

        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for all children lying below
                //find the coordinates of next views,the middle point

                int[] loc4 = new int[2];
                ed4.getLocationOnScreen(loc4);
                int h4 = loc4[1];
                int[] loc5 = new int[2];
                ed5.getLocationOnScreen(loc5);
                int h5 = loc5[1];

                if (flag) {
                    boolean diff4 = (h4 > (screenHeight-keypadHeight));
                    boolean diff5 = (h5 > (screenHeight-keypadHeight));

                    if (diff4) {
                        Toast.makeText(getApplicationContext(), "4 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "4 is above", Toast.LENGTH_LONG).show();
                    }
                    if (diff5) {
                        Toast.makeText(getApplicationContext(), "5 is below", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "5 is above", Toast.LENGTH_LONG).show();
                    }
                }

                Log.d("TAG", "onScrollChange: ed4 -" + h4);
                Log.d("TAG", "onScrollChange: ed5 -" + h5);
                Log.d("TAG", "onScrollStopped: root height - " + rootheight);
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
