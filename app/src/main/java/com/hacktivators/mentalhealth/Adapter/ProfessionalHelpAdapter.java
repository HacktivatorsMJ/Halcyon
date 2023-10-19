package com.hacktivators.mentalhealth.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktivators.mentalhealth.Model.Doc;
import com.hacktivators.mentalhealth.R;

import java.util.ArrayList;

public class ProfessionalHelpAdapter extends RecyclerView.Adapter<ProfessionalHelpAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<Doc> docArrayList;

    public ProfessionalHelpAdapter(Context mContext,ArrayList<Doc> docArrayList) {
        this.mContext = mContext;
        this.docArrayList = docArrayList;


    }

    @NonNull
    @Override
    public ProfessionalHelpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.doc_item, parent, false);
        return new ProfessionalHelpAdapter.ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessionalHelpAdapter.ViewHolder holder, int position) {
        final Doc doc = docArrayList.get(position);


        holder.name.setText(doc.getName());
        Log.d("Test",doc.getName());
        holder.exp.setText(doc.getExp() + " years of experience");


        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ "+" + doc.getPhone_no()));//change the number
                mContext.startActivity(callIntent);
            }
        });

        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return docArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,exp;
        AppCompatButton call,book;



        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            exp = view.findViewById(R.id.years);

            call = view.findViewById(R.id.call);
            book = view.findViewById(R.id.book);



        }
    }
}
