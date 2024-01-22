package com.heaven.smartapplock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.heaven.smartapplock.OnCircleCompleteListener;
import com.heaven.smartapplock.R;
import com.heaven.smartapplock.tools.CircleView;

public class SetAgainActivity extends AppCompatActivity {
    private CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setagain);

        initView();

        SharedPreferences sharedPreferences = getSharedPreferences();
        int lastresult = sharedPreferences.getInt("result", 0);

        circleView.setOnCircleCompleteListener(new OnCircleCompleteListener() {
            @Override
            public void onCircleComplete(String result) {
                // 在这里处理返回值a
                Log.d("result",result);

                String trimmedResult = result.trim();

                int intValue = 0;
                if (!trimmedResult.isEmpty()) {
                    try {
                        intValue = Integer.parseInt(trimmedResult);
                    } catch (NumberFormatException e) {
                        Log.e("NumberFormatException", e.getMessage());
                    }
                }else {
                    Toast.makeText(SetAgainActivity.this, "empty", Toast.LENGTH_SHORT).show();

                }
                if (lastresult==intValue){

                    SharedPreferences.Editor editor_if_exist = sharedPreferences.edit();
                    editor_if_exist.putInt("exist",1);
                    editor_if_exist.apply();

                    startActivity(new Intent(SetAgainActivity.this,EnterActivity.class));
                    finish();
                }else {
                    Toast.makeText(SetAgainActivity.this, "password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        circleView = (CircleView) findViewById(R.id.pattern_lock_view_2);

    }

    private SharedPreferences getSharedPreferences() {
        return getSharedPreferences("key", MODE_PRIVATE);
    }
}