package com.hacktivators.mentalhealth.OnBoarding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.hacktivators.mentalhealth.BackEnd.Service;
import com.hacktivators.mentalhealth.MainActivity;
import com.hacktivators.mentalhealth.Model.User;
import com.hacktivators.mentalhealth.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateUserLoaderActivity extends AppCompatActivity {


    User user;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user_loader);

        user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");

        Log.d("data", String.valueOf(user));


        uploadData();




    }

    private void uploadData() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new Gson();
        String json = gson.toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);

        service = new Retrofit.Builder()
                .baseUrl("http://192.168.1.27:1212/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service.class);

        retrofit2.Call<okhttp3.ResponseBody> responseBodyCall = service.postNewUser(requestBody);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(CreateUserLoaderActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                // Handle failure
            }
        });



    }
}