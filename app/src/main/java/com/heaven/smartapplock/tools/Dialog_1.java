package com.heaven.smartapplock.tools;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.heaven.smartapplock.R;
import com.heaven.smartapplock.adapter.AppListAdapter;


public class Dialog_1 extends Dialog  {
    Context mContext;
    private ImageView iv_appicon;
    private TextView message;
    private String ms;

    private Drawable mdrawable;

    public Dialog_1(@NonNull Context context,Drawable drawable) {
        super(context);
        mContext=context;
        //ms=s;
        mdrawable=drawable;
    }

    public Dialog_1(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext=context;

    }

    protected Dialog_1(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_item, null);
        this.setContentView(layout);

        // 设置dialog的宽度和高度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = 700;
        layoutParams.height = 580;
        layoutParams.gravity = Gravity.CENTER; // 居中显示
        getWindow().setAttributes(layoutParams);

        iv_appicon = (ImageView) layout.findViewById(R.id.iv_appicon);
        message = (TextView) layout.findViewById(R.id.message);


        //message.setText(ms);
        iv_appicon.setImageDrawable(mdrawable);



        layout.findViewById(R.id.confirm).setOnClickListener(v -> {
            dismiss();
            Toast.makeText(mContext, "Add app", Toast.LENGTH_SHORT).show();
        });
        layout.findViewById(R.id.cancel).setOnClickListener(v -> {
            dismiss();
        });
    }

}
