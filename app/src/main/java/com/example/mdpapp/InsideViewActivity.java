package com.example.mdpapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsideViewActivity extends AppCompatActivity {
    ImageView imageFromPi;
    Button takePicture, goBack;
    ApiService apiService;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.220:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        // API Service Logic [End]

        // takePicture Button Logic [Start]
        takePicture.setOnClickListener(view -> {
            captureImage();
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
    private void captureImage() {
        apiService.captureImage().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String imageUrl = "http://192.168.137.220:5000/capture";
                    Glide.with(InsideViewActivity.this)
                            .load(imageUrl)
                            .diskCacheStrategy(DiskCacheStrategy.NONE) // 캐시 사용 안함
                            .skipMemoryCache(true) // 메모리 캐시 사용 안함
                            .into(imageFromPi);
                } else {
                    Toast.makeText(InsideViewActivity.this, "Failed to capture image", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(InsideViewActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    // function Define [End]
}
