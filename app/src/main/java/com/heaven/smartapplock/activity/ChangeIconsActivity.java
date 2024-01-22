package com.heaven.smartapplock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;

import com.heaven.smartapplock.R;

public class ChangeIconsActivity extends AppCompatActivity {

    private ImageView iv_change;

    private int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_icons);

        initView();
        findViewById(R.id.iv_d1).setOnClickListener(v -> {
            iv_change.setImageResource(R.mipmap.ic_launcher);
            a=0;
        });
        findViewById(R.id.iv_v1).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.video1);
            a=1;
        });
        findViewById(R.id.iv_v2).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.video2);
            a=2;
        });
        findViewById(R.id.iv_v3).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.video3);
            a=3;
        });
        findViewById(R.id.iv_v4).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.video4);
            a=4;
        });
        findViewById(R.id.iv_p1).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.photo1);
            a=5;
        });
        findViewById(R.id.iv_p2).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.photo2);
            a=6;
        });
        findViewById(R.id.iv_p3).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.photo3);
            a=7;
        });
        findViewById(R.id.iv_p4).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.photo4);
            a=8;
        });
        findViewById(R.id.iv_t1).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.theme1);
            a=9;
        });
        findViewById(R.id.iv_t2).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.theme2);
            a=10;
        });
        findViewById(R.id.iv_t3).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.theme3);
            a=11;
        });
        findViewById(R.id.iv_t4).setOnClickListener(v -> {
            iv_change.setImageResource(R.drawable.theme4);
            a=12;
        });


        findViewById(R.id.tv_save).setOnClickListener(v -> {
            switch (a){
                case 0:
                    setDefaultAlias("com.heaven.smartapplock.DefaultAliasActivity");
                    break;
                case 1:
                    setDefaultAlias("com.heaven.smartapplock.V1Activity");
                    break;
                case 2:
                    setDefaultAlias("com.heaven.smartapplock.V2Activity");
                    break;
                case 3:
                    setDefaultAlias("com.heaven.smartapplock.V3Activity");
                    break;
                case 4:
                    setDefaultAlias("com.heaven.smartapplock.V4Activity");
                    break;
                case 5:
                    setDefaultAlias("com.heaven.smartapplock.P1Activity");
                    break;
                case 6:
                    setDefaultAlias("com.heaven.smartapplock.P2Activity");
                    break;
                case 7:
                    setDefaultAlias("com.heaven.smartapplock.P3Activity");
                    break;
                case 8:
                    setDefaultAlias("com.heaven.smartapplock.P4Activity");
                    break;
                case 9:
                    setDefaultAlias("com.heaven.smartapplock.T1Activity");
                    break;
                case 10:
                    setDefaultAlias("com.heaven.smartapplock.T2Activity");
                    break;
                case 11:
                    setDefaultAlias("com.heaven.smartapplock.T3Activity");
                    break;
                case 12:
                    setDefaultAlias("com.heaven.smartapplock.T4Activity");
                    break;


            }
        });

    }

    private void initView() {
        iv_change = (ImageView) findViewById(R.id.iv_change);
    }
    public void setDefaultAlias(String activityname) {
        PackageManager packageManager = getPackageManager();
        ComponentName name2 = new ComponentName(this, "com.heaven.smartapplock.V1Activity");
        packageManager.setComponentEnabledSetting(name2, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name3 = new ComponentName(this, "com.heaven.smartapplock.V2Activity");
        packageManager.setComponentEnabledSetting(name3, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name4 = new ComponentName(this, "com.heaven.smartapplock.V3Activity");
        packageManager.setComponentEnabledSetting(name4, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name5 = new ComponentName(this, "com.heaven.smartapplock.V4Activity");
        packageManager.setComponentEnabledSetting(name5, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name6 = new ComponentName(this, "com.heaven.smartapplock.P1Activity");
        packageManager.setComponentEnabledSetting(name6, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name7 = new ComponentName(this, "com.heaven.smartapplock.P2Activity");
        packageManager.setComponentEnabledSetting(name7, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name8 = new ComponentName(this, "com.heaven.smartapplock.P3Activity");
        packageManager.setComponentEnabledSetting(name8, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name9 = new ComponentName(this, "com.heaven.smartapplock.P4Activity");
        packageManager.setComponentEnabledSetting(name9, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name10 = new ComponentName(this, "com.heaven.smartapplock.T1Activity");
        packageManager.setComponentEnabledSetting(name10, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name11 = new ComponentName(this, "com.heaven.smartapplock.T2Activity");
        packageManager.setComponentEnabledSetting(name11, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name12 = new ComponentName(this, "com.heaven.smartapplock.T3Activity");
        packageManager.setComponentEnabledSetting(name12, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name13 = new ComponentName(this, "com.heaven.smartapplock.T4Activity");
        packageManager.setComponentEnabledSetting(name13, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name14 = new ComponentName(this, "com.heaven.smartapplock.DefaultAliasActivity");
        packageManager.setComponentEnabledSetting(name14, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name1 = new ComponentName(this, activityname);
        packageManager.setComponentEnabledSetting(name1, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

}