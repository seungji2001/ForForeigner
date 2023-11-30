package com.example.forforeiner.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forforeiner.BuildConfig;
import com.example.forforeiner.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Dto.LocalQADto.ResponseDto.AnswerInfo;
import Dto.LocalQADto.ResponseDto.Example;
import Dto.MapGeocoderDto.ResponseDto.GeocoderDto;
import Retrofit.MapRetrofitClient;
import Retrofit.MapRetrofitInterface;
import Retrofit.RetrofitClient;
import Retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nearLawCenter extends AppCompatActivity {

    private EditText et;

    private MapRetrofitClient mapRetrofitClient;
    private MapRetrofitInterface mapRetrofitInterface;

    GoogleMap gm;
    Marker centerMarker;
    Marker selectMarker;

    final int REQ_PERMISSION_CODE = 100;
    FusedLocationProviderClient flpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_law_center);

        et = findViewById(R.id.location);

        checkPermission();

        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        smf.getMapAsync(mapReadyCallback);

        flpClient = LocationServices.getFusedLocationProviderClient(this);
    }

    OnMapReadyCallback mapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            gm = googleMap;
            checkPermission();
            gm.setMyLocationEnabled(true);

            LatLng currentLoc = new LatLng(37.606320, 127.041808);
            gm.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 17));


            MarkerOptions options = new MarkerOptions();
            options.position(currentLoc);
            options.icon(BitmapDescriptorFactory.defaultMarker());
            options.title("현재 위치");
            options.snippet("이동중");

            centerMarker = gm.addMarker(options);
        }
    };
    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            // 권한이 있을 경우 수행할 동작
            Toast.makeText(this,"Permissions Granted", Toast.LENGTH_SHORT).show();
        } else {
            // 권한 요청
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQ_PERMISSION_CODE);
        }
    }


    public void findLocation(View view){
        String location = et.getText().toString();

        mapRetrofitClient = MapRetrofitClient.getInstance();
        mapRetrofitInterface = MapRetrofitClient.getMapRetrofitInterface();

        mapRetrofitInterface.geocoder(BuildConfig.GOOGLE_API_KEY, location).enqueue(new Callback<GeocoderDto>() {
            @Override
            public void onResponse(Call<GeocoderDto> call, Response<GeocoderDto> response) {
                GeocoderDto result = response.body();
                Log.d("retrofit", "Data fetch success");
                double latitude = result.getResults().get(0).getGeometry().getLocation().getLat();
                double longitude = result.getResults().get(0).getGeometry().getLocation().getLng();

                LatLng currentLoc = new LatLng(latitude, longitude);
                gm.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 20));

                MarkerOptions options = new MarkerOptions();
                options.position(currentLoc);
                options.icon(BitmapDescriptorFactory.defaultMarker());
                options.title("현재 위치");
                options.snippet("이동중");

                centerMarker = gm.addMarker(options);
            }

            @Override
            public void onFailure(Call<GeocoderDto> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });
    }
}