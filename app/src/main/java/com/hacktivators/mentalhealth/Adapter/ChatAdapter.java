package com.hacktivators.mentalhealth.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.hacktivators.mentalhealth.Model.Chat;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<Chat> mChatArrayList;

    FirebaseUser firebaseUser;

    public ChatAdapter(Context mContext, ArrayList<Chat> mChatArrayList,FirebaseUser firebaseUser) {
        this.mContext = mContext;
        this.mChatArrayList = mChatArrayList;
        this.firebaseUser = firebaseUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.chat_item, parent, false);
        return new ChatAdapter.ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = mChatArrayList.get(position);
        if(chat.getSentby().equals(firebaseUser.getUid())){
            holder.leftChatView.setVisibility(View.GONE);
            holder.rightChatView.setVisibility(View.VISIBLE);
            holder.rightTextView.setText(chat.getMessage());
        }else{
            holder.rightChatView.setVisibility(View.GONE);
            holder.leftChatView.setVisibility(View.VISIBLE);
            holder.leftTextView.setText(chat.getMessage());
        }


    }

    @Override
    public int getItemCount() {
        return mChatArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftChatView,rightChatView;
        TextView leftTextView,rightTextView;
        public ViewHolder(View view) {
            super(view);

            leftChatView  = itemView.findViewById(R.id.left_chat_view);
            rightChatView = itemView.findViewById(R.id.right_chat_view);
            leftTextView = itemView.findViewById(R.id.left_chat_text_view);
            rightTextView = itemView.findViewById(R.id.right_chat_text_view);



        }
    }
}
