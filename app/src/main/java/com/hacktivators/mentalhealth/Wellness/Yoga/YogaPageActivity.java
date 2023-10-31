package com.hacktivators.mentalhealth.Wellness.Yoga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.Constants;
import com.hacktivators.mentalhealth.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class YogaPageActivity extends AppCompatActivity {

    AppCompatEditText inputTime,inputInjury;

    TextView output;

    RadioGroup breathing_group,fitness_group,goal_group;

    int age;

    String time,injury,breathing,fitness,goal,DOB;

    RadioButton yes, no, beginner, intermediate, advanced, mentalClarity, improveFlexibility, strengthBuilding, stressReduction, balanceStability, enhancedSleep, enhancedMood;
    RadioButton mindfulness, cardiovascular, betterPosture, digestiveHealth, spiritualGrowth, improvedBreathing, weightManagement, increasedEnergy;

    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    AppCompatButton start_btn,save;

    RelativeLayout questionsLayout,loadingLayout,resultsLayout;

    Constants constants;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    JSONObject jsonBody = new JSONObject();
    JSONArray messagesArray = new JSONArray();
    JSONObject userMessage = new JSONObject();
    JSONObject systemMessage = new JSONObject();

    AppCompatEditText yogaTitle;

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_page);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        output = findViewById(R.id.output);

        constants = new Constants();
        getFromBirthday();

        // Define the date format

        start_btn = findViewById(R.id.start_btn);

        questionsLayout = findViewById(R.id.questionsLayout);
        loadingLayout = findViewById(R.id.loadingLayout);
        resultsLayout = findViewById(R.id.resultLayout);

        inputTime = findViewById(R.id.inputTime);
        inputInjury = findViewById(R.id.inputInjury);

        breathing_group = findViewById(R.id.yesno);
        fitness_group = findViewById(R.id.fitness_group);
        goal_group = findViewById(R.id.goal_group);

        save = findViewById(R.id.save);

        time = inputTime.getText().toString();
        injury = inputInjury.getText().toString();


        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        beginner = findViewById(R.id.beginner);
        intermediate = findViewById(R.id.intermediate);
        advanced = findViewById(R.id.advanced);


        mentalClarity = findViewById(R.id.mental_clarity);
        improveFlexibility = findViewById(R.id.improve_flexibility);
        strengthBuilding = findViewById(R.id.strength_building);
        stressReduction = findViewById(R.id.stress_reduction);
        balanceStability = findViewById(R.id.balance_stability);
        enhancedSleep = findViewById(R.id.enhanced_sleep);
        enhancedMood = findViewById(R.id.enhanced_mood);

        mindfulness = findViewById(R.id.mindfulness);
        cardiovascular = findViewById(R.id.cardiovascular);
        betterPosture = findViewById(R.id.better_posture);
        digestiveHealth = findViewById(R.id.digestive_health);
        spiritualGrowth = findViewById(R.id.spiritual_growth);
        improvedBreathing = findViewById(R.id.improved_breathing);
        weightManagement = findViewById(R.id.weight_management);
        increasedEnergy = findViewById(R.id.increased_energy);

        yogaTitle = findViewById(R.id.yogaTitle);




        start_btn.setOnClickListener(v -> {

            questionsLayout.setVisibility(View.GONE);
            loadingLayout.setVisibility(View.VISIBLE);

            loadData();

            String prompt = "Make a yoga plan for me I am " + age + " years old."+ injury + " injury " + " I am a " + fitness + " in terms of yoga and fitness. I am planning to do yoga " +
                    time + ". " + breathing + ". My main goal to do yoga is " + goal + "please make a perfect plan for me.";

            try {
                callApi(prompt);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveYoga();
            }
        });

    }

    private void saveYoga() {

        String YogaID = GenYogaID();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id",YogaID);
        hashMap.put("title",yogaTitle.getText().toString());
        hashMap.put("plan",output.getText().toString());



        firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("yoga").document(YogaID).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                if(task.isSuccessful()){

                    startActivity(new Intent(YogaPageActivity.this, YogaViewActivity.class));


                }else {
                    Toast.makeText(getApplicationContext(),"Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }



    private void loadData() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        // Parse the date of birth string to LocalDate
        LocalDate dob = LocalDate.parse(DOB, formatter);
        LocalDate currentDate = LocalDate.now();

        // Calculate the period between the date of birth and current date
        Period period = Period.between(dob, currentDate);

        age = period.getYears();

        if(yes.isChecked()){
            breathing += "I want some breathing exercise";
        }
        if(no.isChecked()){
            breathing += "I dont want breathing exercise";
        }

        if(beginner.isChecked()){
            fitness += "am a beginner ";
        }
        if(intermediate.isChecked()){
            fitness += "am a intermediate ";
        }
        if(advanced.isChecked()){
            fitness += "am a professional ";
        }

        if (mentalClarity.isChecked()) {
            goal += "Mental Clarity ";
        }

        if (improveFlexibility.isChecked()) {
            goal += "Improve Flexibility ";
        }

        if (strengthBuilding.isChecked()) {
            goal += "Strength Building ";
        }

        if (stressReduction.isChecked()) {
            goal += "Stress Reduction ";
        }

        if (balanceStability.isChecked()) {
            goal += "Balance and Stability ";
        }

        if (enhancedSleep.isChecked()) {
            goal += "Enhanced Sleep ";
        }

        if (enhancedMood.isChecked()) {
            goal += "Enhanced Mood ";
        }

        if (mindfulness.isChecked()) {
            goal += "Mindfulness ";
        }

        if (cardiovascular.isChecked()) {
            goal += "Cardiovascular Health ";
        }

        if (betterPosture.isChecked()) {
            goal += "Better Posture ";
        }

        if (digestiveHealth.isChecked()) {
            goal += "Digestive Health ";
        }

        if (spiritualGrowth.isChecked()) {
            goal += "Spiritual Growth ";
        }

        if (improvedBreathing.isChecked()) {
            goal += "Improved Breathing ";
        }

        if (weightManagement.isChecked()) {
            goal += "Weight Management ";
        }

        if (increasedEnergy.isChecked()) {
            goal += "Increased Energy ";
        }
    }

    private void callApi(String prompt) throws JSONException {



        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        systemMessage.put("role","system");
        systemMessage.put("content","add \\n whenever creating new line and use other formating commands in strings appropriately");
        messagesArray.put(userMessage);
        messagesArray.put(systemMessage);
        try {
            jsonBody.put("model","gpt-4");
            jsonBody.put("messages",messagesArray);

            jsonBody.put("max_tokens",4000);
            jsonBody.put("temperature",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization","Bearer " + constants.APIKEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
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

                            formatResult(assistantReply);



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

                    //Toast.makeText(getApplicationContext(),errorBody,Toast.LENGTH_SHORT).show();
                }
            }


        });


    }

    private void formatResult(String assistantReply) {



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingLayout.setVisibility(View.GONE);
                resultsLayout.setVisibility(View.VISIBLE);
                output.setText(assistantReply);
            }
        });


    }


    private void getFromBirthday() {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        rootRef.collection("users").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        DOB = document.getString("age");

                    }
                }
            }
        });







    }

    private String GenYogaID(){
        Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz1234567890";
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += letters.charAt(random.nextInt(letters.length()));
        }

        return result;

    }
}