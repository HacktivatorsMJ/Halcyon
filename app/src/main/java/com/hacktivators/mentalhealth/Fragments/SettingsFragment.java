package com.hacktivators.mentalhealth.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.hacktivators.mentalhealth.OnBoarding.LoginActivity;
import com.hacktivators.mentalhealth.OnBoarding.StartActivity;
import com.hacktivators.mentalhealth.R;


public class SettingsFragment extends Fragment {


    CardView logout_card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings,container,false);


        logout_card = view.findViewById(R.id.logout_card);


        logout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), StartActivity.class));
            }
        });



        return view;
    }
}