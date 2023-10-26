package com.hacktivators.mentalhealth.Tasks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktivators.mentalhealth.Adapter.TaskViewAdapter;
import com.hacktivators.mentalhealth.MainActivity;
import com.hacktivators.mentalhealth.Model.Task;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity implements TaskViewAdapter.EventListener{



    TextView tasks;

    RecyclerView recyclerView;

    private ArrayList<Task> taskArrayList;

    private TaskViewAdapter taskViewAdapter;

    FloatingActionButton add_task;

    FirebaseUser firebaseUser;

    FirebaseFirestore firebaseFirestore;

    int no_of_tasks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskpage);

        taskArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);


        add_task = findViewById(R.id.add_task);
        tasks = findViewById(R.id.tasks);

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
                startActivity(new Intent(TaskActivity.this, NewTaskActivity.class));
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
                        no_of_tasks += 1;
                    }

                    taskViewAdapter.notifyDataSetChanged();


                }

                tasks.setText("You have " + no_of_tasks + " tasks left to do!");
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

        startActivity(new Intent(TaskActivity.this, MainActivity.class));
    }

    public void onDestroy() {
        super.onDestroy();
        taskViewAdapter.removeEventListener();
    }
}