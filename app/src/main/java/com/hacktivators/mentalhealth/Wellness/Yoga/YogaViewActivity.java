package com.hacktivators.mentalhealth.Wellness.Yoga;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktivators.mentalhealth.Adapter.YogaViewAdapter;
import com.hacktivators.mentalhealth.Model.Yoga;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class YogaViewActivity extends AppCompatActivity {

    Intent intent;

    private ArrayList<Yoga> mYogaArrayList;

    private YogaViewAdapter yogaViewAdapter;



    String yogaID;

    RecyclerView recyclerView;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_view);

        intent = getIntent();

        mYogaArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        floatingActionButton = findViewById(R.id.add_yoga);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        yogaViewAdapter = new YogaViewAdapter(YogaViewActivity.this,mYogaArrayList);
        recyclerView.setAdapter(yogaViewAdapter);



        yogaID = intent.getStringExtra("yogaID");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YogaViewActivity.this, YogaPageActivity.class));
            }
        });


        loadData();
    }

    private void loadData() {

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("yoga").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                mYogaArrayList.clear();
                for (DocumentChange documentChange : value.getDocumentChanges()) {
                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                        mYogaArrayList.add(documentChange.getDocument().toObject(Yoga.class));
                    }

                    yogaViewAdapter.notifyDataSetChanged();


                }
            }
        });

    }
}