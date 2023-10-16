package com.hacktivators.mentalhealth.OnBoarding.Questtionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.hacktivators.mentalhealth.R;

public class SetupQuestion4Activity extends AppCompatActivity {

    AppCompatButton yes,no;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupq4);

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupQuestion4Activity.this, SetupQuestion5Activity.class));
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupQuestion4Activity.this, SetupQuestion5Activity.class));
            }
        });


    }
}
