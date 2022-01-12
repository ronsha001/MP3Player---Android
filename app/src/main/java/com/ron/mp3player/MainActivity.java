package com.ron.mp3player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private int songPlaying;
    public Button startButton;
    private boolean isPlayed = false;
    private Handler myHandler = new Handler();
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (seekBar == null){
            startButton = (Button)findViewById(R.id.playButton);

            songPlaying = R.raw.panda;
            mp = MediaPlayer.create(this, songPlaying);

            seekBar = (SeekBar) findViewById(R.id.seekBar);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                double position = mp.getDuration() * ((double)seekBar.getProgress() / 100);
                mp.seekTo((int)position);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()){
            mp.stop();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        isPlayed = false;
        outState.putInt("POSITION_STATE", mp.getCurrentPosition());
        outState.putInt("SONG_PLAYING", songPlaying);
        outState.putBoolean("IS_PLAYED", isPlayed);
        outState.putInt("SEEK_BAR_PROGRESS", seekBar.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        songPlaying = savedInstanceState.getInt("SONG_PLAYING");
        mp = MediaPlayer.create(this, savedInstanceState.getInt("SONG_PLAYING"));
        mp.seekTo(savedInstanceState.getInt("POSITION_STATE"));
        isPlayed = savedInstanceState.getBoolean("IS_PLAYED");

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(savedInstanceState.getInt("SEEK_BAR_PROGRESS"));
    }

    public void stop(View view){
        mp.pause();
        mp.seekTo(0);
        isPlayed = false;
        startButton.setText("Play");
        seekBar.setProgress(0);
        myHandler.removeCallbacks(seekBarProgressThread);
    }

    public void start(View view) {

        if (isPlayed) {
            mp.pause();
            isPlayed = false;
            startButton.setText("Play");
            myHandler.removeCallbacks(seekBarProgressThread);
        } else {
            mp.start();
            isPlayed = true;
            startButton.setText("Pause");
            myHandler.post(seekBarProgressThread);
        }
    }

    private Runnable seekBarProgressThread = new Runnable() {
        @Override
        public void run() {
            double position = (double) mp.getCurrentPosition() / mp.getDuration() * 100;
            seekBar.setProgress((int)position);
            if (mp.getCurrentPosition() < mp.getDuration()-1){
                myHandler.post(this);
            } else {
                startButton.setText("Play");
                isPlayed = false;
                seekBar.setProgress(0);
                mp.seekTo(0);
                myHandler.removeCallbacks(this);
            }
            if (!isPlayed) {
                myHandler.removeCallbacks(this);
            }
        }

    };

    public void clickedNwantiti(View view){
        mp.pause();
        startButton.setText("Play");
        songPlaying = R.raw.lovenwantitiaudio;
        mp = MediaPlayer.create(this, songPlaying);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedPanda(View view){
        mp.pause();
        startButton.setText("Play");
        songPlaying = R.raw.panda;
        mp = MediaPlayer.create(this, songPlaying);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedStarboy(View view){
        mp.pause();
        startButton.setText("Play");
        songPlaying = R.raw.starbuyaudio;
        mp = MediaPlayer.create(this, songPlaying);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedBlindingLights(View view){
        mp.pause();
        startButton.setText("Play");
        songPlaying = R.raw.blinding_lights;
        mp = MediaPlayer.create(this, songPlaying);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedTakeMyBreath(View view){
        mp.pause();
        startButton.setText("Play");
        songPlaying = R.raw.take_my_breath_audio;
        mp = MediaPlayer.create(this, songPlaying);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedBadHabits(View view){
        mp.pause();
        startButton.setText("Play");
        songPlaying = R.raw.bad_habits_audio;
        mp = MediaPlayer.create(this, songPlaying);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedStay(View view){
        mp.pause();
        startButton.setText("Play");
        songPlaying = R.raw.stay_audio;
        mp = MediaPlayer.create(this, songPlaying);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void rewind(View view) {
        mp.seekTo(mp.getCurrentPosition() - 5000);
    }

    public void forward(View view) {
        mp.seekTo(mp.getCurrentPosition() + 5000);
    }

}