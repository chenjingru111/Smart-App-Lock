package com.heaven.smartapplock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.heaven.smartapplock.R;

public class SettingsActivity extends AppCompatActivity {

    private Switch switch_1;
    private SharedPreferences sharedPreferences;
    private int iflock;
    private RelativeLayout rl_changepw;
    private ImageView iv_back;
    private RelativeLayout rl_changeicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        iflock = sharedPreferences.getInt("iflock", 0);

        initView();
        initData();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch_1.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("iflock", 1);
                    editor.apply();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("iflock", 0);
                    editor.apply();
                }
            }
        });
        rl_changepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this,SetActivity.class));
            }
        });
        rl_changeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*setAlias1();
                setDefaultAlias();*/
                startActivity(new Intent(SettingsActivity.this,ChangeIconsActivity.class));
            }

        });
    }

    private void initData() {
        if (iflock==1){
            switch_1.setChecked(true);
        }else {
            switch_1.setChecked(false);
        }
    }

    private void initView() {
        switch_1 = (Switch) findViewById(R.id.switch_1);
        rl_changepw = (RelativeLayout) findViewById(R.id.rl_changepw);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rl_changeicon = (RelativeLayout) findViewById(R.id.rl_changeicon);
    }

    /**
     * 设置默认的别名为启动入口
     */
    public void setDefaultAlias() {
        PackageManager packageManager = getPackageManager();
        ComponentName name1 = new ComponentName(this, "com.heaven.smartapplock.DefaultAliasActivity");
        packageManager.setComponentEnabledSetting(name1, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        ComponentName name2 = new ComponentName(this, "com.heaven.smartapplock.P1Activity");
        packageManager.setComponentEnabledSetting(name2, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 设置别名1为启动入口
     */
    public void setAlias1() {
        PackageManager packageManager = getPackageManager();
        ComponentName name1 = new ComponentName(this, "com.heaven.smartapplock.DefaultAliasActivity");
        packageManager.setComponentEnabledSetting(name1, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName name2 = new ComponentName(this, "com.heaven.smartapplock.P1Activity");
        packageManager.setComponentEnabledSetting(name2, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}