package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagramclone.fragments.ProfileFragment;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailViewActivity extends AppCompatActivity {

    TextView tvUserTop;
    TextView tvUserBottom;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvDate;
    ImageView ivProfilePic;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        tvUserTop = findViewById(R.id.tvUserTop);
        tvUserBottom = findViewById(R.id.tvUserBottom);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvDate = findViewById(R.id.tvDate);
        ivProfilePic = findViewById(R.id.ivProfilePic);

        post = (Post) getIntent().getParcelableExtra("post");
        tvUserTop.setText(post.getUser().getUsername());
        tvUserBottom.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        tvDate.setText(post.getKeyCreatedAt());
        Glide.with(this).load(post.getImage().getUrl()).into(ivImage);
        Glide.with(this).load(post.getUser().getParseFile("profilePic").getUrl()).into(ivProfilePic);

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm EEE MMM d yyyy");
        Date date = post.getCreatedAt();
        String formattedDate = parser.format(date);
        tvDate.setText(formattedDate);

        tvUserTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailViewActivity.this, UserProfileView.class);
                intent.putExtra("user",post.getUser());
                startActivity(intent);
            }
        });
    }
}