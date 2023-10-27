package com.hacktivators.mentalhealth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.Fragments.ChatConnectFragment;
import com.hacktivators.mentalhealth.Fragments.ExploreFragment;
import com.hacktivators.mentalhealth.Fragments.HomeFragment;
import com.hacktivators.mentalhealth.Fragments.ProfileFragment;
import com.hacktivators.mentalhealth.Fragments.SwipeQuesFragment;
import com.hacktivators.mentalhealth.Hamburger.AboutUsActivity;
import com.hacktivators.mentalhealth.Hamburger.ProfessionalHelpActivity;
import com.hacktivators.mentalhealth.Hamburger.SettingsActivity;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends FragmentActivity {

    AppCompatButton start;

    DrawerLayout drawerLayout;

    NavigationView navigationView;

    BottomNavigationView bottomNavigationView;

    CircleImageView profile;

    ImageView hamburger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigation_view);

        hamburger = findViewById(R.id.hamburger);




        profile = findViewById(R.id.profile);

        loadData();



        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        loadFragment(new HomeFragment());

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProfileFragment());
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.about_us) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                    // TO BE ADDED
                } else if (itemId == R.id.feedback) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    // TO BE ADDED
                } else if (itemId == R.id.profess_help) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(MainActivity.this, ProfessionalHelpActivity.class));
                } else if (itemId == R.id.t_c) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    // TO BE ADDED
                } else if (itemId == R.id.p_p) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    // TO BE ADDED
                }else if (itemId == R.id.settings){
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return false;

            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                int itemId = item.getItemId();
                Fragment fragment;

                if (itemId == R.id.home) {
                    fragment = new HomeFragment();
                } else if (itemId == R.id.explore) {
                    fragment = new ExploreFragment();
                } else if (itemId == R.id.connect) {
                    fragment = new SwipeQuesFragment();
                } else if (itemId == R.id.chat) {
                    fragment = new ChatConnectFragment();
                } else {
                    return false;
                }

                loadFragment(fragment);
                return true;
            }
        });


    }

    private void loadData() {


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        rootRef.collection("users").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //String Username = document.getString("username");
                        String Imageurl = document.getString("imageURL");


                        Glide.with(getApplicationContext()).load(Imageurl).into(profile);


                    }
                }
            }
        });

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}