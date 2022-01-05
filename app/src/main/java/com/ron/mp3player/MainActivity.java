package com.ron.mp3player;

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
    public Button startButton;
    private boolean isPlayed = false;
    private Handler myHandler = new Handler();
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.playButton);
        mp = MediaPlayer.create(this, R.raw.panda);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

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
                // Toast.makeText(getApplicationContext(), "progress:"+seekBar.getProgress()+" song:"+mp.getDuration(), Toast.LENGTH_SHORT).show();
            }
        });

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
        mp = MediaPlayer.create(this, R.raw.lovenwantitiaudio);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedPanda(View view){
        mp.pause();
        startButton.setText("Play");
        mp = MediaPlayer.create(this, R.raw.panda);
        isPlayed = false;
        seekBar.setProgress(0);
    }
    public void clickedStarboy(View view){
        mp.pause();
        startButton.setText("Play");
        mp = MediaPlayer.create(this, R.raw.starbuyaudio);
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