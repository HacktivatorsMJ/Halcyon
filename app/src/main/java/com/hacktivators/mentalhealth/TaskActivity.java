package com.hacktivators.mentalhealth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktivators.mentalhealth.Adapter.JournalViewAdapter;
import com.hacktivators.mentalhealth.Adapter.TaskViewAdapter;
import com.hacktivators.mentalhealth.Model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TaskActivity extends AppCompatActivity implements TaskViewAdapter.EventListener{

    AppCompatEditText taskBox;

    AppCompatButton add;

    RecyclerView recyclerView;

    private ArrayList<Task> taskArrayList;

    private TaskViewAdapter taskViewAdapter;

    FloatingActionButton add_task;

    FirebaseUser firebaseUser;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskpage);

        taskArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);


        add_task = findViewById(R.id.add_task);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();

        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        taskViewAdapter = new TaskViewAdapter(taskArrayList,TaskActivity.this,firebaseUser);
        recyclerView.setAdapter(taskViewAdapter);
        taskViewAdapter.addEventListener(TaskActivity.this);


        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaskActivity.this,NewTaskActivity.class));
            }
        });

        loadData();


    }



    private void loadData() {


        firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("tasks").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                taskArrayList.clear();
                assert value != null;
                for (DocumentChange documentChange : value.getDocumentChanges()) {
                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                        taskArrayList.add(documentChange.getDocument().toObject(Task.class));
                    }

                    taskViewAdapter.notifyDataSetChanged();


                }
            }
        });

    }


    @Override
    public void refresh_fragment() {
        loadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(TaskActivity.this,MainActivity.class));
    }

    public void onDestroy() {
        super.onDestroy();
        taskViewAdapter.removeEventListener();
    }
}