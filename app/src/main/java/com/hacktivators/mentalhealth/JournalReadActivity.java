package com.hacktivators.mentalhealth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JournalReadActivity extends AppCompatActivity {

    TextView dateView,journalView;

    AppCompatButton back;

    Intent intent;
    String JournalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_read);

        dateView = findViewById(R.id.date);
        journalView = findViewById(R.id.journal);

        back = findViewById(R.id.back);

        intent = getIntent();

        JournalID = intent.getStringExtra("JournalID");



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        loadData();
    }

    private void loadData() {


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        rootRef.collection("users").document(firebaseUser.getUid()).collection("journals").document(JournalID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String journal = document.getString("journal");
                        long date = document.getLong("date");

                        journalView.setText(journal);

                        SimpleDateFormat df = new SimpleDateFormat("hh:mm", Locale.US);
                        String Time = df.format(date);


                        String date_ = DateFormat.getDateInstance(DateFormat.FULL).format(date);


                        dateView.setText(Time + " " + date_);



                    }
                }
            }
        });



    }
}