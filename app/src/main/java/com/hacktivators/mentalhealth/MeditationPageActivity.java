package com.hacktivators.mentalhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MeditationPageActivity extends AppCompatActivity {

    RelativeLayout how_to_btn;

    AppCompatSeekBar seekBar;
    TextView timerTxt;
    CountDownTimer countDownTimer;
    AppCompatButton start,stop;
    MediaPlayer mediaPlayer;
    Boolean counterIsActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_page);

        how_to_btn = findViewById(R.id.how_to_btn);
        start = findViewById(R.id.start_btn);
        seekBar = findViewById(R.id.seekbar);
        timerTxt = findViewById(R.id.timer);
        timerTxt.setText("30:00");

        how_to_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeditationPageActivity.this, MeditationGuideActivity.class));
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_timer();
            }
        });



        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
        // I am setting the upper range of the SeekBar with 300, since I
        // want the max limit to be 5 minutes or 300 seconds. Change
        // this as per your need.
        seekBar.setMax(3600);
        // I am setting the current default progress with 30, for 30 seconds
        seekBar.setProgress(1800);
        // Attach setOnSeekBarChangeListener to get notified of the user's actions
        seekBar.setOnSeekBarChangeListener(new AppCompatSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // onProgressChanged() method is invoked if the progress level is changed.
                // Call a method here and pass progress as parameter
                // to update the TextView.
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    private void start_timer() {

        if(!counterIsActive){
            counterIsActive = true;
            seekBar.setEnabled(false);
            start.setText("Stop");
            mediaPlayer.start();

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    reset();


                        mediaPlayer.stop();
                }
            }.start();
        }else{

            reset();
        }
    }

    // Define reset() method
    private void reset() {
        timerTxt.setText("30:00");
        seekBar.setProgress(1800);
        countDownTimer.cancel();
        start.setText("Start");
        mediaPlayer.stop();
        seekBar.setEnabled(true);
        counterIsActive = false;
    }

    // Define update() method
    private void update(int progress) {
        // Divide progress by 60 to get the minutes
        int minutes = progress / 60;
        // Divide progress by 60 and get the remainder for seconds
        int seconds = progress % 60;
        String secondsFinal = "";
        // If the value of seconds is less than or equal to 9,
        // print a leading zero if you want to show seconds in 2 digits format
        if(seconds <= 9){
            secondsFinal = "0" + seconds;
        }else{
            secondsFinal = "" + seconds;
        }
        // Update the SeekBar and TextView
        seekBar.setProgress(progress);
        timerTxt.setText("" + minutes + ":" + secondsFinal);
    }

    // In onPause() and onDestroy(), if the counterIsActive flag is true,
    // cancel countDownTimer.
    @Override
    protected void onPause() {
        super.onPause();
        if(counterIsActive){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(counterIsActive){
            countDownTimer.cancel();
        }
    }
}