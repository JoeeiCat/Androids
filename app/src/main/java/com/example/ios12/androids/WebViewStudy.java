package com.example.ios12.androids;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class WebViewStudy extends AppCompatActivity {
    private Button btnBaiDu,btnTenXun;
    private WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_study);
        innit();

    }

    public void innit(){
        btnBaiDu = (Button)this.findViewById(R.id.btn_baiDu);
        btnTenXun = (Button)this.findViewById(R.id.btn_tenXun);
        webView1 = (WebView)this.findViewById(R.id.wv_webView1);

        btnBaiDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView1.loadUrl("https://www.baidu.com");

            }
        });

        btnTenXun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView1.loadUrl("https://www.qq.com");
            }
        });
    }

}
