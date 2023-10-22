package com.hacktivators.mentalhealth.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktivators.mentalhealth.ChatActivity;
import com.hacktivators.mentalhealth.Journal.JournalViewActivity;
import com.hacktivators.mentalhealth.MeditationPageActivity;
import com.hacktivators.mentalhealth.PHQ9Activity;
import com.hacktivators.mentalhealth.R;
import com.hacktivators.mentalhealth.SleepReminderActivity;
import com.hacktivators.mentalhealth.StressTestActivity;
import com.hacktivators.mentalhealth.TaskActivity;
import com.hacktivators.mentalhealth.WellnessPagesActivity;


public class ExploreFragment extends Fragment {


    View journal,wellness,chat,meditation,dst,sst,tasks,sleep;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore,container,false);


        journal = view.findViewById(R.id.journal);
        wellness = view.findViewById(R.id.wellness);
        chat = view.findViewById(R.id.chat);
        meditation = view.findViewById(R.id.meditation);
        dst = view.findViewById(R.id.dst);
        sst = view.findViewById(R.id.sst);
        tasks = view.findViewById(R.id.tasks);
        sleep = view.findViewById(R.id.sleep);


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });

        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JournalViewActivity.class));
            }
        });

        meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeditationPageActivity.class));
            }
        });

        dst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PHQ9Activity.class));
            }
        });

        sst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StressTestActivity.class));
            }
        });

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SleepReminderActivity.class));
            }
        });

        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TaskActivity.class));
            }
        });

        wellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WellnessPagesActivity.class));
            }
        });


        return view;
    }
}