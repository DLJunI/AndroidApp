package com.example.mdpapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InsideViewActivity extends AppCompatActivity {
    ImageView imageFromPi;
    Button takePicture, goBack;
    ApiCaller apiCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_view);

        // findViewById [Start]
        takePicture = findViewById(R.id.takePictureButton);
        goBack = findViewById(R.id.goBackButton);
        imageFromPi = findViewById(R.id.ImageFromPiImageView);
        // findViewById [End]

        // API Service Logic [Start]
        apiCaller = ApiCaller.getInstance(this);
        // API Service Logic [End]


        // takePicture Button Logic [Start]
        takePicture.setOnClickListener(view -> {
            apiCaller.captureImage(imageFromPi);
        });
        // takePicture Button Logic [End]

        // goBack Button Logic [Start]
        goBack.setOnClickListener(view -> {
            finish();
            imageFromPi.setImageResource(R.drawable.wait_for_image);
        });
        // goBack Button Logic [End]
    }

    // function Define [Start]
        //Move to Api Caller
    // function Define [End]
}
