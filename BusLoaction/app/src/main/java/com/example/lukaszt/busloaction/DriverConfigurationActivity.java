package com.example.lukaszt.busloaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.LocationServices;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class DriverConfigurationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Location location;
    private GoogleApiClient googleApiClient;
    private LocationSend locationSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_configuration);
        getSupportActionBar().hide();
        TextView txtView = findViewById(R.id.driverLocation);
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

        }
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    public void start(View view) {
        if (locationSend == null) {
            try {
                String numberBus = ((EditText) findViewById(R.id.numberBus)).getText().toString();
                String lineBus = ((EditText) findViewById(R.id.lineBus)).getText().toString();
                RadioGroup radioButtonGroup = findViewById(R.id.driverRadio);
                int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
                View radioButton = radioButtonGroup.findViewById(radioButtonID);
                int idx = radioButtonGroup.indexOfChild(radioButton);

                TextView txtView = findViewById(R.id.driverLocation);
                txtView.setText("lat:connect");
                Integer numberBusInt = new Integer(numberBus);
                Integer lineBusInt = new Integer(lineBus);

                locationSend = new LocationSend(googleApiClient, numberBusInt, lineBusInt, idx);
                locationSend.run();
            } catch (Exception ex) {
                Log.e("LOCATION DRIVER", ex.getMessage());

            }
        }else{
            locationSend.stopRunning();
            locationSend = null;
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            TextView txtView = findViewById(R.id.driverLocation);
            txtView.setText("lat:" + location.getLatitude() + " lon:" + location.getLongitude());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        TextView txtView = findViewById(R.id.driverLocation);
        txtView.setText("lat:" + location.getLatitude() + " lon:" + location.getLongitude());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
