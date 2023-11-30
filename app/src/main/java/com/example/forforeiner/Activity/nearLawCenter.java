package com.example.forforeiner.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forforeiner.BuildConfig;
import com.example.forforeiner.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelManager;

import java.util.List;

import Dto.MapGeocoderDto.ResponseDto.GeocoderDto;
import Dto.TranslateDto.ResponseDto.ResponseTranslateDto;
import Retrofit.MapRetrofitClient;
import Retrofit.MapRetrofitInterface;
import Retrofit.NaverRetrofitClient;
import Retrofit.RetrofitClient;
import Retrofit.RetrofitInterface;
import dalvik.annotation.optimization.CriticalNative;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nearLawCenter extends AppCompatActivity {

    private EditText et;
    final int REQ_PERMISSION_CODE = 100;

    FusedLocationProviderClient flpClient;

    private MapRetrofitClient mapRetrofitClient;
    private MapRetrofitInterface mapRetrofitInterface;

    private Label label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_law_center);

        et = findViewById(R.id.location);

        showFirstLocation();
    }

    private void showFirstLocation(){
        checkPermission();
        flpClient = LocationServices.getFusedLocationProviderClient(this);
        flpClient.requestLocationUpdates(
                getLocationRequest(),
                mLocCallback,
                Looper.getMainLooper()
        );
    }

    LocationCallback mLocCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            for (Location loc : locationResult.getLocations()) {
                double lat = loc.getLatitude();
                double lng = loc.getLongitude();

                getKakaoMap(lat, lng);
            }
        }
    };

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setNumUpdates(1);
        return locationRequest;
    }

    private void getKakaoMap(double latitude, double longitude){

        MapView mapView = findViewById(R.id.map_view);
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        mapView.start(new MapLifeCycleCallback() {
            @Override
            public void onMapDestroy() {
                // 지도 API 가 정상적으로 종료될 때 호출됨
            }

            @Override
            public void onMapError(Exception error) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }
        }, new KakaoMapReadyCallback() {

            @Override
            public void onMapReady(KakaoMap kakaoMap) {
                // 인증 후 API 가 정상적으로 실행될 때 호출됨
            }
            @Override
            public LatLng getPosition() {
                // 지도 시작 시 위치 좌표를 설정
                return LatLng.from(latitude, longitude);
            }

            @Override
            public int getZoomLevel() {
                // 지도 시작 시 확대/축소 줌 레벨 설정
                return 15;
            }
        });
    }

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
        String address = et.getText().toString();
        getFindLocation(address);
    }

    private void getFindLocation(String address){
        mapRetrofitClient = MapRetrofitClient.getInstance();
        mapRetrofitInterface = MapRetrofitClient.getMapRetrofitInterface();
        mapRetrofitInterface.geocoder(BuildConfig.GOOGLE_API_KEY,address).enqueue(new Callback<GeocoderDto>() {
            @Override
            public void onResponse(Call<GeocoderDto> call, Response<GeocoderDto> response) {
                GeocoderDto result = response.body();
                Log.d("retrofit", "Data fetch success");
                double lat = result.getResults().get(0).getGeometry().getLocation().getLat();
                double lng = result.getResults().get(0).getGeometry().getLocation().getLng();

                LatLng latLng = LatLng.from(lat, lng);

//                label.moveTo(latLng);
//                label.show();
            }

            @Override
            public void onFailure(Call<GeocoderDto> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });
    }

    public void currentLocation(View view){

    }

    class notUsingFunction {
        private void getCurrentLocation(){
            checkPermission();

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            List<String> list = lm.getAllProviders();

            for(int i = 0; i<list.size(); i++){
                list.get(i);
                lm.isProviderEnabled(list.get(i));
            }

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);
            criteria.setAltitudeRequired(false);
            criteria.setSpeedRequired(true);
            criteria.setCostAllowed(true);
            criteria.setBearingRequired(false);

            String bestProvide = locationManager.getBestProvider(criteria, true);

            Location location = null;

            //매니저를 이용하여 위치정보 얻어오기
            if (locationManager.isProviderEnabled("gps")) {
                location = locationManager.getLastKnownLocation("gps");
            }else if (locationManager.isProviderEnabled("network")){
                location = locationManager.getLastKnownLocation("network");
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000L, (float) 10, (LocationListener) this);
            }

            if(location==null){
                et.setText("못찾음");
            }else{
                //위도, 경도 얻어오기
                double latitude=location.getLatitude();
                double longitude=location.getLongitude();

                System.out.println(latitude + " " + longitude);
                getKakaoMap(latitude, longitude);
                et.setText(latitude+" , "+longitude);
            }
        }
    }

}