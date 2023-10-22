package com.hacktivators.mentalhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class WellnessPagesActivity extends AppCompatActivity {


    RelativeLayout meditation_layout,deepBreathing,yoga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellnesspage);

        meditation_layout = findViewById(R.id.meditation_layout);

        deepBreathing = findViewById(R.id.breathing_layout);

        yoga = findViewById(R.id.yoga_layout);












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
                startActivity(new Intent(WellnessPagesActivity.this, YogaPageActivity.class));
            }
        });
    }
}