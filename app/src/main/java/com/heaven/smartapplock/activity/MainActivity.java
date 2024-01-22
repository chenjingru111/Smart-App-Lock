package com.heaven.smartapplock.activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.heaven.smartapplock.R;
import com.heaven.smartapplock.receiver.ScreenOnReceiver;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScreenOnReceiver receiver = new ScreenOnReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, filter);

        findViewById(R.id.tv_init).setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            int exist = sharedPreferences.getInt("exist", 0);
            if (exist==1){
                startActivity(new Intent(this, EnterActivity.class));
            }else {
                startActivity(new Intent(this, SetActivity.class));
            }
        });

    }
}