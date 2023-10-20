package com.hacktivators.mentalhealth.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktivators.mentalhealth.Model.Task;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.ViewHolder> {

    private ArrayList<Task> taskArrayList;

    private Context mContext;


    public TaskViewAdapter(ArrayList<Task> taskArrayList, Context mContext) {
        this.taskArrayList = taskArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.tasks_item, parent, false);
        return new TaskViewAdapter.ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Task task = taskArrayList.get(position);



    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {





        public ViewHolder(View view) {
            super(view);




        }
    }


}
