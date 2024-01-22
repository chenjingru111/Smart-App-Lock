package com.heaven.smartapplock.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.smartapplock.R;
import com.heaven.smartapplock.tools.Dialog_1;

import java.util.List;

public class AppListAdapter extends BaseAdapter  {
    private Context context;
    private List<ApplicationInfo> appList;

    public AppListAdapter(Context context, List<ApplicationInfo> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_app, parent, false);

        ImageView icon = convertView.findViewById(R.id.app_icon);
        TextView name = convertView.findViewById(R.id.app_name);

        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> appList = packageManager.getInstalledApplications(0);

        ApplicationInfo appInfo = appList.get(position);
        icon.setImageDrawable(appInfo.loadIcon(packageManager));
        name.setText(appInfo.loadLabel(packageManager));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = appInfo.packageName;
                System.out.println("Clicked app package name: " + packageName);
                Drawable drawable = appInfo.loadIcon(packageManager);

                Dialog_1 dialog1 = new Dialog_1(context,drawable);
                dialog1.show();
            }
        });


        return convertView;
    }


}