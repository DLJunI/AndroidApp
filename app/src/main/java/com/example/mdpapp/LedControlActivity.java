package com.example.mdpapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LedControlActivity extends AppCompatActivity {
    Button goBack2, ledOn, ledOff;
    ApiCaller apiCaller;

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
        apiCaller = ApiCaller.getInstance(this);
        // API Service Logic [End]

        // LED Buttons Logic [Start]
        ledOn.setOnClickListener(view -> {
            apiCaller.controlLed("on");
        });

        ledOff.setOnClickListener(view -> {
            apiCaller.controlLed("off");
        });
        // LED Buttons Logic [End]

        // goBack Button Logic [Start]
        goBack2.setOnClickListener(view -> {
            finish();
        });
        // goBack Button Logic [End]
    }

    // function Define [Start]
        //Move to Api Caller
    // function Define [End]
}
