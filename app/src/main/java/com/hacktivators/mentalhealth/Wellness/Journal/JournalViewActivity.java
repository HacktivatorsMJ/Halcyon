package com.hacktivators.mentalhealth.Wellness.Journal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktivators.mentalhealth.Adapter.JournalViewAdapter;
import com.hacktivators.mentalhealth.MainActivity;
import com.hacktivators.mentalhealth.Model.Journal;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class JournalViewActivity extends AppCompatActivity {


    FloatingActionButton journalAdd;
    RecyclerView recyclerView;

    JournalViewAdapter journalViewAdapter;

    private ArrayList<Journal> journalArrayList;

    FirebaseFirestore firebaseFirestore;

    FirebaseUser firebaseUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_view);


        journalAdd = findViewById(R.id.journalAdd);
        firebaseFirestore = FirebaseFirestore.getInstance();
        journalArrayList = new ArrayList<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        journalViewAdapter = new JournalViewAdapter(journalArrayList,JournalViewActivity.this);
        recyclerView.setAdapter(journalViewAdapter);


        journalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JournalViewActivity.this, JournalAddActivity.class));
            }
        });



        loadData();
    }

    private void loadData() {


            firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("journals").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    journalArrayList.clear();
                    assert value != null;
                    for(DocumentChange documentChange : value.getDocumentChanges()){
                        if(documentChange.getType() == DocumentChange.Type.ADDED){
                            journalArrayList.add(documentChange.getDocument().toObject(Journal.class));
                        }

                        journalViewAdapter.notifyDataSetChanged();



                    }
                }
            });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(JournalViewActivity.this, MainActivity.class));
    }
}