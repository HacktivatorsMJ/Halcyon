package com.hacktivators.mentalhealth.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktivators.mentalhealth.Model.Yoga;
import com.hacktivators.mentalhealth.R;
import com.hacktivators.mentalhealth.YogaReadActivity;
import com.hacktivators.mentalhealth.YogaViewActivity;

import java.util.ArrayList;

public class YogaViewAdapter extends RecyclerView.Adapter<YogaViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Yoga> mYogaArrayList;

    public YogaViewAdapter(Context mContext, ArrayList<Yoga> mYogaArrayList) {
        this.mContext = mContext;
        this.mYogaArrayList = mYogaArrayList;
    }

    @NonNull
    @Override
    public YogaViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.yoga_item, parent, false);
        return new YogaViewAdapter.ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaViewAdapter.ViewHolder holder, int position) {
        final Yoga yoga = mYogaArrayList.get(position);

        holder.title.setText(yoga.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, YogaReadActivity.class);
                intent.putExtra("yogaID",yoga.getId());

                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mYogaArrayList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView title;



        public ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title);




        }
    }
}
