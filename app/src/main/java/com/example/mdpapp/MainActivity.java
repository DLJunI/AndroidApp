package com.example.mdpapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewInSide = findViewById(R.id.viewInSideButton);
        Intent toInsiderViewActivty = new Intent(MainActivity.this, InsideViewActivity.class);

        Button insideLedControl = findViewById(R.id.insideLedControlButton);
        Intent toLedControlActivity = new Intent(MainActivity.this, LedControlActivity.class);

        viewInSide.setOnClickListener(view -> {
            startActivity(toInsiderViewActivty);
        });

        insideLedControl.setOnClickListener(v -> {
            startActivity(toLedControlActivity);
        });
    }
}