package com.hacktivators.mentalhealth.BackEnd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import com.hacktivators.mentalhealth.Wellness.NatureWalkActivity;

public class MyLocationManager {

    private LocationManager locationManager;
    private LocationListener locationListener;

    public MyLocationManager(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        initLocationListener();
    }

    private void initLocationListener() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                // Now you can use latitude and longitude as the user's location
                // For example, you can call a method to fetch nearby parks with this location
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Handle status changes if needed
            }

            @Override
            public void onProviderEnabled(String provider) {
                // Handle provider enabled
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Handle provider disabled
            }
        };
    }

    public void setActivityReference(Activity activity) {
        this.activity = activity;
    }

    private void initLocationListener() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                // Call the method in the activity to handle the location update
                if (activity != null) {
                    ((NatureWalkActivity) activity).handleLocationUpdate(latitude, longitude);
                }
            }

            // ... (other LocationListener methods)
        };
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        // Check if location permissions are granted before requesting updates

        // Register for location updates

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,   // Minimum time interval between updates in milliseconds (e.g., 1000ms = 1s)
                10,     // Minimum distance between updates in meters
                locationListener
        );
    }

    public void stopLocationUpdates() {
        // Stop receiving location updates
        locationManager.removeUpdates(locationListener);
    }
}
