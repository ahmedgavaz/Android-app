package com.example.guessthecountry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelpScreen extends AppCompatActivity {
    private static final String TAG = "HelpScreen";
    private Button mainMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        Log.d(TAG, "onCreate: Started.");
        mainMenu = findViewById(R.id.back);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpScreen.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(HelpScreen.this, MainMenu.class);
        startActivity(intent);
    }
}
