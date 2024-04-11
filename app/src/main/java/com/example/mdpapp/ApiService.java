package com.example.mdpapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("capture")
    Call<ResponseBody> captureImage();
}

