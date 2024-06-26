package com.hacktivators.mentalhealth.OnBoarding.Questtionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.hacktivators.mentalhealth.Model.User;
import com.hacktivators.mentalhealth.OnBoarding.CreateUserLoaderActivity;
import com.hacktivators.mentalhealth.OnBoarding.LonelyActivity;
import com.hacktivators.mentalhealth.R;

import java.util.Objects;

public class SetupQuestionActivity extends AppCompatActivity {

    SeekBar seekBar;

    TextView sleep_no;
    AppCompatButton next;

    ImageView imageView;

    User user;

    Integer score = 8;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupq);

        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.sleep_bar);
        sleep_no = findViewById(R.id.sleep_no);
        seekBar.setMax(16);
        seekBar.setProgress(8);

        imageView = findViewById(R.id.imageView);

//        user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");

        seekBar.setOnSeekBarChangeListener(new AppCompatSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // onProgressChanged() method is invoked if the progress level is changed.
                // Call a method here and pass progress as parameter
                // to update the TextView.
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setSleep_score(score);
//                Intent intent = new Intent(SetupQuestionActivity.this,CreateUserLoaderActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);

                    startActivity(new Intent(SetupQuestionActivity.this, SetupQuestion1Activity.class));
            }
        });


    }

    private void update(int progress) {

        // Update the SeekBar and TextView

        score = progress;

        if(progress < 3){

            imageView.setImageDrawable(getDrawable(R.drawable.sleep3));

        }else if(progress >=3 && progress < 6){

            imageView.setImageDrawable(getDrawable(R.drawable.sleep2));

        } else if (progress >= 6 && progress <= 8){

            imageView.setImageDrawable(getDrawable(R.drawable.sleep1));

        }else if(progress > 8 && progress < 12){

            imageView.setImageDrawable(getDrawable(R.drawable.sleep2));

        } else if (progress >= 12) {
            imageView.setImageDrawable(getDrawable(R.drawable.sleep3));

        }
        seekBar.setProgress(progress);
        sleep_no.setText(String.valueOf(progress));

    }

}
