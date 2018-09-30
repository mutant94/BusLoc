package com.example.lukaszt.busloaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
    }


    public void openDriver(View view) {
        Intent intent = new Intent(this, DriverConfigurationActivity.class);
        startActivity(intent);
    }

    public void openPassenger(View view) {
        Intent intent = new Intent(this, PassengerMapsActivity.class);
        startActivity(intent);
    }
}
