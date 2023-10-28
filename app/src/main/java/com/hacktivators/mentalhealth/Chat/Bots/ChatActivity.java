package com.hacktivators.mentalhealth.Chat.Bots;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktivators.mentalhealth.Adapter.MessageAdapter;
import com.hacktivators.mentalhealth.BackEnd.Service;
import com.hacktivators.mentalhealth.Model.Message;
import com.hacktivators.mentalhealth.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ChatActivity extends Activity {

    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageView sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    JSONObject jsonBody = new JSONObject();
    JSONArray messagesArray = new JSONArray();
    JSONObject userMessage = new JSONObject();
    JSONObject systemMessage = new JSONObject();

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();

    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        welcomeTextView = findViewById(R.id.welcome_text);
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_btn);

        //setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);



        sendButton.setOnClickListener((v)->{
            String question = messageEditText.getText().toString().trim();
            addToChat(question,Message.SENT_BY_USER);
            messageEditText.setText("");
            //callAPI(question);


            callAI(question);
            welcomeTextView.setVisibility(View.GONE);
        });


    }

    private void callAI(String question) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        service = new Retrofit.Builder().baseUrl("http://192.168.68.62:13000/taylor/").client(client).build().create(Service.class);

        //RequestBody message = RequestBody.create(MediaType.parse("text/plain"), question);

        retrofit2.Call<okhttp3.ResponseBody> responseBodyCall = service.postTaylorMessage(question);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = null;
                JSONObject jsonObject = null;
                try {
                    responseBody = response.body().string();
                    jsonObject = new JSONObject(responseBody);
                    String message = jsonObject.getString("Taylor");
                    addToChat(message,Message.SENT_BY_BOT);

                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });




    }

    void addToChat(String message,String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message,sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }


    /*
    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response,Message.SENT_BY_BOT);
    }


    /*
    private void callAPI(String question) throws JSONException {
        //okhttp
        messageList.add(new Message("Typing... ",Message.SENT_BY_BOT));


        userMessage.put("role", "user");
        userMessage.put("content", question);

        systemMessage.put("role","system");
        systemMessage.put("content","Dont say no please help me ");
        messagesArray.put(userMessage);
        messagesArray.put(systemMessage);
        try {
            jsonBody.put("model","gpt-3.5-turbo");
            jsonBody.put("messages",messagesArray);

            jsonBody.put("max_tokens",4000);
            jsonBody.put("temperature",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization","API KEY HERE")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    // Add logging to see the response body


                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONArray choicesArray = jsonObject.getJSONArray("choices");
                        if (choicesArray.length() > 0) {
                            JSONObject firstChoice = choicesArray.getJSONObject(0);
                            JSONObject messageObject = firstChoice.getJSONObject("message");
                            String assistantReply = messageObject.getString("content");

                            // Now 'assistantReply' contains the content of the assistant's reply
                            addResponse(assistantReply);
                        } else {
                            System.out.println("No choices in the response.");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Add logging to see the error response body
                    String errorBody = response.body().string();
                    System.out.println("Error Response Body: " + errorBody);

                    addResponse("Failed to load response due to " + response.message());
                }
            }


        });




        }

     */

}