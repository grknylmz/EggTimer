package com.saintgames.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerButton;
    boolean counterIsActive = false;
    CountDownTimer countDownTimer;



    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft /  60;
        int seconds = secondsLeft - minutes * 60;

        String secondStrings = "0";

        if (seconds % 10 == 0 || seconds == 0){
            timerTextView.setText( secondStrings + Integer.toString(minutes) + ":" + Integer.toString(seconds) + secondStrings);
        }
        else {
            timerTextView.setText( secondStrings + Integer.toString(minutes) + ":" + Integer.toString(seconds));

        }
    }


    public void controlCountdown(View view) throws InterruptedException {
        if (counterIsActive == false){
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100 , 1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000 );
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cock);
                    mediaPlayer.start();
                }
            }.start();
        }
        else{
            timerSeekBar.setProgress(30);
            timerSeekBar.setEnabled(true);
            counterIsActive = false;
            controllerButton.setText("Go!");
            countDownTimer.cancel();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerTextView  = (TextView)findViewById(R.id.timerTextView);
        controllerButton  = (Button) findViewById(R.id.startButton);
        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
