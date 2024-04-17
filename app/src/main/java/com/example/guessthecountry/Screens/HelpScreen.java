package com.example.guessthecountry.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecountry.R;

public class HelpScreen extends AppCompatActivity {
    private static final String TAG = "HelpScreen";
    private String username;
    private Button mainMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        Log.d(TAG, "onCreate: Started.");
        Intent receiveMode = getIntent();
        username = receiveMode.getStringExtra("User");
        mainMenu = findViewById(R.id.back);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpScreen.this, MainMenu.class);
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HelpScreen.this, MainMenu.class);
        startActivity(intent);
        finish();
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
