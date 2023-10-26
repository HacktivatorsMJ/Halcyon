package com.hacktivators.mentalhealth.Chat.Bots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.hacktivators.mentalhealth.CheerfulActivity;
import com.hacktivators.mentalhealth.R;

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


    }
}