package com.heaven.smartapplock.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.heaven.smartapplock.OnCircleCompleteListener;
import com.heaven.smartapplock.R;
import com.heaven.smartapplock.activity.SetAgainActivity;
import com.heaven.smartapplock.tools.CircleView;

public class LockScreenService extends Service {

    private WindowManager mWindowManager;
    private View mLockScreenView;
    private CircleView pattern_lock_view_enter;
    private int lastresult;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
        lastresult = sharedPreferences.getInt("result", 0);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                PixelFormat.TRANSLUCENT);
        mLockScreenView = LayoutInflater.from(this).inflate(R.layout.lock_screen, null);
        mWindowManager.addView(mLockScreenView, layoutParams);


        pattern_lock_view_enter = (CircleView) mLockScreenView.findViewById(R.id.pattern_lock_view_enter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        pattern_lock_view_enter.setOnCircleCompleteListener(new OnCircleCompleteListener() {
            @Override
            public void onCircleComplete(String result) {
                String trimmedResult = result.trim();
                int intValue = 0;
                if (!trimmedResult.isEmpty()) {
                    try {
                        intValue = Integer.parseInt(trimmedResult);
                    } catch (NumberFormatException e) {
                        Log.e("NumberFormatException-enter", e.getMessage());
                    }
                }
                if (lastresult==intValue){
                    stopSelf();
                }else {
                    Toast.makeText(LockScreenService.this, "password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWindowManager.removeView(mLockScreenView);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




}

