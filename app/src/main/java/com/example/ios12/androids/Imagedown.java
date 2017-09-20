package com.example.ios12.androids;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Imagedown extends AppCompatActivity {
    private EditText inputUrl;//地址
    private Button btnDown;//下载按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagedown);

        Inite();

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    /**
     * 按钮的绑定
     */
    private void Inite(){
        inputUrl = (EditText)this.findViewById(R.id.input_url);
        btnDown = (Button)this.findViewById(R.id.btn_dowm);
    }

    /**
     * 检测sd 卡是否挂载
     * @return true or false
     */
    public boolean sdCardCheck(){
        try{
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        }
        catch (Exception ex){
            show("sd 卡检测：：："+ex.getMessage());
            return false;
        }
    }

    /**
     * 下载图片
     * @param imgPath   图片的URL地址
     */
    public void downImage(String imgPath){

    }


    /**
     * Toast 相关的show 方法
     * @param str   输出的字符串
     */
    public void show(String str){
        Toast.makeText(Imagedown.this, str, Toast.LENGTH_SHORT).show();
    }

}
