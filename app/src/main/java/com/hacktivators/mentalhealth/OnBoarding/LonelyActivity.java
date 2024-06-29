package com.hacktivators.mentalhealth.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hacktivators.mentalhealth.Model.User;
import com.hacktivators.mentalhealth.OnBoarding.Questtionaire.SetupQuestionActivity;
import com.hacktivators.mentalhealth.R;

import java.util.Objects;

public class LonelyActivity extends AppCompatActivity {

    AppCompatButton start;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lonely);


        start = findViewById(R.id.start_btn);

//        user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");
//        Log.d("data",user.getUsername());
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LonelyActivity.this,SetupQuestionActivity.class);
//                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}