package com.senior.helpet;
/*
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlaceDetectionClient;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

public class LostpetFragment extends AppCompatActivity  {

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.fragment_lostpet, container, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lostpet);

        // Construct a GeoDataClient.
        //GeoDataClient mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        //PlaceDetectionClient mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        //FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        int serviceAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LostpetFragment.this);

        if(serviceAvailable == ConnectionResult.SUCCESS) {
            //if maps service is available, initialize the upload location button
            Button btnOpenMap = (Button) findViewById(R.id.upload_location);
            btnOpenMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LostpetFragment.this, MapActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            Toast.makeText(this, "Map requests can't be made currently", Toast.LENGTH_SHORT).show();
        }
    }

}
*/


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.SupportMapFragment;

public class LostpetFragment extends AppCompatActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lostpet);

        if (isServicesOK()) {
            Button btnOpenMap = (Button) findViewById(R.id.upload_location);
            btnOpenMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LostpetFragment.this, MapActivity.class);
                    startActivity(intent);
                }
            });
        }
    }



    public boolean isServicesOK(){
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LostpetFragment.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(LostpetFragment.this, available, 9001);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}