package com.hacktivators.mentalhealth.Journal;

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

    CardView red,blue,green,purple,yellow;
    ImageView redCheck,blueCheck,greenCheck,purpleCheck,yellowCheck;

    String ColorSelected = "null";

    boolean color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_view);


        journalAdd = findViewById(R.id.journalAdd);
        firebaseFirestore = FirebaseFirestore.getInstance();
        journalArrayList = new ArrayList<Journal>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        journalViewAdapter = new JournalViewAdapter(journalArrayList,JournalViewActivity.this);
        recyclerView.setAdapter(journalViewAdapter);

        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        purple = findViewById(R.id.purple);
        yellow = findViewById(R.id.yellow);

        redCheck = findViewById(R.id.red_check);
        blueCheck = findViewById(R.id.blue_check);
        greenCheck = findViewById(R.id.green_check);
        purpleCheck = findViewById(R.id.purple_check);
        yellowCheck = findViewById(R.id.yellow_check);

        journalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JournalViewActivity.this, JournalAddActivity.class));
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ColorSelected.equals("red")) {
                    redCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                    loadData(false);
                }else {
                    redCheck.setVisibility(View.VISIBLE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "red";
                    loadData(true);
                }






            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ColorSelected.equals("blue")) {
                    blueCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                    loadData(false);

                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.VISIBLE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "blue";
                    loadData(true);

                }






            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ColorSelected.equals("green")) {
                    greenCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                    loadData(false);
                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.VISIBLE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "green";
                    loadData(true);
                }





            }
        });

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ColorSelected.equals("purple")) {
                    purpleCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                    loadData(false);
                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.VISIBLE);
                    yellowCheck.setVisibility(View.GONE);



                    color = true;
                    ColorSelected = "purple";
                    loadData(true);
                }






            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ColorSelected.equals("yellow")) {
                    yellowCheck.setVisibility(View.GONE);
                    color = false;
                    ColorSelected = "null";
                    loadData(false);
                }else {
                    redCheck.setVisibility(View.GONE);
                    blueCheck.setVisibility(View.GONE);
                    greenCheck.setVisibility(View.GONE);
                    purpleCheck.setVisibility(View.GONE);
                    yellowCheck.setVisibility(View.VISIBLE);

                    color = true;
                    ColorSelected = "yellow";
                    loadData(true);

                }

            }
        });


        loadData(color);
    }

    private void loadData(boolean color) {

        if(color){
            firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("journals").whereEqualTo("colorTxt",ColorSelected).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    journalArrayList.clear();
                    for(DocumentChange documentChange : value.getDocumentChanges()){
                        if(documentChange.getType() == DocumentChange.Type.ADDED){
                            journalArrayList.add(documentChange.getDocument().toObject(Journal.class));
                        }

                        journalViewAdapter.notifyDataSetChanged();



                    }
                }
            });
        }else {
            firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("journals").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    journalArrayList.clear();
                    for(DocumentChange documentChange : value.getDocumentChanges()){
                        if(documentChange.getType() == DocumentChange.Type.ADDED){
                            journalArrayList.add(documentChange.getDocument().toObject(Journal.class));
                        }

                        journalViewAdapter.notifyDataSetChanged();



                    }
                }
            });
        }


    }
}