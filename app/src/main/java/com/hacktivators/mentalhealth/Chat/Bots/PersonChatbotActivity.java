package com.hacktivators.mentalhealth.Chat.Bots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.hacktivators.mentalhealth.CheerfulActivity;
import com.hacktivators.mentalhealth.R;
import com.hacktivators.mentalhealth.SarcasticActivity;

public class PersonChatbotActivity extends AppCompatActivity {

    RelativeLayout cheerful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_chatbot);

        cheerful = findViewById(R.id.cheerful_layout);

        cheerful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonChatbotActivity.this, CheerfulActivity.class));
            }
        });


        RelativeLayout sarcastic;

        sarcastic = findViewById(R.id.sarcastic_layout);
        sarcastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonChatbotActivity.this, SarcasticActivity.class));
            }
        });
    }

}
