package com.example.lukaszt.busloaction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class LocationSend implements Runnable {

    private int time = 1000;
    private GoogleApiClient googleApiClient;
    private int numberBus;
    private int lineBus;
    private int option;
    private boolean running;

    public LocationSend(GoogleApiClient googleApiClient, int numberBus, int lineBus, int option) {
        this.googleApiClient = googleApiClient;
        this.numberBus = numberBus;
        this.lineBus = lineBus;
        this.option = option;
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

                //activity.updateText(location, handler);
                Log.w("SEND LOCATION ", "info " + numberBus + " " + lineBus + " " + option + " " + location.getLatitude() + " " + location.getLongitude());
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning(){
        running = false;
    }


}
