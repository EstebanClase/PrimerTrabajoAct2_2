package com.example.primertrabajo;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView txt_nombreEscogido;
    Button btn_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txt_nombreEscogido = (TextView) findViewById(R.id.txt_nombreEscogido);
        Intent inte = this.getIntent();
        String n = inte.getStringExtra("nickname").toString();
        txt_nombreEscogido.setText(n);
        btn_perfil = (Button)findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Perfil();
            }
        });
        int estado = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(estado == ConnectionResult.SUCCESS) {

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else{
            Dialog mens = GooglePlayServicesUtil.getErrorDialog(estado,(Activity) getApplicationContext(), 10);
            mens.show();
        }
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings USet = mMap.getUiSettings();
        USet.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng LosParrones = new LatLng(-30.610999, -71.214327);
        mMap.addMarker(new MarkerOptions().position(LosParrones).title("Marcador en Los Parrones").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        float zoom = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LosParrones, zoom));
    }

    public void Perfil(){
        Intent perfil = new Intent(this, Perfil.class);
        startActivity(perfil);
    }
}