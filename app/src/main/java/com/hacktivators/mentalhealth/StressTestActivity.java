package com.hacktivators.mentalhealth;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.R;

public class StressTestActivity extends AppCompatActivity {


    private static final String SSTIME = "SSTIME";
    RelativeLayout st1,st2,st3,st4,st5,st6,st7,st8,st9,st10;

    AppCompatButton nev1,nev2,nev3,nev4,nev5,nev6,nev7,nev8,nev9,nev10;
    AppCompatButton alm1, alm2, alm3, alm4, alm5, alm6, alm7, alm8, alm9, alm10;
    AppCompatButton som1, som2, som3, som4, som5, som6, som7, som8, som9, som10;
    AppCompatButton fair1, fair2, fair3, fair4, fair5, fair6, fair7, fair8, fair9, fair10;
    AppCompatButton oft1, oft2, oft3, oft4, oft5, oft6, oft7, oft8, oft9, oft10;

    AppCompatButton start_btn,done;

    RelativeLayout start_layout,prof_help,result,loading;

    View ds1,ds2,ds3,ds4,ds5;

    TextView scoreTxt,DescTxt;

    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_test);

        ds1 = findViewById(R.id.ds1);
        ds2 = findViewById(R.id.ds2);
        ds3 = findViewById(R.id.ds3);
        ds4 = findViewById(R.id.ds4);
        ds5 = findViewById(R.id.ds5);

        st1 = findViewById(R.id.st1);
        st2 = findViewById(R.id.st2);
        st3 = findViewById(R.id.st3);
        st4 = findViewById(R.id.st4);
        st5 = findViewById(R.id.st5);
        st6 = findViewById(R.id.st6);
        st7 = findViewById(R.id.st7);
        st8 = findViewById(R.id.st8);
        st9 = findViewById(R.id.st9);
        st10 = findViewById(R.id.st10);

        // Initialize AppCompatButtons
        nev1 = findViewById(R.id.nev1);
        nev2 = findViewById(R.id.nev2);
        nev3 = findViewById(R.id.nev3);
        nev4 = findViewById(R.id.nev4);
        nev5 = findViewById(R.id.nev5);
        nev6 = findViewById(R.id.nev6);
        nev7 = findViewById(R.id.nev7);
        nev8 = findViewById(R.id.nev8);
        nev9 = findViewById(R.id.nev9);
        nev10 = findViewById(R.id.nev10);

        alm1 = findViewById(R.id.alm1);
        alm2 = findViewById(R.id.alm2);
        alm3 = findViewById(R.id.alm3);
        alm4 = findViewById(R.id.alm4);
        alm5 = findViewById(R.id.alm5);
        alm6 = findViewById(R.id.alm6);
        alm7 = findViewById(R.id.alm7);
        alm8 = findViewById(R.id.alm8);
        alm9 = findViewById(R.id.alm9);
        alm10 = findViewById(R.id.alm10);

        // Initialize buttons for som1 to som10
        som1 = findViewById(R.id.som1);
        som2 = findViewById(R.id.som2);
        som3 = findViewById(R.id.som3);
        som4 = findViewById(R.id.som4);
        som5 = findViewById(R.id.som5);
        som6 = findViewById(R.id.som6);
        som7 = findViewById(R.id.som7);
        som8 = findViewById(R.id.som8);
        som9 = findViewById(R.id.som9);
        som10 = findViewById(R.id.som10);

        // Initialize buttons for fair1 to fair10
        fair1 = findViewById(R.id.fair1);
        fair2 = findViewById(R.id.fair2);
        fair3 = findViewById(R.id.fair3);
        fair4 = findViewById(R.id.fair4);
        fair5 = findViewById(R.id.fair5);
        fair6 = findViewById(R.id.fair6);
        fair7 = findViewById(R.id.fair7);
        fair8 = findViewById(R.id.fair8);
        fair9 = findViewById(R.id.fair9);
        fair10 = findViewById(R.id.fair10);

        // Initialize buttons for oft1 to oft10
        oft1 = findViewById(R.id.oft1);
        oft2 = findViewById(R.id.oft2);
        oft3 = findViewById(R.id.oft3);
        oft4 = findViewById(R.id.oft4);
        oft5 = findViewById(R.id.oft5);
        oft6 = findViewById(R.id.oft6);
        oft7 = findViewById(R.id.oft7);
        oft8 = findViewById(R.id.oft8);
        oft9 = findViewById(R.id.oft9);
        oft10 = findViewById(R.id.oft10);

        prof_help = findViewById(R.id.help_btn);

        start_btn = findViewById(R.id.start_btn);
        DescTxt = findViewById(R.id.depression_status);

        start_layout = findViewById(R.id.start_layout);


        result = findViewById(R.id.result);

        loading = findViewById(R.id.loading);


        scoreTxt = findViewById(R.id.desc);

        done = findViewById(R.id.done);


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_layout.setVisibility(View.GONE);
                st1.setVisibility(View.VISIBLE);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StressTestActivity.this,MainActivity.class));
            }
        });


        prof_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StressTestActivity.this, ProfessionalHelpActivity.class));
            }
        });



        nev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st1.setVisibility(View.GONE);
                st2.setVisibility(View.VISIBLE);
                // You can use specific actions for nev1 here
            }
        });

        nev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st2.setVisibility(View.GONE);
                st3.setVisibility(View.VISIBLE);
                // You can use specific actions for nev2 here
            }
        });


        nev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st3.setVisibility(View.GONE);
                st4.setVisibility(View.VISIBLE);
                // You can use specific actions for nev3 here
            }
        });

        // For nev4
        nev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st4.setVisibility(View.GONE);
                st5.setVisibility(View.VISIBLE);
                // You can use specific actions for nev4 here
            }
        });

        // For nev5
        nev5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st5.setVisibility(View.GONE);
                st6.setVisibility(View.VISIBLE);
                // You can use specific actions for nev5 here
            }
        });

        // For nev6
        nev6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st6.setVisibility(View.GONE);
                st7.setVisibility(View.VISIBLE);
                // You can use specific actions for nev6 here
            }
        });

        // For nev7
        nev7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st7.setVisibility(View.GONE);
                st8.setVisibility(View.VISIBLE);
                // You can use specific actions for nev7 here
            }
        });

        // For nev8
        nev8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st8.setVisibility(View.GONE);
                st9.setVisibility(View.VISIBLE);
                // You can use specific actions for nev8 here
            }
        });

        // For nev9
        nev9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st9.setVisibility(View.GONE);
                st10.setVisibility(View.VISIBLE);
                // You can use specific actions for nev9 here
            }
        });

        // For nev10
        nev10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle specific actions for nev10 here

                getResult();
                st10.setVisibility(View.GONE);
            }
        });
        alm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st1.setVisibility(View.GONE);
                st2.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        alm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st2.setVisibility(View.GONE);
                st3.setVisibility(View.VISIBLE);
                score += 1;

            }
        });

        alm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st3.setVisibility(View.GONE);
                st4.setVisibility(View.VISIBLE);
                score += 1;
                // You can use specific actions for alm3 here
            }
        });

// For alm4
        alm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st4.setVisibility(View.GONE);
                st5.setVisibility(View.VISIBLE);
                score += 1;
                // You can use specific actions for alm4 here
            }
        });

// For alm5
        alm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st5.setVisibility(View.GONE);
                st6.setVisibility(View.VISIBLE);
                score += 1;
                // You can use specific actions for alm5 here
            }
        });

// For alm6
        alm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st6.setVisibility(View.GONE);
                st7.setVisibility(View.VISIBLE);
                score += 1;
                // You can use specific actions for alm6 here
            }
        });

// For alm7
        alm7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st7.setVisibility(View.GONE);
                st8.setVisibility(View.VISIBLE);
                score += 1;
                // You can use specific actions for alm7 here
            }
        });

// For alm8
        alm8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st8.setVisibility(View.GONE);
                st9.setVisibility(View.VISIBLE);
                score += 1;
                // You can use specific actions for alm8 here
            }
        });

// For alm9
        alm9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st9.setVisibility(View.GONE);
                st10.setVisibility(View.VISIBLE);
                score += 1;
                // You can use specific actions for alm9 here
            }
        });

// For alm10
        alm10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle specific actions for alm10 here
                getResult();
                st10.setVisibility(View.GONE);
            }
        });

        som1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st1.setVisibility(View.GONE);
                st2.setVisibility(View.VISIBLE);
                score += 2;
            }
        });

        som2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st2.setVisibility(View.GONE);
                st3.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som2 here
            }
        });

// For som3
        som3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st3.setVisibility(View.GONE);
                st4.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som3 here
            }
        });

// For som4
        som4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st4.setVisibility(View.GONE);
                st5.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som4 here
            }
        });

// For som5
        som5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st5.setVisibility(View.GONE);
                st6.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som5 here
            }
        });

// For som6
        som6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st6.setVisibility(View.GONE);
                st7.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som6 here
            }
        });

// For som7
        som7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st7.setVisibility(View.GONE);
                st8.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som7 here
            }
        });

// For som8
        som8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st8.setVisibility(View.GONE);
                st9.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som8 here
            }
        });

// For som9
        som9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st9.setVisibility(View.GONE);
                st10.setVisibility(View.VISIBLE);
                score += 2;
                // You can use specific actions for som9 here
            }
        });

// For som10
        som10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle specific actions for som10 here
                getResult();
                st10.setVisibility(View.GONE);
            }
        });

        fair1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st1.setVisibility(View.GONE);
                st2.setVisibility(View.VISIBLE);
                score += 3;
            }
        });

        fair2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st2.setVisibility(View.GONE);
                st3.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair2 here
            }
        });

// For fair3
        fair3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st3.setVisibility(View.GONE);
                st4.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair3 here
            }
        });

// For fair4
        fair4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st4.setVisibility(View.GONE);
                st5.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair4 here
            }
        });

// For fair5
        fair5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st5.setVisibility(View.GONE);
                st6.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair5 here
            }
        });

// For fair6
        fair6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st6.setVisibility(View.GONE);
                st7.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair6 here
            }
        });

// For fair7
        fair7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st7.setVisibility(View.GONE);
                st8.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair7 here
            }
        });

// For fair8
        fair8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st8.setVisibility(View.GONE);
                st9.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair8 here
            }
        });

// For fair9
        fair9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st9.setVisibility(View.GONE);
                st10.setVisibility(View.VISIBLE);
                score += 3;
                // You can use specific actions for fair9 here
            }
        });

// For fair10
        fair10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle specific actions for fair10 here
                getResult();
                st10.setVisibility(View.GONE);
            }
        });

        oft1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st1.setVisibility(View.GONE);
                st2.setVisibility(View.VISIBLE);
                score += 4;
            }
        });

        oft2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st2.setVisibility(View.GONE);
                st3.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft2 here
            }
        });

// For oft3
        oft3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st3.setVisibility(View.GONE);
                st4.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft3 here
            }
        });

// For oft4
        oft4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st4.setVisibility(View.GONE);
                st5.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft4 here
            }
        });

        // For oft5
        oft5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st5.setVisibility(View.GONE);
                st6.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft5 here
            }
        });

        // For oft6
        oft6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st6.setVisibility(View.GONE);
                st7.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft6 here
            }
        });

        // For oft7
        oft7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st7.setVisibility(View.GONE);
                st8.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft7 here
            }
        });

        // For oft8
        oft8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st8.setVisibility(View.GONE);
                st9.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft8 here
            }
        });

        // For oft9
        oft9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st9.setVisibility(View.GONE);
                st10.setVisibility(View.VISIBLE);
                score += 4;
                // You can use specific actions for oft9 here
            }
        });

        // For oft10
        oft10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle specific actions for oft10 here
                getResult();
                st10.setVisibility(View.GONE);
            }
        });
    }

    private void getResult() {

        loading.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.GONE);
                result.setVisibility(View.VISIBLE);
            }
        },5000);

        long currentDateInMillis = System.currentTimeMillis();

        SharedPreferences sharedPreferences = getSharedPreferences("tests", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SSTIME, currentDateInMillis);
        editor.apply();

        scoreTxt.setText(score + "/" + "40");


        if( score == 0){
            DescTxt.setText("You have no stress!!!!");
            prof_help.setVisibility(View.INVISIBLE);


        } else if (score >= 1 && score <= 13) {

            DescTxt.setText("There are chances that you might have low stress");
            prof_help.setVisibility(View.INVISIBLE);
            ds1.setBackgroundColor(getColor(R.color.green));


        } else if (score > 13 && score <= 26) {

            DescTxt.setText("There are chances that you might moderate stress");
            prof_help.setVisibility(View.INVISIBLE);

            ds1.setBackgroundColor(getColor(R.color.green));
            ds2.setBackgroundColor(getColor(R.color.green));
            ds3.setBackgroundColor(getColor(R.color.yellow));


        } else if (score > 26 && score <=40) {

            DescTxt.setText("There are chances that you might have high perceived stress");
            ds1.setBackgroundColor(getColor(R.color.green));
            ds2.setBackgroundColor(getColor(R.color.green));
            ds3.setBackgroundColor(getColor(R.color.yellow));
            ds4.setBackgroundColor(getColor(R.color.red));
            ds5.setBackgroundColor(getColor(R.color.red));
            prof_help.setVisibility(View.VISIBLE);

        }

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        assert firebaseUser != null;
        firebaseFirestore.collection("users").document(firebaseUser.getUid()).update("stress_score",score);



    }
}