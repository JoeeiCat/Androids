package com.example.ios12.androids;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class ListViewOnICON extends AppCompatActivity {
    private ListView listview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_on_icon);

        listview1 = (ListView)this.findViewById(R.id.lv_listView1);

//        int[] images = new int[]{
//                R.drawable.icon2,
//                R.drawable.icon3,
//                R.drawable.icon4,
//                R.drawable.icon5,
//                R.drawable.icon6,
//                R.drawable.icon7
//        };

        String[] title = new String[]{
                      "保密设置",
                      "安全",
                      "系统设置",
                      "上网",
                      "我的文档",
                      "GPS导航"
        };
    }

}
