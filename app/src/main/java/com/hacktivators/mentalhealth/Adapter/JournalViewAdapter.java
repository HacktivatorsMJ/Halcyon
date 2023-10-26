package com.hacktivators.mentalhealth.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktivators.mentalhealth.Wellness.Journal.JournalReadActivity;
import com.hacktivators.mentalhealth.Model.Journal;
import com.hacktivators.mentalhealth.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class JournalViewAdapter extends RecyclerView.Adapter<JournalViewAdapter.ViewHolder> {

    private ArrayList<Journal> journalArrayList;

    private Context mContext;


    public JournalViewAdapter(ArrayList<Journal> journalArrayList, Context mContext) {
        this.journalArrayList = journalArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.journal_item, parent, false);
        return new JournalViewAdapter.ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Journal journal = journalArrayList.get(position);

        holder.journal.setText(journal.getJournal());
        long time = journal.getDate();


        SimpleDateFormat df = new SimpleDateFormat("hh:mm", Locale.US);
        String Time = df.format(new Date());
        holder.time.setText(Time);

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(time);
        holder.date.setText(date);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, JournalReadActivity.class);
                intent.putExtra("JournalID",journal.getId());
                Log.d("JournalID",journal.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return journalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView time,date,journal;



        public ViewHolder(View view) {
            super(view);

            time = view.findViewById(R.id.time);
            date = view.findViewById(R.id.date);
            journal = view.findViewById(R.id.journal);



        }
    }
}
