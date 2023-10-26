package com.hacktivators.mentalhealth.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktivators.mentalhealth.Adapter.ChatAdapter;
import com.hacktivators.mentalhealth.MainActivity;
import com.hacktivators.mentalhealth.Model.Chat;
import com.hacktivators.mentalhealth.Model.ChatItem;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomChatActivity extends AppCompatActivity {

    Intent intent;

    String chatID;

    AppCompatButton delete;

    ArrayList<Chat> mChatArrayList;
    ChatAdapter chatAdapter;

    RecyclerView recyclerView;
    ImageButton send_btn;

    EditText messageBox;

    FirebaseUser firebaseUser;

    RelativeLayout end_layout,editor_layout;

    ImageView endChat;

    Dialog dialog;

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

        endChat = findViewById(R.id.delete);

        delete = findViewById(R.id.delete_btn);

        editor_layout = findViewById(R.id.editor_layout);
        end_layout = findViewById(R.id.end_layout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        send_btn = findViewById(R.id.send_btn);

        Log.d("id",chatID);

        dialog = new Dialog(RandomChatActivity.this);

        readChat();


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        endChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMediaSelect();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RandomChatActivity.this, MainActivity.class));
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatID);
                databaseReference.removeValue();

            }
        });




        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ChatItem chatItem = snapshot.getValue(ChatItem.class);

                if(chatItem.isDeleted()){
                    end_layout.setVisibility(View.VISIBLE);
                    editor_layout.setVisibility(View.GONE);

                }else {
                    end_layout.setVisibility(View.GONE);
                    editor_layout.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    private void onMediaSelect(){


        AppCompatButton delete;

        TextView title,desc;

        dialog.setContentView(R.layout.warning_dialog);

        delete = dialog.findViewById(R.id.delete);
        title = dialog.findViewById(R.id.title);
        desc = dialog.findViewById(R.id.desc);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


        dialog.setCancelable(true);


        title.setText("Warning!");
        desc.setText("This action is permanent and you cant recover again!");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatID);
                databaseReference.child("deleted").setValue(true);
                startActivity(new Intent(RandomChatActivity.this, MainActivity.class));
            }
        });






        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }

}