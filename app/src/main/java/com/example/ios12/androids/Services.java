package com.example.ios12.androids;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Services extends AppCompatActivity {
    private Button btnStartServer;
    private Button btnStop;
    private Button btnStart2;
    private Button btnStop2;
    private Button btnBindStart,btnBindStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        //开始服务
        btnStartServer = (Button)this.findViewById(R.id.btn_start);
        btnStartServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this,MyService.class);//指定服务
                intent.putExtra("mp3","yoyoyo.mp3");
                startService(intent);
            }
        });


        //停止服务
        btnStop = (Button)this.findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this,MyService.class);//指定服务
                stopService(intent);
            }
        });

        btnStart2 = (Button)this.findViewById(R.id.btn_start2);
        btnStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mp3 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Timor.mp3";
                Intent intent = new Intent(Services.this,MyIntentService.class);
                intent.putExtra("mp3",mp3);
                startService(intent);
            }
        });


        btnBindStart = (Button)this.findViewById(R.id.btn_bindstart);
        btnBindStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BundleService.MyBinder binder = (BundleService.MyBinder)iBinder;
            BundleService bindService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}
