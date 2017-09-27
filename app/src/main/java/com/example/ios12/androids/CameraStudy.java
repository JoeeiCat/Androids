package com.example.ios12.androids;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class CameraStudy extends AppCompatActivity {
    private Button btnPhoto,btnPrive;

    private Camera camera;//相机对象
    private boolean isPreview;//是否为预览模式

    private SurfaceView surfaceView;//显示相机预览照片

    private SurfaceHolder sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置为全屏显示，且必须放在setContentView 之前
        setContentView(R.layout.activity_camera_study);
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //因为 拍摄到的照片要放在 sd 卡上面
            show("未检测出sd 卡！");
        }
        surfaceView = (SurfaceView)this.findViewById(R.id.sv_surfaceView1);
        sh = surfaceView.getHolder();
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置 SurfaceHolder 不设置缓存

        init();
    }

    public void init(){
        btnPhoto = (Button)this.findViewById(R.id.btn_photo);
        btnPrive = (Button)this.findViewById(R.id.btn_preview);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show("没有？");
                btnTakePhoto();
            }
        });

        btnPrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPreview();
            }
        });
    }

    /**
     * 预览
     */
    public void btnPreview(){
        try{
            if (!isPreview){
                camera = Camera.open();
            }
            camera.setPreviewDisplay(sh);//设置用于显示预览的 SurfaceView
            Camera.Parameters parametes = camera.getParameters();//获取相机参数
            parametes.setPictureSize(640,480);//设置预览画面尺寸
            parametes.setPictureFormat(PixelFormat.JPEG);//指定图片格式
            parametes.set("jpeg-quality",80);//设置图片质量
            parametes.setPictureSize(640,480);//拍摄图片的尺寸

            camera.setParameters(parametes);//重新设置相机参数
            camera.startPreview();//开始预览
            camera.autoFocus(                                                                                                                               null);//设置自动聚焦
        }
        catch (Exception e){
            show(e.getMessage());
        }
    }


    public void btnTakePhoto(){
        try{
            if (camera == null){
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        camera.takePicture(null,null,jpeg);
                    }
                });
            }
        }
        catch (Exception e){
            show(e.getMessage());
        }
    }

    //实现拍照的回调接口
    Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            //根据拍照所得的数据创建位图
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            //加载文件的对应布局资源
            View saveView = getLayoutInflater().inflate(R.layout.save,null);
            final EditText photoName = (EditText)saveView.findViewById(R.id.et_photoName);
            final ImageView show = (ImageView)saveView.findViewById(R.id.iv_show);
            show.setImageBitmap(bitmap);
            camera.stopPreview();//停止预览
            isPreview = false;
            //使用对话框显示saveDialog 组件
            new AlertDialog.Builder(CameraStudy.this).setView(saveView).setPositiveButton("保存", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    File file = new File("/sdcard/pictures/" + photoName.getText().toString() + ".jpg");
                    try{
                        show("调用了拍照的方法");
                        file.createNewFile();
                        FileOutputStream fileOS = new FileOutputStream(file);
                        //将图片压缩为jpeg格式输出到输出流对象中
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOS);
                        fileOS.flush();
                        fileOS.close();
                        isPreview = true;
                        resetCamera();
                    }
                    catch (Exception e){
                        show(e.getMessage());
                    }
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    isPreview = true;
                    resetCamera();//重新预览
                }
            }).show();
        }
    };

    //开启预览
    private void resetCamera(){
        if (isPreview){
            camera.startPreview();
        }
    }
    
    public void show(String str){
        Toast.makeText(CameraStudy.this, str, Toast.LENGTH_SHORT).show();
    }

}
