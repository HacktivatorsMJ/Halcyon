package com.hacktivators.mentalhealth.Wellness.Yoga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.R;

public class YogaReadActivity extends AppCompatActivity {

    TextView title_,plan_;

    Intent intent;

    String YogaID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_read);


        title_ = findViewById(R.id.title);

        plan_ = findViewById(R.id.output);

        intent = getIntent();


        YogaID = intent.getStringExtra("yogaID");




        loadData();
    }

    private void loadData() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        rootRef.collection("users").document(firebaseUser.getUid()).collection("yoga").document(YogaID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String title = document.getString("title");
                        String plan = document.getString("plan");


                        title_.setText(title);
                        plan_.setText(plan);



                    }
                }
            }
        });

    }
}