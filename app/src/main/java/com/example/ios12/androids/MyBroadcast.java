package com.example.ios12.androids;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ios12 on 17/9/20.
 * 创建广播接收器
 */

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "监听到了广播！", Toast.LENGTH_SHORT).show();
        if (intent.getAction() == "MyAction"){
            Toast.makeText(context, intent.getStringExtra("username"), Toast.LENGTH_SHORT).show();
        }
    }
}
