package com.heaven.smartapplock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.heaven.smartapplock.R;
import com.heaven.smartapplock.adapter.AppListAdapter;

import java.util.List;

public class ChooseAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_app);


        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> appList = packageManager.getInstalledApplications(0);
        ListView appListView = findViewById(R.id.app_list);
        AppListAdapter adapter = new AppListAdapter(this, appList);

        appListView.setAdapter(adapter);
    }
}