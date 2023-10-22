package com.hacktivators.mentalhealth.OnBoarding.Questtionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.hacktivators.mentalhealth.R;

public class SetupQuestionActivity extends AppCompatActivity {

    SeekBar seekBar;

    TextView sleep_no;
    AppCompatButton next;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupq);

        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.sleep_bar);
        sleep_no = findViewById(R.id.sleep_no);
        seekBar.setMax(16);
        seekBar.setProgress(8);

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
                startActivity(new Intent(SetupQuestionActivity.this, SetupQuestion1Activity.class));
            }
        });


    }

    private void update(int progress) {

        // Update the SeekBar and TextView
        seekBar.setProgress(progress);
        sleep_no.setText(String.valueOf(progress));

    }

}
