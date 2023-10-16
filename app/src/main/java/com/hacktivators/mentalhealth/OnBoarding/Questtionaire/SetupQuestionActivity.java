package com.hacktivators.mentalhealth.OnBoarding.Questtionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.hacktivators.mentalhealth.R;

public class SetupQuestionActivity extends AppCompatActivity {

    AppCompatButton next;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupq);

        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupQuestionActivity.this, SetupQuestion1Activity.class));
            }
        });


    }

}
