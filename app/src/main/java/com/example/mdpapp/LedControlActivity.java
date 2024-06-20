package com.example.mdpapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LedControlActivity extends AppCompatActivity {
    Button goBack2, ledOn, ledOff;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_control);

        // findViewById [Start]
        goBack2 = findViewById(R.id.goBackButton2);
        ledOn = findViewById(R.id.ledOnButton);
        ledOff = findViewById(R.id.ledOffButton);
        // findViewById [End]

        // API Service Logic [Start]
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.220:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        // API Service Logic [End]

        // LED Buttons Logic [Start]
        ledOn.setOnClickListener(view -> {
            controlLed(apiService.turnOnLed());
        });

        ledOff.setOnClickListener(view -> {
            controlLed(apiService.turnOffLed());
        });
        // LED Buttons Logic [End]

        // goBack Button Logic [Start]
        goBack2.setOnClickListener(view -> {
            finish();
        });
        // goBack Button Logic [End]
    }

    // function Define [Start]
    private void controlLed(Call<ResponseBody> ledCall) {
        ledCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LedControlActivity.this, "LED command sent!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LedControlActivity.this, "Failed to send LED command!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LedControlActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    // function Define [End]
}
