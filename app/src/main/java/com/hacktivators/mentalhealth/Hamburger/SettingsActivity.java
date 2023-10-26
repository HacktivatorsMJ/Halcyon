package com.hacktivators.mentalhealth.Hamburger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.hacktivators.mentalhealth.OnBoarding.StartActivity;
import com.hacktivators.mentalhealth.R;

public class SettingsActivity extends AppCompatActivity {


    CardView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logout = findViewById(R.id.logout_card);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, StartActivity.class));
                FirebaseAuth.getInstance().signOut();
            }
        });

    }
}