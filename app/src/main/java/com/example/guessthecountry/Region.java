package com.example.guessthecountry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Region extends AppCompatActivity {
    private static final String TAG = "ThirdScreen";
    private Button africa,america,asia,europe,back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.region);
        Log.d(TAG, "onCreate: Started.");
        africa = findViewById(R.id.Africa);
        america= findViewById(R.id.America);
        asia= findViewById(R.id.Asia);
        europe= findViewById(R.id.Europe);
        back= findViewById(R.id.BackToDifficulty);
        Intent receiveMode = getIntent();
        String mode = receiveMode.getStringExtra("Mode");
        String level = receiveMode.getStringExtra("Level");
        africa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this,TestScreen.class);
                intent.putExtra("Mode",mode);
                intent.putExtra("Level",level);
                intent.putExtra("Area","Africa");
                startActivity(intent);
            }
        });
        america.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this,TestScreen.class);
                intent.putExtra("Mode",mode);
                intent.putExtra("Level",level);
                intent.putExtra("Area","America");
                startActivity(intent);
            }
        });
        asia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this,TestScreen.class);
                intent.putExtra("Mode",mode);
                intent.putExtra("Level",level);
                intent.putExtra("Area","Asia");
                startActivity(intent);
            }
        });
        europe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this,TestScreen.class);
                intent.putExtra("Mode",mode);
                intent.putExtra("Level",level);
                intent.putExtra("Area","Europe");
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Region.this, Level.class);
                intent.putExtra("Mode",mode);
                startActivity(intent);
            }
        });
    }
}
