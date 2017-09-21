package com.example.ios12.androids;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class DownloadByThread extends AppCompatActivity {
    private Button btnGetImage,btnDownloadImage;
    private EditText etImagePath;
    private ImageView ivImage;

    private static final int STATE_SUCESS = 1000;
    private static final int STATE_FAIL = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_by_thread);

        init();
    }

    public void init(){
        etImagePath = (EditText)this.findViewById(R.id.et_imagePath);
        ivImage = (ImageView)this.findViewById(R.id.iv_image);
        btnGetImage = (Button)this.findViewById(R.id.btn_getImage);
        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String path = etImagePath.getText().toString();
                String path = "http://img5.imgtn.bdimg.com/it/u=1970854329,2085827846&fm=27&gp=0.jpg";
                HandlerThread handlerThread = new HandlerThread("ht");//新建一个子线程 名字为 ht
                handlerThread.start();//开始线程
                MyThread myThread = new MyThread(handlerThread.getLooper());//为子线程分配 looper 消息操作对象
                Message msg = myThread.obtainMessage();//获取消息
                msg.obj = path;
                msg.sendToTarget();
            }
        });

        //点击下载图片资源
        btnDownloadImage = (Button)this.findViewById(R.id.btn_downLoadImage);
        btnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = myHandler.obtainMessage();
                        try{
                            String path = "http://img5.imgtn.bdimg.com/it/u=1970854329,2085827846&fm=27&gp=0.jpg";
                            downLoadFileByURL(path);
                            Thread.sleep(1000);
                            message.what = STATE_SUCESS;
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                            message.what = STATE_FAIL;
                        }
                        message.sendToTarget();
                    }
                }).start();
            }
        });
    }

    /**
     * 根据图片的URL资源地址  获取图片的资源信息
     * @param imagePath 图片资源路径
     * @return  Bitmap 类型的位图信息
     */
    public Bitmap getImagePathByUrl(String imagePath){
        try{
            Bitmap bm = null;
            URL url = new URL(imagePath);
            URLConnection conn = url.openConnection();//获取连接通道
            conn.connect();//开通连接
            InputStream inputStream = conn.getInputStream();//获取连接资源
            bm = BitmapFactory.decodeStream(inputStream);//使用位图工厂生成位图资源
            return bm;
        }
        catch (Exception ex){
            show(ex.getMessage());
            return null;
        }
    }


    /**
     * 处理子线程
     */
    private class MyThread extends Handler{
        public MyThread() {
        }

        public MyThread(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Message message = myHandler.obtainMessage();
            try{
                Bitmap bitmap = getImagePathByUrl(msg.obj.toString());
                Thread.sleep(1000);
                message.what = STATE_SUCESS;
                message.obj = bitmap;
            }
            catch (Exception ex){
                ex.printStackTrace();
                message.what = STATE_FAIL;
            }
            message.sendToTarget();

        }
    }


    /**
     * 通过资源地址下载图片资源
     * @param pathUrl   图片的资源地址
     */
    public void downLoadFileByURL(String pathUrl){
        try{
            URL url = new URL(pathUrl);
            URLConnection conn = url.openConnection();//获取连接通道
            conn.connect();//开通连接
            InputStream inputStream = conn.getInputStream();//获取连接资源

            //将图片资源保存到本地
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                show("sd 卡未挂载！");
            }else{
                String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ttt.jpg";
                FileOutputStream fileOutputStream = new FileOutputStream(savePath);
//                int data = inputStream.read();//第一种读取方式  效率较低
//                while (data != -1){
//                    fileOutputStream.write(data);//将data 写入到输出流
//                    data = inputStream.read();
//                }

                int byteSum = 0;//第二种写入方式效率更高
                int byteRead = 0;
                byte [] data = new byte[1024];
                while((byteRead = inputStream.read(data)) != -1){
                    byteSum += byteRead;
                    fileOutputStream.write(data,0,byteRead);
                }

                inputStream.close();//关闭资源
                fileOutputStream.close();
            }
        }
        catch (Exception ex){

        }
    }

    //主线程 handler，处理子线程返回的数据信息
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case STATE_SUCESS:
                    show("进程成功！");
                    Bitmap bitmap = (Bitmap)msg.obj;
                    ivImage.setImageBitmap(bitmap);
                    break;
                case STATE_FAIL:
                    show("进程失败~");
                    break;
                default:
                    show("其他原因！");
                    break;
            }
        }
    };


    
    public void show(String str){
        Toast.makeText(DownloadByThread.this, str, Toast.LENGTH_SHORT).show();
    }

}
