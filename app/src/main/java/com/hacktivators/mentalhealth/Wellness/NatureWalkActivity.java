package com.hacktivators.mentalhealth.Wellness;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.hacktivators.mentalhealth.BackEnd.MyLocationManager;
import com.hacktivators.mentalhealth.R;

import java.util.Arrays;
import java.util.List;

public class NatureWalkActivity extends AppCompatActivity {




    private FusedLocationProviderClient fusedLocationProviderClient;
    SeekBar seekBar;

    TextView timerTxt;

    AppCompatButton start;

    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    Boolean counterIsActive = false;

    private MyLocationManager locationManager;

    double latitude;
    double longitude;

    int radius =3000;

    PlacesClient placesClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naturewalk);

        seekBar = findViewById(R.id.seekbar);
        timerTxt = findViewById(R.id.timer);
        start = findViewById(R.id.start_btn);

        timerTxt.setText("30:00");

        Places.initialize(getApplicationContext(), "AIzaSyBh9xshULCTEnWr9XCktcegaJLfusvCaio");
        placesClient = Places.createClient(this);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_timer();
            }
        });

        locationManager = new MyLocationManager(this);
        locationManager.startLocationUpdates();

        locationManager.setActivityReference(NatureWalkActivity.this);



        seekBar.setMax(7200);
        // I am setting the current default progress with 30, for 30 seconds
        seekBar.setProgress(1800);

        seekBar.setMin(300);
        // Attach setOnSeekBarChangeListener to get notified of the user's actions
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


        loadData();


    }

    @SuppressLint("MissingPermission")
    private void loadData() {

        String placeType = "park";

        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.TYPES);

        // Create a request object
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);

        // Use the placesClient to send the request
        placesClient.findCurrentPlace(request).addOnSuccessListener((response) -> {
            for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                Place place = placeLikelihood.getPlace();

                double likelihood = placeLikelihood.getLikelihood();


                    List<Place.Type> placeTypes = place.getTypes();
                    Log.i(TAG, "Place: " + place.getName() + ", Likelihood: " + likelihood + ", Types: " + place.getId());

                    // Process the places as needed

            }
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode() + ", " + apiException.getStatusMessage());
            }
        });





    }

    private void start_timer() {

        if(!counterIsActive){
            counterIsActive = true;
            seekBar.setEnabled(false);
            start.setText("Stop");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    reset();



                }
            }.start();
        }else{

            reset();
        }
    }


    private boolean containsPark(List<Place.Type> placeTypes) {
        for (Place.Type type : placeTypes) {
            if (type.toString().equalsIgnoreCase("PARK")) {
                return true;
            }
        }
        return false;
    }


    public void handleLocationUpdate(double latitude_, double longitude_) {
        // Log the latitude and longitude
        Log.d("LocationUpdate", "Latitude: " + latitude_ + ", Longitude: " + longitude_);
        latitude = latitude_;
        longitude = longitude_;
    }



    // Define reset() method
    private void reset() {
        timerTxt.setText("30:00");
        seekBar.setProgress(1800);
        countDownTimer.cancel();
        start.setText("Start");
        mediaPlayer.stop();
        seekBar.setEnabled(true);
        counterIsActive = false;
    }

    // Define update() method
    private void update(int progress) {
        // Divide progress by 60 to get the minutes
        int minutes = progress / 60;
        // Divide progress by 60 and get the remainder for seconds
        int seconds = progress % 60;
        String secondsFinal = "";
        // If the value of seconds is less than or equal to 9,
        // print a leading zero if you want to show seconds in 2 digits format
        if(seconds <= 9){
            secondsFinal = "0" + seconds;
        }else{
            secondsFinal = "" + seconds;
        }
        // Update the SeekBar and TextView
        seekBar.setProgress(progress);
        timerTxt.setText("" + minutes + ":" + secondsFinal);
    }

    // In onPause() and onDestroy(), if the counterIsActive flag is true,
    // cancel countDownTimer.
    @Override
    protected void onPause() {
        super.onPause();
        if(counterIsActive){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(counterIsActive){
            countDownTimer.cancel();
        }
        locationManager.stopLocationUpdates();
    }



}
