package com.hacktivators.mentalhealth.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktivators.mentalhealth.Adapter.ChatItemAdapter;
import com.hacktivators.mentalhealth.Model.ChatItem;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class ChatConnectFragment extends Fragment {

    FirebaseUser firebaseUser;
    ArrayList<ChatItem> chatItemArrayList,chatItemArrayList2;

    ChatItemAdapter chatItemAdapter;

    RecyclerView recyclerView1,recyclerView2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_connect, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView1 = view.findViewById(R.id.recycler_view1);
        recyclerView2 = view.findViewById(R.id.recycler_view2);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireActivity());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireActivity());

        chatItemArrayList = new ArrayList<>();
        chatItemArrayList2 = new ArrayList<>();

        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView1.setLayoutManager(layoutManager1);

        loadData();

        return view;
    }

    private void loadData() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ChatItem chatItem = dataSnapshot.getValue(ChatItem.class);


                    assert chatItem != null;
                    if(chatItem.getCreatedby().equals(firebaseUser.getUid())){
                        chatItemArrayList.add(chatItem);
                    }



                    chatItemAdapter = new ChatItemAdapter(getContext(),chatItemArrayList,firebaseUser);
                    recyclerView1.setAdapter(chatItemAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("chatlist");

        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ChatItem chatItem = dataSnapshot.getValue(ChatItem.class);


                    assert chatItem != null;
                    if(chatItem.getAcceptedby().equals(firebaseUser.getUid())){
                        chatItemArrayList2.add(chatItem);
                    }



                    chatItemAdapter = new ChatItemAdapter(getContext(),chatItemArrayList2,firebaseUser);
                    recyclerView2.setAdapter(chatItemAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}