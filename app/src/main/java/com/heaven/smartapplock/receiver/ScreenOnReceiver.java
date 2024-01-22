package com.heaven.smartapplock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.heaven.smartapplock.service.LockScreenService;

public class ScreenOnReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 在此处处理接收到的广播
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            context.startService(new Intent(context, LockScreenService.class));
        }
    }
}