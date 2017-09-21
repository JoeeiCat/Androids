package com.example.ios12.androids;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HandleStudy extends AppCompatActivity {
    private Button btnGetMainHandleID,btnStartHandle;
    private TextView tvShow;//每隔一秒 递增显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_study);
        tvShow = (TextView)this.findViewById(R.id.tvNumber);

        //获取主线程id
        btnGetMainHandleID = (Button)this.findViewById(R.id.btn_getMainHandle);
        btnGetMainHandleID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long mianTreadId = Thread.currentThread().getId();//主线程的id是 1
                show(mianTreadId.toString());
            }
        });

        //开启一个子线程
        btnStartHandle = (Button)this.findViewById(R.id.btn_startHandle);

        btnStartHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    HandlerThread handler = new HandlerThread("ht");
                    handler.start();
                    showNumHandler shows = new showNumHandler(handler.getLooper());

                    Message mes = shows.obtainMessage();
                    mes.sendToTarget();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                            try{
//                                Message msg = mainHandler.obtainMessage();
//                                msg.what = 1;
//                                msg.sendToTarget();
//                            }
//                            catch(Exception ex){
////                                show(ex.getMessage());
//                            }
//                    }
//                }).start();
//                try{
//                    Thread.sleep(1000);
//                }
//                catch (Exception ex){
//                    ex.printStackTrace();
//                }
//                new myThread().run();
//                for (int i=0;i<10;i++){
//                    new myThread().run();
//                }
            }
        });
    }


//    //线程的第二种创建方式
//    private class myThread implements Runnable{
//        @Override
//        public void run() {
//            for (int i =0;i<10;i++){
//                try{
//                    Message msg = mainHandler.obtainMessage();
//                    msg.what = i;
//                    msg.sendToTarget();
//                    Thread.sleep(1000);
//                }
//                catch(Exception ex){
//                    show(ex.getMessage());
//                }
//            }
//        }
//    }


//    private class  myHandler extends  Handler{
//
//    }
    
    public void show(String str){
        Toast.makeText(HandleStudy.this, str, Toast.LENGTH_SHORT).show();
    }



    //子线程
    private class showNumHandler extends  Handler{
        public showNumHandler() {
        }

        public showNumHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i=0;i<10;i++){
                Message msgs = mainHandler.obtainMessage();
                msgs.what = i;
                msgs.sendToTarget();
                try{
                    Thread.sleep(1000);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    //新建一个主线程的代理者  默认是主线程的Looper，用来处理子线程上的事件
    Handler mainHandler = new Handler(){
//        @Override
//        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
//            tvShow.setText(tvShow.getText()+ String.valueOf(msg.what));
//            return super.sendMessageAtTime(msg, uptimeMillis);
//        }

        @Override
        public void handleMessage(Message msg) {
            tvShow.setText(tvShow.getText()+ String.valueOf(msg.what));
            super.handleMessage(msg);
        }
    };

}
