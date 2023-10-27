package com.hacktivators.mentalhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Progressive_View_Activity extends AppCompatActivity {

    RelativeLayout session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressive_view);

        session1 = findViewById(R.id.progressive2);

        session1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Progressive_View_Activity.this, Session1Activity.class));
            }
        });

        RelativeLayout session2;

        session2 = findViewById(R.id.progressive3);

        session2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Progressive_View_Activity.this, Session2Activity.class));
            }
        });

    }
}