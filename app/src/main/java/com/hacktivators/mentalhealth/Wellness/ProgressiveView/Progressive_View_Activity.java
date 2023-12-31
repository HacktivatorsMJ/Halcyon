package com.hacktivators.mentalhealth.Wellness.ProgressiveView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.hacktivators.mentalhealth.R;

public class Progressive_View_Activity extends AppCompatActivity {

    RelativeLayout session1;
    RelativeLayout session2;

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



        session2 = findViewById(R.id.progressive3);

        session2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Progressive_View_Activity.this, Session2Activity.class));
            }
        });

    }
}