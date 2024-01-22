package com.heaven.smartapplock.tools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Random {
    // 获取应用程序信息和图标的方法
    public  static void getRandomAppIcon(Context context, ImageView imageView) {
        // 获取PackageManager对象
        PackageManager packageManager = context.getPackageManager();

        // 获取手机内的所有应用程序信息
        List<ApplicationInfo> appList = packageManager.getInstalledApplications(0);
        // 随机选择一个应用程序
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomIndex = random.nextInt(appList.size());
        ApplicationInfo selectedApp = appList.get(randomIndex);

        // 获取应用程序的图标
        Drawable appIcon = selectedApp.loadIcon(packageManager);

        imageView.setImageDrawable(appIcon);
    }
}
