package com.hacktivators.mentalhealth.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hacktivators.mentalhealth.JournalViewActivity;
import com.hacktivators.mentalhealth.R;


public class ExploreFragment extends Fragment {


    View journal;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore,container,false);


        journal = view.findViewById(R.id.journal);


        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JournalViewActivity.class));
            }
        });


        return view;
    }
}