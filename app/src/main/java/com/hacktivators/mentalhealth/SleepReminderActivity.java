package com.hacktivators.mentalhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SleepReminderActivity extends AppCompatActivity {

    RelativeLayout music1,music2,music3,music4;

    AppCompatButton music1Play,music2Play,music3Play,music4Play,music1Stop,music2Stop,music3Stop,music4Stop;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_reminder);

        music1Play = findViewById(R.id.music1play);
        music2Play = findViewById(R.id.music2play);
        music3Play = findViewById(R.id.music3play);
        music4Play = findViewById(R.id.music4play);

        music1Stop = findViewById(R.id.music1Stop);
        music2Stop = findViewById(R.id.music2Stop);
        music3Stop = findViewById(R.id.music3Stop);
        music4Stop = findViewById(R.id.music4Stop);





        music1Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
                mediaPlayer.start();
                music1Play.setVisibility(View.GONE);
                music1Stop.setVisibility(View.VISIBLE);
            }
        });

        music2Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song2);
                mediaPlayer.start();
                music2Play.setVisibility(View.GONE);
                music2Stop.setVisibility(View.VISIBLE);
            }
        });

        music3Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
                mediaPlayer.start();
                music3Play.setVisibility(View.GONE);
                music3Stop.setVisibility(View.VISIBLE);
            }
        });


        music4Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song2);
                mediaPlayer.start();
                music4Play.setVisibility(View.GONE);
                music4Stop.setVisibility(View.VISIBLE);
            }
        });


        music1Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                music1Play.setVisibility(View.VISIBLE);
                music1Stop.setVisibility(View.GONE);
            }
        });

        music2Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                music2Play.setVisibility(View.VISIBLE);
                music2Stop.setVisibility(View.GONE);
            }
        });

        music3Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                music3Play.setVisibility(View.VISIBLE);
                music3Stop.setVisibility(View.GONE);
            }
        });

        music4Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                music4Play.setVisibility(View.VISIBLE);
                music4Stop.setVisibility(View.GONE);
            }
        });



    }
}