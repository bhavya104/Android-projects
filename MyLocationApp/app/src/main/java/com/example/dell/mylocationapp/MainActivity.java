package com.example.dell.mylocationapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback ,TA {

    GoogleMap map;
    Button btnGetDirection;
    MarkerOptions place1,place2;

    Polyline currentPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetDirection = (Button)findViewById(R.id.btnGetDirection);
        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.mapFrag);

        mapFragment.getMapAsync(this);

        place1 = new MarkerOptions().position(new LatLng(30.516519, 76.659880).title("Location 1"));
        place2 = new MarkerOptions().position(new LatLng(30.514436, 76.661352).title("Location 2"));


        String url = getUrl(place1.getPosition(),place2.getPosition(),"drovong");
        new FetchURL(MainActivity.this).execute(url,"driving");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

    }

    private String getUrl(LatLng origin,LatLng dest,String directionMode){
     String str_origin = "origin" + origin.latitude + "," +origin.longitude;

     String str_dest = "destination=" + dest.latitude +","+dest.longitude;

     String mode = "mode=" + directionMode;
     String parameters = str_origin + "&" + mode;
     String output = "json";

     String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
    }
}
