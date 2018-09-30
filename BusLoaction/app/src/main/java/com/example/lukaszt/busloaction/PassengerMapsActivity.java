package com.example.lukaszt.busloaction;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PassengerMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static List<MarkerOptions> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        markers = new ArrayList<>();
        // Add a marker in Sydney and move the camera
        for(Bus b : Bus.values()){
            LatLng bus = new LatLng(b.lat, b.lon);
            BitmapDescriptor busIcon = BitmapDescriptorFactory.fromResource(R.raw.if_bus_37919);
            MarkerOptions marker = new MarkerOptions();
            marker.position(bus);
            marker.icon(busIcon);
            marker.title(b.getText());
            mMap.addMarker(marker);
            markers.add(marker);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bus, 11f));

        }
    }



    public void filter(View view) {

        String numberText = ((EditText)findViewById(R.id.passLineBus)).getText().toString();
        RadioGroup radioButtonGroup = findViewById(R.id.passRadio);
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        View radioButton = radioButtonGroup.findViewById(radioButtonID);
        int idx = radioButtonGroup.indexOfChild(radioButton);
        try {
            Integer number = new Integer(numberText);
            mMap.clear();
            markers = new ArrayList<>();
            for (Bus b : Bus.values()) {
                if(b.numberLine == number.intValue() && b.option == idx ){
                LatLng bus = new LatLng(b.lat, b.lon);
                BitmapDescriptor busIcon = BitmapDescriptorFactory.fromResource(R.raw.if_bus_37919);
                MarkerOptions marker = new MarkerOptions();
                marker.position(bus);
                marker.icon(busIcon);
                marker.title(b.getText());
                mMap.addMarker(marker);
                markers.add(marker);
                }
            }
        }catch (Exception ex){
            Log.e("PassengerMap", ex.getMessage());
        }
    }

    public void refresh(View view) {
        try {
            getNewLocation();
            mMap.clear();
            for (MarkerOptions m : markers) {
                mMap.addMarker(m);
            }
        }catch (Exception ex){
            Log.e("PassengerMap", ex.getMessage());
        }
    }

    private void getNewLocation() {
        MarkerOptions markerOptions = markers.get(0);
        LatLng ll = markerOptions.getPosition();
        Random random = new Random();
        LatLng ll2 = new LatLng(ll.latitude - 0.00001 * (random.nextInt(1000) - 500), ll.longitude - 0.00001 * (random.nextInt(1000) - 500));
        markerOptions.position(ll2);
    }
}
