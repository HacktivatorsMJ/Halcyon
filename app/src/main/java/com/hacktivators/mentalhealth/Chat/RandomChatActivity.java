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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hacktivators.mentalhealth.Adapter.ChatAdapter;
import com.hacktivators.mentalhealth.BackEnd.Service;
import com.hacktivators.mentalhealth.MainActivity;
import com.hacktivators.mentalhealth.Model.Chat;
import com.hacktivators.mentalhealth.Model.ChatItem;
import com.hacktivators.mentalhealth.Model.Data;
import com.hacktivators.mentalhealth.Model.Sender;
import com.hacktivators.mentalhealth.Model.Token;
import com.hacktivators.mentalhealth.Notification.APIService;
import com.hacktivators.mentalhealth.Notification.Client;
import com.hacktivators.mentalhealth.Notification.MyResponse;
import com.hacktivators.mentalhealth.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;

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

    Dialog dialog,nsfw_dialog;

    Service service;

    String userid;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");



    String statement;

    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_chat);

        intent = getIntent();

        mChatArrayList = new ArrayList<>();

        chatID = intent.getStringExtra("id");

        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
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
        nsfw_dialog = new Dialog(RandomChatActivity.this);

        readChat();


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageBox.getText().toString();
                callAI(message);

            }
        });

        endChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteSelect();

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


        getId();

    }

    private void getId() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatID);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ChatItem chatItem = snapshot.getValue(ChatItem.class);

                if(chatItem.getAcceptedby().equals(firebaseUser.getUid())){

                    userid = chatItem.getCreatedby();
                }else {
                    userid = chatItem.getAcceptedby();
                }


                statement = chatItem.getStatement();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void callAI(String question) {



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        service = new Retrofit.Builder().baseUrl("http://192.168.68.62:13000/nsfw/").client(client).build().create(Service.class);

        //RequestBody message = RequestBody.create(MediaType.parse("text/plain"), question);

        retrofit2.Call<okhttp3.ResponseBody> responseBodyCall = service.checkNSFW(question);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String responseBody = null;
                JSONObject jsonObject = null;
                try {
                    responseBody = response.body().string();
                    jsonObject = new JSONObject(responseBody);
                    String result = jsonObject.getString("NSFW");

                    sendMessage(result);



                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }




        });




    }


    private void sendMessage(String result) {






        if(result.equals("NSFW")){

            Toast.makeText(getApplicationContext(),"NSFW",Toast.LENGTH_SHORT).show();
            onNSFW();

        }else {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chatlist").child(chatID).child("chats");
            long currentTimeMillis = System.currentTimeMillis();
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("message",messageBox.getText().toString());
            hashMap.put("sentby",firebaseUser.getUid());
            hashMap.put("timestamp",currentTimeMillis);

            databaseReference.push().updateChildren(hashMap);

            SendNoty(statement,chatID);

            messageBox.setText("");
        }





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

    private void SendNoty(String statement,String chatID){


        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = tokens.orderByKey().equalTo(userid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Token token = snapshot1.getValue(Token.class);
                    Data data = new Data("Message in Halcyon", "New message in " + statement,chatID);
                    assert token != null;
                    Sender sender = new Sender(data, token.getToken());
                    apiService.sendNotification(sender)
                            .enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(retrofit2.Call<MyResponse> call, retrofit2.Response<MyResponse> response) {
                                    if (response.code() == 200) {
                                        assert response.body() != null;
                                        if (response.body().success != 1) {
                                            Toast.makeText(RandomChatActivity.this, "Failed! Error code 0x08060101", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                };

                                @Override
                                public void onFailure(retrofit2.Call<MyResponse> call, Throwable t) {

                                }



                            });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }
    private void onDeleteSelect(){


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

    private void onNSFW(){


        AppCompatButton delete;



        nsfw_dialog.setContentView(R.layout.nsfw_dialog);

        delete = nsfw_dialog.findViewById(R.id.delete);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = nsfw_dialog.getWindow();
        nsfw_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


        nsfw_dialog.setCancelable(true);



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               nsfw_dialog.dismiss();
               messageBox.setText("");
            }
        });






        nsfw_dialog.getWindow().setGravity(Gravity.CENTER);
        nsfw_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        nsfw_dialog.show();
    }

}