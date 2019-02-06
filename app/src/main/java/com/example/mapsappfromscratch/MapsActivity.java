package com.example.mapsappfromscratch;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static com.example.mapsappfromscratch.MuseumsViewholder.ADDRESS;
import static com.example.mapsappfromscratch.MuseumsViewholder.CITY;
import static com.example.mapsappfromscratch.MuseumsViewholder.MUSEUM_NAME;
import static com.example.mapsappfromscratch.MuseumsViewholder.STATE;
import static com.example.mapsappfromscratch.MuseumsViewholder.ZIP;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private String museumName;
    private String museumAddress;
    private String city;
    private String state;
    private String zip;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        museumName = intent.getStringExtra(MUSEUM_NAME);
        museumAddress = intent.getStringExtra(ADDRESS);
        city = intent.getStringExtra(CITY);
        state = intent.getStringExtra(STATE);
        zip = intent.getStringExtra(ZIP);


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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //Adds the zoom in and zoom out settings
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setAllGesturesEnabled(true);

        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<Address> address;
        LatLng p1;

        try{
            address = geocoder.getFromLocationName(museumAddress +","+ city + "," +state +","+zip,5);
            if (address != null){
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p1).title("Marker in -  " + museumName));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(p1));
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
