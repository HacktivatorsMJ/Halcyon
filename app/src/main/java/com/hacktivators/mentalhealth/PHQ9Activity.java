package com.hacktivators.mentalhealth;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PHQ9Activity extends AppCompatActivity {

    private static final String DPTIME = "DPTIME";
    RelativeLayout q1,q2,q3,q4,q5,q6,q7,q8,q9,loading,prof_help,result;

    AppCompatButton naa1,naa2,naa3,naa4,naa5,naa6,naa7,naa8,naa9;
    AppCompatButton sd1,sd2,sd3,sd4,sd5,sd6,sd7,sd8,sd9;
    AppCompatButton mth1,mth2,mth3,mth4,mth5,mth6,mth7,mth8,mth9;
    AppCompatButton ne1,ne2,ne3,ne4,ne5,ne6,ne7,ne8,ne9;

    AppCompatButton done;

    TextView scoreTxt,DescTxt;

    View ds1,ds2,ds3,ds4,ds5;

    int score = 0;

    int shaded = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phq9);


        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);
        q6 = findViewById(R.id.q6);
        q7 = findViewById(R.id.q7);
        q8 = findViewById(R.id.q8);
        q9 = findViewById(R.id.q9);


        naa1 = findViewById(R.id.naa1);
        naa2 = findViewById(R.id.naa2);
        naa3 = findViewById(R.id.naa3);
        naa4 = findViewById(R.id.naa4);
        naa5 = findViewById(R.id.naa5);
        naa6 = findViewById(R.id.naa6);
        naa7 = findViewById(R.id.naa7);
        naa8 = findViewById(R.id.naa8);
        naa9 = findViewById(R.id.naa9);

        sd1 = findViewById(R.id.sd1);
        sd2 = findViewById(R.id.sd2);
        sd3 = findViewById(R.id.sd3);
        sd4 = findViewById(R.id.sd4);
        sd5 = findViewById(R.id.sd5);
        sd6 = findViewById(R.id.sd6);
        sd7 = findViewById(R.id.sd7);
        sd8 = findViewById(R.id.sd8);
        sd9 = findViewById(R.id.sd9);

        mth1 = findViewById(R.id.mth1);
        mth2 = findViewById(R.id.mth2);
        mth3 = findViewById(R.id.mth3);
        mth4 = findViewById(R.id.mth4);
        mth5 = findViewById(R.id.mth5);
        mth6 = findViewById(R.id.mth6);
        mth7 = findViewById(R.id.mth7);
        mth8 = findViewById(R.id.mth8);
        mth9 = findViewById(R.id.mth9);

        ne1 = findViewById(R.id.ne1);
        ne2 = findViewById(R.id.ne2);
        ne3 = findViewById(R.id.ne3);
        ne4 = findViewById(R.id.ne4);
        ne5 = findViewById(R.id.ne5);
        ne6 = findViewById(R.id.ne6);
        ne7 = findViewById(R.id.ne7);
        ne8 = findViewById(R.id.ne8);
        ne9 = findViewById(R.id.ne9);

        prof_help = findViewById(R.id.help_btn);
        done = findViewById(R.id.done);
        ds1 = findViewById(R.id.ds1);
        ds2 = findViewById(R.id.ds2);
        ds3 = findViewById(R.id.ds3);
        ds4 = findViewById(R.id.ds4);
        ds5 = findViewById(R.id.ds5);

        DescTxt = findViewById(R.id.depression_status);


        result = findViewById(R.id.result);

        loading = findViewById(R.id.loading);


        scoreTxt = findViewById(R.id.desc);



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PHQ9Activity.this,MainActivity.class));
            }
        });


        prof_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PHQ9Activity.this, ProfessionalHelpActivity.class));
            }
        });


        naa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa1
                q1.setVisibility(View.GONE);
                q2.setVisibility(View.VISIBLE);
            }
        });


        naa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa2
                q2.setVisibility(View.GONE);
                q3.setVisibility(View.VISIBLE);
            }
        });

        naa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa3
                q3.setVisibility(View.GONE);
                q4.setVisibility(View.VISIBLE);
            }
        });

        naa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa4
                q4.setVisibility(View.GONE);
                q5.setVisibility(View.VISIBLE);
            }
        });

        naa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa5
                q5.setVisibility(View.GONE);
                q6.setVisibility(View.VISIBLE);
            }
        });

        naa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa6
                q6.setVisibility(View.GONE);
                q7.setVisibility(View.VISIBLE);
            }
        });

        naa7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa7
                q7.setVisibility(View.GONE);
                q8.setVisibility(View.VISIBLE);
            }
        });

        naa8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa8
                q8.setVisibility(View.GONE);
                q9.setVisibility(View.VISIBLE);
            }
        });

        naa9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for naa9

                // Handle the last question, as there's no q10 in your example
            }
        });




        sd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd1
                q1.setVisibility(View.GONE);
                q2.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd2
                q2.setVisibility(View.GONE);
                q3.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd3
                q3.setVisibility(View.GONE);
                q4.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd4
                q4.setVisibility(View.GONE);
                q5.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd5
                q5.setVisibility(View.GONE);
                q6.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd6
                q6.setVisibility(View.GONE);
                q7.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd7
                q7.setVisibility(View.GONE);
                q8.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd8
                q8.setVisibility(View.GONE);
                q9.setVisibility(View.VISIBLE);
                score += 1;
            }
        });

        sd9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for sd9
                q9.setVisibility(View.GONE);


                CalculateResult();
                // Handle the last question, as there's no q10 in your example
                score += 1;
                shaded += 1;
            }
        });






        mth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth1
                q1.setVisibility(View.GONE);
                q2.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth2
                q2.setVisibility(View.GONE);
                q3.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth3
                q3.setVisibility(View.GONE);
                q4.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth4
                q4.setVisibility(View.GONE);
                q5.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth5
                q5.setVisibility(View.GONE);
                q6.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth6
                q6.setVisibility(View.GONE);
                q7.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth7
                q7.setVisibility(View.GONE);
                q8.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth8
                q8.setVisibility(View.GONE);
                q9.setVisibility(View.VISIBLE);
                score += 2;
                shaded += 1;
            }
        });

        mth9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for mth9
                q9.setVisibility(View.GONE);
                CalculateResult();
                // Handle the last question, as there's no q10 in your example
                score += 2;
                shaded += 1;
            }
        });





        ne1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne1
                q1.setVisibility(View.GONE);
                q2.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne2
                q2.setVisibility(View.GONE);
                q3.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne3
                q3.setVisibility(View.GONE);
                q4.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne4
                q4.setVisibility(View.GONE);
                q5.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne5
                q5.setVisibility(View.GONE);
                q6.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne6
                q6.setVisibility(View.GONE);
                q7.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne7
                q7.setVisibility(View.GONE);
                q8.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne8
                q8.setVisibility(View.GONE);
                q9.setVisibility(View.VISIBLE);
                shaded += 1;
                score += 3;
            }
        });

        ne9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for ne9
                q9.setVisibility(View.GONE);
                CalculateResult();
                // Handle the last question, as there's no q10 in your example
                shaded += 1;
                score += 3;
            }
        });








    }

    private void CalculateResult() {


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
        editor.putLong(DPTIME, currentDateInMillis);
        editor.apply();

        scoreTxt.setText(score + "/" + "27");


        if( score == 0){
            DescTxt.setText("You have no depression!!!!");
            prof_help.setVisibility(View.GONE);


        } else if (score >= 1 && score <= 4) {

            DescTxt.setText("There are chances that you might have minimal depression");
            prof_help.setVisibility(View.GONE);
            ds1.setBackgroundColor(getColor(R.color.green));


        } else if (score > 4 && score <= 9) {

            DescTxt.setText("There are chances that you might have mild depression");
            prof_help.setVisibility(View.GONE);

            ds1.setBackgroundColor(getColor(R.color.green));
            ds2.setBackgroundColor(getColor(R.color.green));

            
        } else if (score > 9 && score <=14) {

            DescTxt.setText("There are chances that you might have moderate depression");
            ds1.setBackgroundColor(getColor(R.color.green));
            ds2.setBackgroundColor(getColor(R.color.green));
            ds3.setBackgroundColor(getColor(R.color.yellow));

        } else if (score > 14 && score <= 19) {
            DescTxt.setText("There are chances that you might have moderately severe depression");
            ds1.setBackgroundColor(getColor(R.color.green));
            ds2.setBackgroundColor(getColor(R.color.green));
            ds3.setBackgroundColor(getColor(R.color.yellow));
            ds4.setBackgroundColor(getColor(R.color.red));
        } else if (score > 20 && score <= 27) {
            DescTxt.setText("There are chances that you might have severe depression");
            ds1.setBackgroundColor(getColor(R.color.green));
            ds2.setBackgroundColor(getColor(R.color.green));
            ds3.setBackgroundColor(getColor(R.color.yellow));
            ds4.setBackgroundColor(getColor(R.color.red));
            ds5.setBackgroundColor(getColor(R.color.red));


        }


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        assert firebaseUser != null;
        firebaseFirestore.collection("users").document(firebaseUser.getUid()).update("DepressionScore",score);


    }
}