package com.example.mdpapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ServoControlActivity extends AppCompatActivity {
    Button openDoor, closeDoor, goBack;
    ApiCaller apiCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servo_control);

        openDoor = findViewById(R.id.openDoorButton);
        closeDoor = findViewById(R.id.closeDoorButton);
        goBack = findViewById(R.id.goBackButton3);

        apiCaller = ApiCaller.getInstance(this);

        openDoor.setOnClickListener(v -> {
            apiCaller.openDoor();
        });

        closeDoor.setOnClickListener(v -> {
            apiCaller.closeDoor();
        });

        goBack.setOnClickListener(v -> {
            finish();
        });
    }
}