package com.example.guessthecountry.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guessthecountry.Database.Database;
import com.example.guessthecountry.R;

public class MainMenu extends AppCompatActivity {
    private Button flag, capital, help, map, results, exit, edit;
    private String username;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.d(TAG, "onCreate: Started.");
        Intent receiveMode = getIntent();
        username = receiveMode.getStringExtra("User");
        flag = findViewById(R.id.flag);
        capital = findViewById(R.id.capital);
        help = findViewById(R.id.help);
        map = findViewById(R.id.map);
        results = findViewById(R.id.results);
        exit = findViewById(R.id.exit);
        edit = findViewById(R.id.editProfile);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, EditProfile.class);
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Level.class);
                intent.putExtra("Mode", "Flag");
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
        capital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Level.class);
                intent.putExtra("Mode", "Capital");
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, HelpScreen.class);
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Level.class);
                intent.putExtra("Mode", "Map");
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Result.class);
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.mediaPlayer.start();
    }
}
