package com.example.ios12.androids;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Broadcast extends AppCompatActivity {
    private Button btnRegist;
    private Button btnSend;
    private Button btnCancel;

    private  MyBroadcast receiver;
    private String actionName = "MyAction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        btnRegist = (Button)this.findViewById(R.id.btn_regist);
        btnCancel = (Button)this.findViewById(R.id.btn_cancel);
        btnSend = (Button)this.findViewById(R.id.btn_send);

        //注册广播
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Broadcast.this, "注册广播！", Toast.LENGTH_SHORT).show();
                receiver = new MyBroadcast();
                Broadcast.this.registerReceiver(receiver,new IntentFilter(actionName));
            }
        });

        //发送广播
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Broadcast.this, "发送广播！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(actionName);
                intent.putExtra("username","韩雨舫");
                Broadcast.this.sendBroadcast(intent);
            }
        });

        //取消广播
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Broadcast.this.unregisterReceiver(receiver);
            }
        });

    }

}
