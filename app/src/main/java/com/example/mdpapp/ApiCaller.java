package com.example.mdpapp;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCaller {
    private static final String BASE_URL = "http://192.168.137.220:5000/";
    private static ApiCaller instance;
    private ApiService apiService;
    private Context context;

    private ApiCaller(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiCaller getInstance(Context context) {
        if (instance == null) {
            instance = new ApiCaller(context);
        }
        return instance;
    }

    public void captureImage(ImageView imageView) {
        apiService.captureImage().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String imageUrl = BASE_URL + "capture";
                    Glide.with(context)
                            .load(imageUrl)
                            .diskCacheStrategy(DiskCacheStrategy.NONE) // 캐시 사용 안함
                            .skipMemoryCache(true) // 메모리 캐시 사용 안함
                            .into(imageView);
                } else {
                    Toast.makeText(context, "Failed to capture image", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void controlLed(String action) {
        Call<ResponseBody> call;
        if (action.equals("on")) {
            call = apiService.turnOnLed();
        } else {
            call = apiService.turnOffLed();
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "LED command sent!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to send LED command!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
