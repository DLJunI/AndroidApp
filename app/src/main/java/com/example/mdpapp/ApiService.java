package com.example.mdpapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("capture")
    Call<ResponseBody> captureImage();

    @GET("led/on")
    Call<ResponseBody> turnOnLed();

    @GET("led/off")
    Call<ResponseBody> turnOffLed();

    @GET("door/open")
    Call<ResponseBody> openDoor();

    @GET("door/close")
    Call<ResponseBody> closeDoor();
}

