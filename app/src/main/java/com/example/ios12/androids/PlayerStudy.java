package com.example.ios12.androids;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.session.MediaController;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.HashMap;

public class PlayerStudy extends AppCompatActivity {
    private Button btnPlayBySP1,btnPlayBySP2,btnPlayBySP3;

    private SoundPool soundPool;
    private HashMap<Integer,Integer> soundMap = new HashMap<Integer,Integer>();

    private VideoView videoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_study);

        InitSound();
        InitMp4();
    }

    /**
     * 加载音频
     */
    public void InitSound(){

        soundPool = new SoundPool(3, AudioManager.STREAM_SYSTEM,0);//创建对象
        soundMap.put(1,soundPool.load(PlayerStudy.this,R.raw.bird,1));
        soundMap.put(2,soundPool.load(PlayerStudy.this,R.raw.check,1));
        soundMap.put(3,soundPool.load(PlayerStudy.this,R.raw.haha,1));

        btnPlayBySP1 = (Button)this.findViewById(R.id.btn_playBySP1);
        btnPlayBySP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundMap.get(1),1,1,0,0,1);
            }
        });

        btnPlayBySP2 = (Button)this.findViewById(R.id.btn_playBySP2);
        btnPlayBySP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundMap.get(2),1,1,0,0,1);
            }
        });

        btnPlayBySP3 = (Button)this.findViewById(R.id.btn_playBySP3);
        btnPlayBySP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundMap.get(3),1,1,0,0,1);
            }
        });
    }

    /**
     * 播放视频
     */
    public void InitMp4(){
        videoView1 = (VideoView)this.findViewById(R.id.vv_videoPlayer);
        String mp4 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/sun.mov";

        android.widget.MediaController mediaController = new android.widget.MediaController(this);
        File file = new File(mp4);
        if(file.exists()){
            videoView1.setVideoPath(file.getAbsolutePath());//指定播放视频文件路径
            videoView1.setMediaController(mediaController);
            videoView1.requestFocus();//让 VideoView 获得焦点
            try{
                videoView1.start();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(PlayerStudy.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(PlayerStudy.this, "文件不存在！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
