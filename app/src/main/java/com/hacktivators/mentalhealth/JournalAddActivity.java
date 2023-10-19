package com.hacktivators.mentalhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Random;

public class JournalAddActivity extends AppCompatActivity {


    AppCompatButton refreshQuestion,close,save,newQuestion;

    TextView generatedQuestion;

    EditText journal_box;

    boolean QuestionBol = false;

    FirebaseUser firebaseUser;

    String JournalID;

    String Question;

    LinearLayout colors;

    CardView red,blue,green,purple,yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_add);


        refreshQuestion = findViewById(R.id.refresh);

        close = findViewById(R.id.close);

        save = findViewById(R.id.save);

        newQuestion = findViewById(R.id.newQuestion);

        generatedQuestion = findViewById(R.id.generatedQuestion);

        journal_box = findViewById(R.id.journal_box);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        colors = findViewById(R.id.color);




        newQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionBol = true;
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String JournalTXT = journal_box.getText().toString();

                saveJournal(JournalTXT);
            }
        });






    }

    private void saveJournal(String Journal) {



        JournalID = GenJournalID();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        HashMap hashMap = new HashMap();
        if(QuestionBol){

            hashMap.put("ID",JournalID);
            hashMap.put("journal",Journal);
            hashMap.put("question",true);
            hashMap.put("questionTxt",Question);
        }else {

            hashMap.put("ID",JournalID);
            hashMap.put("journal",Journal);
            hashMap.put("question",false);
        }


        firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("journals").document(JournalID).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Journal saved!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private String GenJournalID(){
        Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz1234567890";
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += letters.charAt(random.nextInt(letters.length()));
        }

        return result;

    }
}