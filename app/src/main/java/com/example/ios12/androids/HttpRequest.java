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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest extends AppCompatActivity {
    private Button btnGetRequest;
    private TextView tvShowRequest;

    private static final int SUCCESS = 1;
    private static final int FAIL = 2;

    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_request);
        init();
    }

    public void init(){
        btnGetRequest = (Button)this.findViewById(R.id.btn_getReuqest);
        tvShowRequest = (TextView)this.findViewById(R.id.tv_showRequest);
        btnGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandlerThread handlerThread = new HandlerThread("test");
                handlerThread.start();
                MyThread myThread = new MyThread(handlerThread.getLooper());
                Message message = myThread.obtainMessage();
                message.sendToTarget();
            }
        });
    }

    public String getRequestObject(){
        String pathUrl = "http://jxgc.yngsxy.net/cms/userHandler.ashx";
        try{
            URL url = new URL(pathUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data;
            while((data = bufferedReader.readLine())!= null){
                result += data;
            }
            inputStreamReader.close();
            connection.disconnect();
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return result;
        }
    }

    private class MyThread extends Handler{
        public MyThread() {
        }

        public MyThread(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Message message = myHanderl.obtainMessage();
            try{
                String str = getRequestObject();
                if (str == null){
                    message.what = FAIL;
                }
                else{
                    message.what = SUCCESS;
                }
                message.obj = str;
                Thread.sleep(2000);
            }
            catch (Exception ex){
                ex.printStackTrace();
                message.what = FAIL;
            }
            message.sendToTarget();
        }
    }

    Handler myHanderl = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case SUCCESS:
                    show(msg.obj.toString());
                    show("成功！");
                    break;
                case FAIL:
                    show("失败~");
                    break;
                default:
                    show("其他原因");
                    break;
            }
        }
    };

    public void show(String str){
        Toast.makeText(HttpRequest.this, str, Toast.LENGTH_SHORT).show();
    }
}
