package com.hacktivators.mentalhealth.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktivators.mentalhealth.Chat.Bots.ChatActivity;
import com.hacktivators.mentalhealth.Chat.Bots.PersonChatbotActivity;
import com.hacktivators.mentalhealth.Wellness.Journal.JournalViewActivity;
import com.hacktivators.mentalhealth.Wellness.Meditation.MeditationPageActivity;
import com.hacktivators.mentalhealth.Wellness.MusicTherapyActivity;
import com.hacktivators.mentalhealth.Tests.PHQ9Activity;
import com.hacktivators.mentalhealth.R;
import com.hacktivators.mentalhealth.Wellness.SleepReminderActivity;
import com.hacktivators.mentalhealth.Tests.StressTestActivity;
import com.hacktivators.mentalhealth.Tasks.TaskActivity;
import com.hacktivators.mentalhealth.Wellness.WellnessPagesActivity;


public class ExploreFragment extends Fragment {


    View journal,wellness,chat,meditation,dst,sst,tasks,sleep,music;






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
        music = view.findViewById(R.id.music);


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PersonChatbotActivity.class));
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

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MusicTherapyActivity.class));
            }
        });


        return view;
    }
}