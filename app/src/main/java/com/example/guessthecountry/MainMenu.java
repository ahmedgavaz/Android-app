package com.example.guessthecountry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainMenu extends AppCompatActivity {
    private Button flag,capital,help,map,results;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.d(TAG, "onCreate: Started.");
        flag = findViewById(R.id.flag);
        capital = findViewById(R.id.capital);
        help = findViewById(R.id.help);
        map=findViewById(R.id.map);
        results=findViewById(R.id.results);
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Level.class);
                intent.putExtra("Mode","Flag");
                startActivity(intent);
            }
        });
        capital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Level.class);
                intent.putExtra("Mode","Capital");
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,HelpScreen.class);
                startActivity(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Level.class);
                intent.putExtra("Mode","Map");
                startActivity(intent);
            }
        });
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Result.class);
                startActivity(intent);
            }
        });
    }
}