package com.example.ios12.androids;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;

/**
 * Created by ios12 on 17/9/20.
 */

public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String mp3 = intent.getStringExtra("mp3");
        MediaPlayer player = new MediaPlayer();
        try{
            player.setDataSource(mp3);
            player.prepare();
            player.start();
        }
        catch (Exception ex){

        }
    }
}
