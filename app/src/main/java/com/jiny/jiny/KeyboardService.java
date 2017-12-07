package com.jiny.jiny;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by shreyanshsharma on 12/7/17.
 */

public class KeyboardService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        String flag = intent.getStringExtra("flag");
        String value = intent.getStringExtra("value");
        Log.d(TAG, "onStartCommand: flag - - - - ->"+flag);
        if("show".equals(flag)){
            if(value.equals("above")){
                Log.d(TAG, "onStartCommand: No arrow needed ");
            }else{
                //insert the overlay here
                ImageView bugImage = new ImageView(getApplicationContext());
                bugImage.setImageResource(R.drawable.arrow);
                overlayArrow(bugImage);

                Log.d(TAG, "onStartCommand: arrow needed ");
            }

        }else{
            //hide the overlay image
            Log.d(TAG, "onStartCommand: No arrow needed ");
//            Log.d(TAG, "onStartCommand: flag is coming "+flag);
//            Toast.makeText(getApplicationContext(),"Keyboard is hidden",Toast.LENGTH_SHORT).show();
        }
        return START_STICKY;
    }

    private void overlayArrow(final ImageView bugImage){
        final WindowManager wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        int windowManagerLayoutParamType = WindowManager.LayoutParams.TYPE_PHONE;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            windowManagerLayoutParamType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

       final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                100,
                100,
                windowManagerLayoutParamType,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        
        wManager.addView(bugImage,layoutParams);

    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public static View makeMeBlink(View view, int duration, int offset) {

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(duration);
        anim.setStartOffset(offset);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);
        return view;
    }
}
