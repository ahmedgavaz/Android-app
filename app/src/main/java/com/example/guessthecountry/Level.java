package com.example.guessthecountry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Level extends AppCompatActivity {
    private static final String TAG = "SecondScreen";
    private Button easy,medium,hard,mainMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);
        Log.d(TAG, "onCreate: Started.");
        easy = findViewById(R.id.easy);
        medium= findViewById(R.id.medium);
        hard= findViewById(R.id.hard);
        mainMenu= findViewById(R.id.backToMainMenu);
        Intent receiveMode = getIntent();
        String mode = receiveMode.getStringExtra("Mode");
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, Region.class);
                intent.putExtra("Mode",mode);
                intent.putExtra("Level","Easy");
                finish();
                startActivity(intent);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, Region.class);
                intent.putExtra("Mode",mode);
                intent.putExtra("Level","Medium");
                finish();
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, Region.class);
                intent.putExtra("Mode",mode);
                intent.putExtra("Level","Hard");
                finish();
                startActivity(intent);
            }
        });
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, MainMenu.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
