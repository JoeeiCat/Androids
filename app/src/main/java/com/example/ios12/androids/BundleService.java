package com.example.ios12.androids;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class BundleService extends Service {
    MediaPlayer player;

    public BundleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        return super.onUnbind(intent);
    }

    public void playMusic(String mp3){
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
                System.out.print(ex.getMessage());
            }
        }
    }


    public class MyBinder extends Binder{
        public BundleService getService(){
            return BundleService.this;
        }
    }
}
