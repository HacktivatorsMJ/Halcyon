package com.hacktivators.mentalhealth.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktivators.mentalhealth.Adapter.ChatAdapter;
import com.hacktivators.mentalhealth.Model.Chat;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomChatActivity extends AppCompatActivity {

    Intent intent;

    String chatID;

    ArrayList<Chat> mChatArrayList;
    ChatAdapter chatAdapter;

    RecyclerView recyclerView;
    ImageButton send_btn;

    EditText messageBox;

    FirebaseUser firebaseUser;

    ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_chat);

        intent = getIntent();

        mChatArrayList = new ArrayList<>();

        chatID = intent.getStringExtra("id");

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        messageBox = findViewById(R.id.message_edit_text);

        delete = findViewById(R.id.delete);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        send_btn = findViewById(R.id.send_btn);

        Log.d("id",chatID);


        readChat();


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void sendMessage() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatID).child("chats");
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message",messageBox.getText().toString());
        hashMap.put("sentby",firebaseUser.getUid());
        hashMap.put("timestamp",currentTimeMillis);

        databaseReference.push().updateChildren(hashMap);

        messageBox.setText("");



    }

    private void readChat() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatID).child("chats");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChatArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    mChatArrayList.add(chat);

                }

                chatAdapter = new ChatAdapter(RandomChatActivity.this,mChatArrayList,firebaseUser);
                recyclerView.setAdapter(chatAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}