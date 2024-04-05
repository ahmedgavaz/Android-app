package com.example.guessthecountry.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecountry.R;

public class Level extends AppCompatActivity {
    private static final String TAG = "SecondScreen";
    private Button easy, medium, hard, mainMenu;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);
        Log.d(TAG, "onCreate: Started.");
        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        mainMenu = findViewById(R.id.backToMainMenu);
        Intent receiveMode = getIntent();
        String mode = receiveMode.getStringExtra("Mode");
        username = receiveMode.getStringExtra("User");
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, Region.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("User", username);
                intent.putExtra("Level", "Easy");
                startActivity(intent);
                finish();
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, Region.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("User", username);
                intent.putExtra("Level", "Medium");
                startActivity(intent);
                finish();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, Region.class);
                intent.putExtra("Mode", mode);
                intent.putExtra("User", username);
                intent.putExtra("Level", "Hard");
                startActivity(intent);
                finish();
            }
        });
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, MainMenu.class);
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
