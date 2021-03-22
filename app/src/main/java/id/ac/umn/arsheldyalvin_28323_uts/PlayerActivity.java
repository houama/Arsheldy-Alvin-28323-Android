package id.ac.umn.arsheldyalvin_28323_uts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlayerActivity extends AppCompatActivity {

    Bundle songInfo;
    int position;
    ArrayList musicList;
    TextView player_time, player_duration, songName;
    SeekBar seek_bar;
    ImageView btn_previous, btn_play, btn_pause, btn_next;

    static MediaPlayer mediaPlayer;
//    Handler handler = new Handler();
//    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        seek_bar = findViewById(R.id.seek_bar);
        btn_previous = findViewById(R.id.btn_rewind);
        btn_play = findViewById(R.id.btn_play);
        btn_next = findViewById(R.id.btn_forward);
        songName = findViewById(R.id.songName);

//        if(mediaPlayer!=null){
//            mediaPlayer.stop();
//        }

        Intent intent = getIntent();
        songInfo = intent.getExtras();

        musicList = (ArrayList) songInfo.getStringArrayList("List");
        position = songInfo.getInt("position",0);

        Log.d("Array", String.valueOf(position));

        initializeMusicPlayer(position);

//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                seek_bar.setProgress(mediaPlayer.getCurrentPosition());
//
//                handler.postDelayed(this,500);
//            }
//        };

        //int duration = mediaPlayer.getDuration();
        //String mDuration = convertFormat(duration);

        //player_duration.setText(mDuration);

//        btn_play.setOnClickListener(new View.OnContextClickListener(){
//            @Override
//            public void onClick(View v){
//                btn_play.setVisibility(View.GONE);
//                btn_pause.setVisibility(View.VISIBLE);
//                mediaPlayer.start();
//                seek_bar.setMax(mediaPlayer.getDuration());
//                handler.postDelayed(runnable,0);
//            }
//        });

//        btn_pause.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                btn_pause.setVisibility(View.GONE);
//                btn_play.setVisibility(View.VISIBLE);
//                mediaPlayer.pause();
//                handler.removeCallbacks(runnable);
//            }
//        };

        btn_play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                play();
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(position <= 0){
                    position = musicList.size();
                }else{
                    position++;
                }
                initializeMusicPlayer(position);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(position < musicList.size() -1){
                    position++;
                }else{
                    position = 0;
                }
                initializeMusicPlayer(position);
            }
        });
    }

    private void initializeMusicPlayer(int position){
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.reset();
        }

        String title = (String) musicList.get(position);
        String name = title.substring(title.lastIndexOf("/") + 1);
        songName.setText(name);

        Uri uri = Uri.parse(musicList.get(position).toString());

        mediaPlayer = MediaPlayer.create(this,uri);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seek_bar.setMax(mediaPlayer.getDuration());

                btn_play.setImageResource(R.drawable.ic_pause);

                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btn_play.setImageResource(R.drawable.ic_play);
            }
        });

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    seek_bar.setProgress(progress);
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mediaPlayer!=null){
                    try{
                        if(mediaPlayer.isPlaying()){
                            Message message = new Message();
                            message.what = mediaPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();




    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            seek_bar.setProgress(msg.what);
        }
    };

    private void play(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            btn_play.setImageResource(R.drawable.ic_play);
        }else{
            mediaPlayer.start();
            btn_play.setImageResource(R.drawable.ic_pause);
        }
    }
}
