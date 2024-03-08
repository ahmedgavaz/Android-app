package com.example.guessthecountry;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.Manifest;
import android.content.pm.PackageManager;


public class Region extends AppCompatActivity implements LocationListener {
    private static final String TAG = "ThirdScreen";
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private Button africa, america, asia, europe, back, myRegion;
    private String mode;
    private String level;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.region);
        Log.d(TAG, "onCreate: Started.");
        africa = findViewById(R.id.Africa);
        america = findViewById(R.id.America);
        asia = findViewById(R.id.Asia);
        europe = findViewById(R.id.Europe);
        myRegion = findViewById(R.id.MyRegion);
        back = findViewById(R.id.BackToDifficulty);
        Intent receiveMode = getIntent();
        mode = receiveMode.getStringExtra("Mode");
        level = receiveMode.getStringExtra("Level");
        africa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("Level", level);
                intent.putExtra("Area", "Africa");
                startActivity(intent);
            }
        });
        america.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("Level", level);
                intent.putExtra("Area", "America");
                startActivity(intent);
            }
        });
        asia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("Level", level);
                intent.putExtra("Area", "Asia");
                startActivity(intent);
            }
        });
        europe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("Level", level);
                intent.putExtra("Area", "Europe");
                startActivity(intent);
            }
        });
        myRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ContextCompat.checkSelfPermission(Region.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(Region.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Region.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, Region.this, null);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, Level.class);
                intent.putExtra("Mode", mode);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
                String countryCode = listAddresses.get(0).getCountryCode();
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("Level", level);
                String continent="";
                if (getCountryFromCode(countryCode,DataRepository.easyAfrica()) ||
                        getCountryFromCode(countryCode,DataRepository.mediumAfrica()) ||
                        getCountryFromCode(countryCode,DataRepository.hardAfrica())){
                    continent="Africa";
                }else if (getCountryFromCode(countryCode,DataRepository.easyAmerica()) ||
                        getCountryFromCode(countryCode,DataRepository.mediumAmerica()) ||
                        getCountryFromCode(countryCode,DataRepository.hardAmerica())){
                    continent="America";
                }else if (getCountryFromCode(countryCode,DataRepository.easyAsia()) ||
                        getCountryFromCode(countryCode,DataRepository.mediumAsia()) ||
                        getCountryFromCode(countryCode,DataRepository.hardAsia())){
                    continent="Asia";
                }else if (getCountryFromCode(countryCode,DataRepository.easyEurope()) ||
                        getCountryFromCode(countryCode,DataRepository.mediumEurope()) ||
                        getCountryFromCode(countryCode,DataRepository.hardEurope())){
                    continent="Europe";
                }
                intent.putExtra("Area", continent);
                startActivity(intent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean getCountryFromCode(String countryCode, List<Country> countries) {
        for (Country country : countries) {
            if (country.getFlag().equalsIgnoreCase(countryCode)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(Region.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(Region.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, Region.this, null);
            } else {
                Intent intent = new Intent(Region.this, MainMenu.class);
                startActivity(intent);
            }
        }
    }
}
