package com.example.guessthecountry.Screens;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.Manifest;

import com.example.guessthecountry.Database.Database;
import com.example.guessthecountry.R;


public class Region extends AppCompatActivity implements LocationListener {
    private static final String TAG = "ThirdScreen";
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private Button africa, america, asia, europe, back, myRegion;
    private String mode, level, username;

    final Database db = new Database(this);

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
        username = receiveMode.getStringExtra("User");
        level = receiveMode.getStringExtra("Level");
        africa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("User", username);
                intent.putExtra("Level", level);
                intent.putExtra("Area", "Africa");
                startActivity(intent);
                finish();
            }
        });
        america.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("User", username);
                intent.putExtra("Level", level);
                intent.putExtra("Area", "America");
                startActivity(intent);
                finish();
            }
        });
        asia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("Level", level);
                intent.putExtra("User", username);
                intent.putExtra("Area", "Asia");
                startActivity(intent);
                finish();
            }
        });
        europe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("Level", level);
                intent.putExtra("User", username);
                intent.putExtra("Area", "Europe");
                startActivity(intent);
                finish();
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
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
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
                String countryCode = listAddresses.get(0).getCountryCode().toLowerCase();
                Intent intent = new Intent(Region.this, TestScreen.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("User", username);
                intent.putExtra("Level", level);
                String continent = db.getCountriesByFlag(countryCode).get(0).getContinent();
                intent.putExtra("Area", continent);
                startActivity(intent);
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        }
    }
}
