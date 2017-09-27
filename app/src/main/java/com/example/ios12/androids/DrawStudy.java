package com.example.ios12.androids;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

public class DrawStudy extends AppCompatActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_study);
        frameLayout = (FrameLayout)this.findViewById(R.id.framelayout1);
        frameLayout.addView(new myView(DrawStudy.this));
    }

    public class myView extends View{
        public myView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);//设置背景颜色为白色
            Paint paint = new Paint();
            paint.setAntiAlias(true);//使用抗锯齿功能，但是会使绘图速度变慢
            paint.setStrokeWidth(3);//设置笔触的宽度
            paint.setColor(Color.GREEN);
            canvas.drawArc(new RectF(10,20,500,510),0,-60,true,paint);
            paint.setColor(Color.RED);
            canvas.drawArc(new RectF(10,20,500,510),0,300,true,paint);
        }
    }
}
