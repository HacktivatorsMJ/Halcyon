package com.hacktivators.mentalhealth.Wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NativeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.hacktivators.mentalhealth.Progressive_View_Activity;
import com.hacktivators.mentalhealth.Wellness.Meditation.MeditationPageActivity;
import com.hacktivators.mentalhealth.R;
import com.hacktivators.mentalhealth.Wellness.Yoga.YogaViewActivity;

public class WellnessPagesActivity extends AppCompatActivity {


    RelativeLayout meditation_layout,deepBreathing,yoga,journal,music_layout,naturewalks,progrssive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellnesspage);

        meditation_layout = findViewById(R.id.meditation_layout);

        deepBreathing = findViewById(R.id.breathing_layout);

        yoga = findViewById(R.id.yoga_layout);




        music_layout = findViewById(R.id.music_layout);


        naturewalks = findViewById(R.id.walks_layout);

        progrssive = findViewById(R.id.progrssive);









        music_layout = findViewById(R.id.music_layout);

        meditation_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellnessPagesActivity.this, MeditationPageActivity.class));
            }
        });

        deepBreathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellnessPagesActivity.this, Deep_Breathing_Activity.class));
            }
        });

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellnessPagesActivity.this, YogaViewActivity.class));
            }
        });

        progrssive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellnessPagesActivity.this, Progressive_View_Activity.class));
            }
        });

        naturewalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellnessPagesActivity.this, NatureWalkActivity.class));
            }
        });
    }
}