package com.hacktivators.mentalhealth.Adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final Context mContext;




    public HomeAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public static final int JOURNAL = 0;
    public static final int MUSIC = 1;

    public static final int ARTICLE = 2;
    public static final int CHAT = 3;

    public static final int BOOK = 4;


    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == JOURNAL) {

            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.journel_block, parent, false);
            return new HomeAdapter.ViewHolder(viewGroup);
        }
        if (viewType == MUSIC) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.music_recommend, parent, false);
            return new HomeAdapter.ViewHolder(viewGroup);

        }

        if (viewType == ARTICLE) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.article_recommends, parent, false);
            return new HomeAdapter.ViewHolder(viewGroup);
        }

        if (viewType == CHAT) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.chat_block, parent, false);
            return new HomeAdapter.ViewHolder(viewGroup);
        } else if (viewType == BOOK) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.book_recommend, parent, false);
            return new HomeAdapter.ViewHolder(viewGroup);
        }
        return null;
    }


    @Override
    public int getItemViewType(int position){

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour>=6 && hour<12){
            return JOURNAL;

        } else if(hour>= 12 && hour < 17){
            return ARTICLE;

        } else if(hour >= 17 && hour < 21){
            return BOOK;

        } else if(hour >= 21){
           return MUSIC;

        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {









    }

    @Override
    public int getItemCount() {
        return 2;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(View view) {
            super(view);



        }
    }


}
