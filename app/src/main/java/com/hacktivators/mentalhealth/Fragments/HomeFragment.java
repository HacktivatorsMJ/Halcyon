package com.hacktivators.mentalhealth.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktivators.mentalhealth.ChatActivity;
import com.hacktivators.mentalhealth.R;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    TextView greet;

    CircleImageView profile;
    String greeting = null;



    View chat_block;
    LinearLayout featured;

    TextView foryouTXT,featuredTXT;


    TextView art_recom,book_recom,vid_recom;
    String articleUrl,bookUrl,videoUrl;

    RelativeLayout article,book,video;


    LinearLayout morning,noon,night;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.recycler_view);

        featured = view.findViewById(R.id.featured);

        morning = view.findViewById(R.id.morning);
        noon = view.findViewById(R.id.noon);
        night = view.findViewById(R.id.night);


        article = view.findViewById(R.id.article_layout);
        book = view.findViewById(R.id.book_layout);
        video = view.findViewById(R.id.video_layout);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);


        foryouTXT = view.findViewById(R.id.for_youTXT);
        featuredTXT = view.findViewById(R.id.featuredTXT);

        featured.setVisibility(View.GONE);
        if(hour < 6){
            night.setVisibility(View.VISIBLE);
        }
        if (hour >= 6 && hour < 12) {
            morning.setVisibility(View.VISIBLE);
        } else if (hour >= 12 && hour <= 20) {
            noon.setVisibility(View.VISIBLE);
        } else if (hour >= 21) {
            night.setVisibility(View.VISIBLE);
        }

        art_recom = view.findViewById(R.id.article_recom);
        book_recom = view.findViewById(R.id.book_recom);
        vid_recom = view.findViewById(R.id.video_recom);

        chat_block = view.findViewById(R.id.chat_block);

        foryouTXT.setTextColor(getContext().getResources().getColor(R.color.primary));


        foryouTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featured.setVisibility(View.GONE);
                if(hour < 6){
                    night.setVisibility(View.VISIBLE);
                }
                if (hour >= 6 && hour < 12) {
                    morning.setVisibility(View.VISIBLE);
                } else if (hour >= 12 && hour <= 20) {
                    noon.setVisibility(View.VISIBLE);
                } else if (hour >= 21) {
                    night.setVisibility(View.VISIBLE);
                }

                foryouTXT.setTextColor(getContext().getResources().getColor(R.color.primary));
                featuredTXT.setTextColor(getContext().getResources().getColor(R.color.black));
            }

        });

        chat_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatActivity.class));

            }
        });

        featuredTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featured.setVisibility(View.VISIBLE);

                    morning.setVisibility(View.GONE);

                    noon.setVisibility(View.GONE);

                    night.setVisibility(View.GONE);

                foryouTXT.setTextColor(getContext().getResources().getColor(R.color.black));
                featuredTXT.setTextColor(getContext().getResources().getColor(R.color.primary));

            }
        });

       // loadData();
        //Set greeting


        if(hour > 0){
            greeting = "You should be sleeping..";
        }
        if(hour>=6 && hour<12){
            greeting = "Good Morning";
        } else if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
        } else if(hour >= 21 && hour < 24){
            greeting = "Good Night";
        }


        greet = view.findViewById(R.id.greet);



        profile = view.findViewById(R.id.profile);


        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(articleUrl); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(bookUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(videoUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        loadData();



        return view;
    }

    private void loadData() {


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        rootRef.collection("users").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String Username = document.getString("username");
                        String Imageurl = document.getString("imageURL");

                        greet.setText(greeting + "\n" + Username);

                    }
                }
            }
        });

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("recom").document("featured").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String bookTitle = document.getString("bookTitle");
                        String articleTitle = document.getString("articleTitle");
                        String videoTitle = document.getString("videoTitle");

                        String bookUrl_ = document.getString("bookUrl");
                        String articleUrl_ = document.getString("articleUrl");
                        String videoUrl_ = document.getString("videoUrl");

                        art_recom.setText(articleTitle);
                        book_recom.setText(bookTitle);
                        vid_recom.setText(videoTitle);

                        bookUrl = bookUrl_;
                        articleUrl = articleUrl_;
                        videoUrl = videoUrl_;


                    }
                }
            }
        });

    }


}