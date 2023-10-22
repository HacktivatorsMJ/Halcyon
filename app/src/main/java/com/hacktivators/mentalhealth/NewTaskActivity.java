package com.hacktivators.mentalhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;


    AppCompatEditText taskBox,taskDescription;

    MaterialTimePicker materialTimePicker;


    AppCompatButton date_btn,time_btn,add_btn,cancel_btn;

    TextView timeTxt,dateTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


        taskBox = findViewById(R.id.taskBox);
        taskDescription = findViewById(R.id.descBox);

        date_btn = findViewById(R.id.selectDate);
        time_btn = findViewById(R.id.selectTime);
        add_btn  = findViewById(R.id.add);
        cancel_btn = findViewById(R.id.cancel);

        timeTxt = findViewById(R.id.time);
        dateTxt = findViewById(R.id.date);

        materialTimePicker  = new MaterialTimePicker.Builder().build();


        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialTimePicker.show(getSupportFragmentManager(),"Time picker");
            }
        });

        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        materialDateBuilder.setTitleText("Select the date");
        materialDateBuilder.setPositiveButtonText("Ok");
        materialDateBuilder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        materialDateBuilder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR);

        CalendarConstraints.Builder calendarConstraintsBuilder = new CalendarConstraints.Builder();
        calendarConstraintsBuilder.setValidator(DateValidatorPointForward.now());

        materialDateBuilder.setCalendarConstraints(calendarConstraintsBuilder.build());
        final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();


        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"Date of reminders");
            }
        });

        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hour = String.valueOf(materialTimePicker.getHour());
                String minute = String.valueOf(materialTimePicker.getMinute());
                String time = hour + " : " + minute;
                timeTxt.setText(time);
            }
        });


        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {

                long date_ = materialDatePicker.getSelection();
                String date = DateFormat.getDateInstance(DateFormat.FULL).format(date_);
                dateTxt.setText(date);

            }
        });


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void addTask() {

        String Task_title = taskBox.getText().toString();
        String Task_description = taskDescription.getText().toString();
        String Task_id = GenTaskID();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id",Task_id);
        hashMap.put("title",Task_title);
        hashMap.put("time",timeTxt.getText().toString());
        hashMap.put("description",Task_description);



        firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("tasks").document(Task_id).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Task saved!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewTaskActivity.this, TaskActivity.class));

                }else {
                    Toast.makeText(getApplicationContext(),"Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }

    private String GenTaskID(){
        Random random = new Random();
        String letters = "abcdefghijklmnopqrstuvwxyz1234567890";
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += letters.charAt(random.nextInt(letters.length()));
        }

        return result;

    }
}