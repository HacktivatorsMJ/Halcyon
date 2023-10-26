package com.hacktivators.mentalhealth.Hamburger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktivators.mentalhealth.Adapter.ProfessionalHelpAdapter;
import com.hacktivators.mentalhealth.Model.Doc;
import com.hacktivators.mentalhealth.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ProfessionalHelpActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Doc> docArrayList;
    ProfessionalHelpAdapter professionalHelpAdapter;

    FirebaseFirestore firebaseFirestore;

    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_help);



        recyclerView = findViewById(R.id.recycler_view);

        docArrayList = new ArrayList<>();

        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        professionalHelpAdapter = new ProfessionalHelpAdapter(ProfessionalHelpActivity.this,docArrayList);
        recyclerView.setAdapter(professionalHelpAdapter);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();



        loadData();

    }

    private void loadData() {
        firebaseFirestore.collection("doctors").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                docArrayList.clear();
                for(DocumentChange documentChange : value.getDocumentChanges()){
                    if(documentChange.getType() == DocumentChange.Type.ADDED){
                        docArrayList.add(documentChange.getDocument().toObject(Doc.class));
                    }

                    professionalHelpAdapter.notifyDataSetChanged();



                }

            }
        });
    }
}