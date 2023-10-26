package com.hacktivators.mentalhealth.Wellness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.hacktivators.mentalhealth.R;

public class SleepReminderActivity extends AppCompatActivity {

    RelativeLayout music1,music2,music3,music4;

    AppCompatButton music1Play,music2Play,music3Play,music4Play,music1Stop,music2Stop,music3Stop,music4Stop;

    MediaPlayer mediaPlayer1,mediaPlayer2,mediaPlayer3,mediaPlayer4;

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


        mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.song5);
        mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.song6);
        mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.song3);
        mediaPlayer4 = MediaPlayer.create(getApplicationContext(), R.raw.song4);




        music1Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.song);


                mediaPlayer1.start();
                mediaPlayer2.stop();
                mediaPlayer3.stop();
                mediaPlayer4.stop();

                music1Play.setVisibility(View.GONE);
                music1Stop.setVisibility(View.VISIBLE);
                music2Play.setVisibility(View.VISIBLE);
                music2Stop.setVisibility(View.GONE);
                music3Play.setVisibility(View.VISIBLE);
                music3Stop.setVisibility(View.GONE);
                music4Play.setVisibility(View.VISIBLE);
                music4Stop.setVisibility(View.GONE);
            }
        });

        music2Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.song2);
                mediaPlayer2.start();
                mediaPlayer1.stop();
                mediaPlayer3.stop();
                mediaPlayer4.stop();
                music2Play.setVisibility(View.GONE);
                music2Stop.setVisibility(View.VISIBLE);
                music1Play.setVisibility(View.VISIBLE);
                music1Stop.setVisibility(View.GONE);
                music3Play.setVisibility(View.VISIBLE);
                music3Stop.setVisibility(View.GONE);
                music4Play.setVisibility(View.VISIBLE);
                music4Stop.setVisibility(View.GONE);
            }
        });

        music3Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.song);

                mediaPlayer3.start();
                mediaPlayer1.stop();
                mediaPlayer2.stop();
                mediaPlayer4.stop();
                music3Play.setVisibility(View.GONE);
                music3Stop.setVisibility(View.VISIBLE);
                music1Play.setVisibility(View.VISIBLE);
                music1Stop.setVisibility(View.GONE);
                music2Play.setVisibility(View.VISIBLE);
                music2Stop.setVisibility(View.GONE);
                music4Play.setVisibility(View.VISIBLE);
                music4Stop.setVisibility(View.GONE);

            }
        });


        music4Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer4 = MediaPlayer.create(getApplicationContext(), R.raw.song2);

                mediaPlayer4.start();
                mediaPlayer1.stop();
                mediaPlayer2.stop();
                mediaPlayer3.stop();
                music4Play.setVisibility(View.GONE);
                music4Stop.setVisibility(View.VISIBLE);
                music1Play.setVisibility(View.VISIBLE);
                music1Stop.setVisibility(View.GONE);
                music2Play.setVisibility(View.VISIBLE);
                music2Stop.setVisibility(View.GONE);
                music3Play.setVisibility(View.VISIBLE);
                music3Stop.setVisibility(View.GONE);
            }
        });


        music1Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.stop();
                music1Play.setVisibility(View.VISIBLE);
                music1Stop.setVisibility(View.GONE);
            }
        });

        music2Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer2.stop();
                music2Play.setVisibility(View.VISIBLE);
                music2Stop.setVisibility(View.GONE);
            }
        });

        music3Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer3.stop();
                music3Play.setVisibility(View.VISIBLE);
                music3Stop.setVisibility(View.GONE);
            }
        });

        music4Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer4.stop();
                music4Play.setVisibility(View.VISIBLE);
                music4Stop.setVisibility(View.GONE);
            }
        });



    }
}