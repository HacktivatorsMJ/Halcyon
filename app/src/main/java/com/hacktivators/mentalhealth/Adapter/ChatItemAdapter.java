package com.hacktivators.mentalhealth.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktivators.mentalhealth.Chat.RandomChatActivity;
import com.hacktivators.mentalhealth.Model.Chat;
import com.hacktivators.mentalhealth.Model.ChatItem;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class ChatItemAdapter extends RecyclerView.Adapter<ChatItemAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<ChatItem> mChatItemArrayList;

    FirebaseUser firebaseUser;

    public ChatItemAdapter(Context mContext, ArrayList<ChatItem> mChatItemArrayList,FirebaseUser firebaseUser) {
        this.mContext = mContext;
        this.mChatItemArrayList = mChatItemArrayList;
        this.firebaseUser = firebaseUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.random_chat_item, parent, false);
        return new ChatItemAdapter.ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatItem chatItem = mChatItemArrayList.get(position);

        holder.statement.setText(chatItem.getStatement());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(mContext, RandomChatActivity.class);
                chatIntent.putExtra("id", chatItem.getId());
                mContext.startActivity(chatIntent);
            }
        });



        latestMessage(chatItem.getId());


    }

    private void latestMessage(String chatId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatId).child("chats");

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);

                    if(snapshot.exists()){

                        latestMessage(chat.getMessage());

                    }else {
                        latestMessage("Waiting for response");
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mChatItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView statement,latestmessage;
        public ViewHolder(View view) {
            super(view);

           statement = view.findViewById(R.id.statement);
            latestmessage = view.findViewById(R.id.message);



        }
    }
}
