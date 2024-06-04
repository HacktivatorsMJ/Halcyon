package com.hacktivators.mentalhealth.Chat.Bots;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.ChatFutures;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Gemini extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void runGemini(){
        GenerativeModel gm = new GenerativeModel("gemini-1.5-flash","AIzaSyDFJKIK3lLXQkX5KVSrkYu-vS3_ejHpYv8" );

        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        // The Gemini 1.5 models are versatile and work with multi-turn conversations (like chat)


// (optional) Create previous chat history for context
        Content.Builder userContentBuilder = new Content.Builder();
        userContentBuilder.setRole("user");
        userContentBuilder.addText("Hello, I have 2 dogs in my house.");
        Content userContent = userContentBuilder.build();

        Content.Builder modelContentBuilder = new Content.Builder();
        modelContentBuilder.setRole("model");
        modelContentBuilder.addText("Great to meet you. What would you like to know?");
        Content modelContent = userContentBuilder.build();

        List<Content> history = Arrays.asList(userContent, modelContent);

// Initialize the chat
        ChatFutures chat = model.startChat(history);

        Content.Builder messageBuilder = new Content.Builder();
        messageBuilder.setRole("user");
        messageBuilder.addText("How many paws are in my house?");

        Content message = messageBuilder.build();


        ListenableFuture<GenerateContentResponse> response = chat.sendMessage(message);


// Send the message

        Executor executor = Executors.newSingleThreadExecutor();

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                System.out.println(resultText);
                Log.d("gemini_msg",resultText);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, executor);
    }
}

