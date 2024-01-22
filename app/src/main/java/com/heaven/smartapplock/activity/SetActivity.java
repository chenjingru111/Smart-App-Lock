package com.heaven.smartapplock.activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.heaven.smartapplock.OnCircleCompleteListener;
import com.heaven.smartapplock.R;
import com.heaven.smartapplock.tools.CircleView;

public class SetActivity extends FragmentActivity {

    private CircleView circleView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        initView();

        sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);

        circleView.setOnCircleCompleteListener(new OnCircleCompleteListener() {
            @Override
            public void onCircleComplete(String result) {
                // 在这里处理返回值a

                String trimmedResult = result.trim();
                int intValue = 0;
                if (!trimmedResult.isEmpty()) {
                    try {
                        intValue = Integer.parseInt(trimmedResult);
                    } catch (NumberFormatException e) {
                        Log.e("NumberFormatException", e.getMessage());
                    }
                    }else {
                    Toast.makeText(SetActivity.this, "empty", Toast.LENGTH_SHORT).show();

                }
                // 判断位数大于等于4并且不为空
                if (trimmedResult.length() >= 4 && !trimmedResult.isEmpty()) {
                    Log.d("result", String.valueOf(intValue));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("result", intValue);
                    editor.apply();
                    startActivity(new Intent(SetActivity.this, SetAgainActivity.class));
                    finish();
                }else {
                    Toast.makeText(SetActivity.this, "must >= 4 numbers", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void initView() {
        circleView = (CircleView) findViewById(R.id.pattern_lock_view);
    }
}