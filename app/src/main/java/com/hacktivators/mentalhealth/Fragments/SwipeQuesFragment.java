package com.hacktivators.mentalhealth.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktivators.mentalhealth.Adapter.StackAdapter;
import com.hacktivators.mentalhealth.Chat.Bots.ChatActivity;
import com.hacktivators.mentalhealth.Chat.RandomChatActivity;
import com.hacktivators.mentalhealth.Model.Stack;
import com.hacktivators.mentalhealth.R;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class SwipeQuesFragment extends Fragment {

    CardStackView stackView;

    StackAdapter stackAdapter;

    ArrayList<Stack> stackArrayList;
    FirebaseUser firebaseUser;

    ImageView addStatement;

    Dialog dialog;
    CardStackLayoutManager cardStackLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swipe_ques,container,false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        stackArrayList = new ArrayList<>();


        stackView = view.findViewById(R.id.stackView);


        CardStackListener cardStackListener = new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }

            @Override
            public void onCardSwiped(Direction direction) {

                Log.d("Swiped",direction.toString());

                if(direction.toString().equals("Right")){
                    int topPosition = cardStackLayoutManager.getTopPosition();
                    if (topPosition < stackAdapter.getItemCount()) {
                        Stack selectedStack = stackAdapter.mStackArrayList.get(topPosition);

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("chatlist").child(selectedStack.getId());

                        HashMap<String,Object> hashMap1 = new HashMap<String,Object>();
                        hashMap1.put("status",true);
                        reference.updateChildren(hashMap1);

                        // Pass the data to the next activity using Intent
                        Intent chatIntent = new Intent(requireActivity(), RandomChatActivity.class);
                        chatIntent.putExtra("id", selectedStack.getId());
                        startActivity(chatIntent);

                    }else {
                        int position = topPosition -1;
                        Stack selectedStack = stackAdapter.mStackArrayList.get(position);

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("chatlist").child(selectedStack.getId());

                        HashMap<String,Object> hashMap1 = new HashMap<String,Object>();
                        hashMap1.put("status",true);
                        reference.updateChildren(hashMap1);

                        // Pass the data to the next activity using Intent
                        Intent chatIntent = new Intent(requireActivity(), RandomChatActivity.class);
                        chatIntent.putExtra("id", selectedStack.getId());
                        startActivity(chatIntent);
                    }
                }





            }

            @Override
            public void onCardRewound() {

            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {

            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        };

        cardStackLayoutManager = new CardStackLayoutManager(requireActivity(),cardStackListener);
        cardStackLayoutManager.setStackFrom(StackFrom.Top);
        cardStackLayoutManager.setTranslationInterval(8.0f);
        cardStackLayoutManager.setCanScrollHorizontal(true);
        cardStackLayoutManager.setCanScrollVertical(false);
        cardStackLayoutManager.setVisibleCount(3);



        stackView.setLayoutManager(cardStackLayoutManager);





        addStatement = view.findViewById(R.id.addStatement);

        dialog = new Dialog(requireActivity());

        addStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClick();
            }
        });

        loadData();


        return view;
    }

    private void onAddClick() {

        dialog.setContentView(R.layout.add_statement_dialog);

        AppCompatButton send;
        AppCompatEditText editText;

        editText = dialog.findViewById(R.id.inputStatement);
        send = dialog.findViewById(R.id.send_btn);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String statement = editText.getText().toString();
                String stackID = GenStackID();
                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("chatlist");
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id",stackID);
                hashMap.put("statement", statement);
                hashMap.put("status",false);
                hashMap.put("createdby",firebaseUser.getUid());

                databaseReference1.child(stackID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                    }
                });
            }
        });


        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();

    }


    private void loadData() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Stack stack = dataSnapshot.getValue(Stack.class);

                    assert stack != null;
                    if(!stack.getCreatedby().equals(firebaseUser.getUid())){
                        stackArrayList.add(stack);

                    }

                    stackAdapter = new StackAdapter(getContext(),stackArrayList);
                    stackView.setAdapter(stackAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }


    private String GenStackID(){
        Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz1234567890";
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += letters.charAt(random.nextInt(letters.length()));
        }

        return result;

    }
}