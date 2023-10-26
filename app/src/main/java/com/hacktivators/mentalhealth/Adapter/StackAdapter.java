package com.hacktivators.mentalhealth.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktivators.mentalhealth.Model.Stack;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class StackAdapter extends RecyclerView.Adapter<StackAdapter.ViewHolder> {

    private Context mContext;

    public ArrayList<Stack> mStackArrayList;

    public StackAdapter(Context mContext, ArrayList<Stack> mStackArrayList) {
        this.mContext = mContext;
        this.mStackArrayList = mStackArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.stack_view, parent, false);
        return new ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Stack stack = mStackArrayList.get(position);

        holder.statement.setText(stack.getStatement());



    }

    @Override
    public int getItemCount() {
        return mStackArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView statement;




        public ViewHolder(View view) {
            super(view);

            statement = view.findViewById(R.id.statement);



        }
    }

}
