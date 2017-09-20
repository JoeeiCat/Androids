package com.example.ios12.androids;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {

    private MediaPlayer player;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {//必须重写的一个方法
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.i("hanyf",intent.getStringExtra("mp3"));
        String mp3 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Timor.mp3";
        if (mp3 != ""){
            if (player != null && player.isPlaying()){
                player.stop();
            }
            try{
                player = new MediaPlayer();
                player.setDataSource(mp3);
                player.prepare();//预加载音频
                player.start();
            }
            catch (Exception ex){

            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
