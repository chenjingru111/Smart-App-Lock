package com.heaven.smartapplock.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.heaven.smartapplock.R;
import com.heaven.smartapplock.service.LockScreenService;
import com.heaven.smartapplock.tools.Random;

import java.util.List;

public class EnterActivity extends AppCompatActivity {
    private int REQUEST_CODE_OVERLAY_PERMISSION=1;
    private ImageView tv_rl2_top_1;
    private ImageView iv_1;
    private ImageView iv_2;
    private ImageView iv_3;
    private Switch switch_1;
    private Switch switch_2;
    private SharedPreferences sharedPreferences;

    private int a=0;
    private int iflock;
    private ImageView iv_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


        sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);

        iflock = sharedPreferences.getInt("iflock", 0);
        getPermition();
        initView();
        startLockScreenService();
        initData();


        tv_rl2_top_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterActivity.this,SettingsActivity.class));
            }
        });
        findViewById(R.id.rl_chooseapp).setOnClickListener(v -> {
                startActivity(new Intent(EnterActivity.this,ChooseAppActivity.class));

        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        startLockScreenService();
    }

    private void startLockScreenService() {

        int iflock1 = sharedPreferences.getInt("iflock", 0);
        Log.e("iflock", String.valueOf(iflock1));

        if (iflock1==1) {
            startService(new Intent(this, LockScreenService.class));
        }
    }

    private void initView() {
        tv_rl2_top_1 = (ImageView) findViewById(R.id.tv_rl2_top_1);
        iv_1 = (ImageView) findViewById(R.id.iv_1);
        iv_2 = (ImageView) findViewById(R.id.iv_2);
        iv_3 = (ImageView) findViewById(R.id.iv_3);
        iv_settings = (ImageView) findViewById(R.id.iv_settings);
    }
    private void initData() {
        Random.getRandomAppIcon(this,iv_1);
        Random.getRandomAppIcon(this,iv_2);
        Random.getRandomAppIcon(this,iv_3);
        Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                View view = LayoutInflater.from(EnterActivity.this).inflate(R.layout.popwindow_item1,null);
                final PopupWindow popWindow = new PopupWindow(view, phoneWide_1_3(),phoneHight());//这里的长宽是取得的屏幕长宽代码见末端
                switch_1 = (Switch) view.findViewById(R.id.switch_1);
                switch_2 = (Switch) view.findViewById(R.id.switch_2);
                TextView tv_getpermission = (TextView) view.findViewById(R.id.tv_getpermission);

                if (Settings.canDrawOverlays(EnterActivity.this)){
                    switch_2.setChecked(true);
                }
                //TODO
                int color = ContextCompat.getColor(EnterActivity.this, R.color.blue);
                ColorStateList trackColor = ColorStateList.valueOf(color);
                //设置开关颜色
                switch_1.setTrackTintList(trackColor);
                switch_2.setTrackTintList(trackColor);

                permissionGet_switch_1();
                switch_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (switch_1.isChecked()){
                            a=1;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("iflock", a);
                            editor.apply();
                        }else {
                            a=0;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("iflock", a);
                            editor.apply();
                        }
                    }
                });

                tv_getpermission.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openAppSettings();
                    }
                });
                /*switch_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ColorStateList colorStateList = ColorStateList.valueOf(Color.BLUE); // 设置开关打开后的颜色为红色
                        switch_1.setThumbTintList(colorStateList); // 应用颜色到开关
                    }
                });*/
                popWindow.setAnimationStyle(R.style.AnimTopRight);
                //部分机型点击空白区域无法使popowindow消失，加上这三句话
                popWindow.setBackgroundDrawable(new BitmapDrawable());
                popWindow.setFocusable(true);
                popWindow.setOutsideTouchable(true);
                popWindow.update();
                popWindow.showAtLocation(view, Gravity.BOTTOM, 0,0);
            }
        };

        handler.postDelayed(runnable, 2000); // 2秒后执行runnable中的代码
    }
    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
    private void getPermition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_OVERLAY_PERMISSION);
            }
        }
    }

    private int phoneHight(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int height = display.getHeight();
        int i = height / 9;
        int i1 = i * 4;
        return i1;
    }

    private int phoneWide_1_3(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int wide = display.getWidth();
        return wide;

    }

    private void permissionGet_switch_1(){
        //boolean serviceRunning = isServiceRunning(this, LockScreenService.class);
        if(iflock==1){
            switch_1.setChecked(true);
        }else {
            switch_1.setChecked(false);
        }
    }

    /*public boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo service : runningServices) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Settings.canDrawOverlays(this)) {
                    // 悬浮窗权限已授予，可以启动Service
                    startService(new Intent(this, LockScreenService.class));
                } else {
                    // 悬浮窗权限未授予，需要提示用户
                    Toast.makeText(this, "请授予悬浮窗权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}