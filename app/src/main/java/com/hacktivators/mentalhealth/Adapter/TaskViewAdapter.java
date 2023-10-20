package com.hacktivators.mentalhealth.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.Model.Task;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.ViewHolder> {

    private ArrayList<Task> taskArrayList;

    private Context mContext;

    EventListener refresh_listener;

    FirebaseUser firebaseUser;
    public interface EventListener {
        void refresh_fragment();
    }

    public TaskViewAdapter(ArrayList<Task> taskArrayList, Context mContext,FirebaseUser firebaseUser) {
        this.taskArrayList = taskArrayList;
        this.mContext = mContext;
        this.firebaseUser = firebaseUser;
    }

    public void addEventListener(EventListener refresh_listener){
        this.refresh_listener = refresh_listener;
    }
    public void removeEventListener(){
        refresh_listener = null;
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

        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());



        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean check;
                if(isChecked){
                    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                    check = true;

                    //firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("tasks").document(task.getId()).delete();
                    holder.title.setBackground(mContext.getDrawable(R.drawable.strike_through));
                    holder.title.setTextColor(mContext.getColor(androidx.cardview.R.color.cardview_dark_background));
                    holder.description.setBackground(mContext.getDrawable(R.drawable.strike_through));
                    holder.description.setTextColor(mContext.getColor(androidx.cardview.R.color.cardview_dark_background));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(check){
                                firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("tasks").document(task.getId()).delete();
                                holder.checkBox.setChecked(false);
                                refresh_listener.refresh_fragment();
                            }

                        }
                    },5000);

                }else {
                    check = false;
                    holder.title.setBackground(null);
                    holder.title.setTextColor(mContext.getColor(R.color.secondary));
                    holder.description.setBackground(null);
                    holder.description.setTextColor(mContext.getColor(R.color.secondary));
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        
        TextView title,description;

        CheckBox checkBox;





        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            checkBox = view.findViewById(R.id.checkbox);




        }
    }


}
