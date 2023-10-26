package com.hacktivators.mentalhealth.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hacktivators.mentalhealth.OnBoarding.Questtionaire.SetupQuestionActivity;
import com.hacktivators.mentalhealth.R;

public class LonelyActivity extends AppCompatActivity {

    AppCompatButton start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lonely);


        start = findViewById(R.id.start_btn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LonelyActivity.this, SetupQuestionActivity.class));
            }
        });
    }
}