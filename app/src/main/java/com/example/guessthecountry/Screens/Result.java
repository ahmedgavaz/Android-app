package com.example.guessthecountry.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecountry.DTO.Winner;
import com.example.guessthecountry.Database.Database;
import com.example.guessthecountry.Database.FileIO;
import com.example.guessthecountry.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Result extends AppCompatActivity {
    private static final String TAG = "Results";
    private Button mainMenu;
    private TextView place, name, points;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Log.d(TAG, "onCreate: Started.");
        Intent receiveMode = getIntent();
        username = receiveMode.getStringExtra("User");
        mainMenu = findViewById(R.id.back);
        List<Winner> winners = FileIO.readUsersFromFile(getApplicationContext());
        Collections.sort(winners, Comparator.comparingInt(Winner::getPoints).reversed());
        name = findViewById(R.id.name);
        place = findViewById(R.id.place);
        points = findViewById(R.id.points);
        for (int i = 0; i < winners.size(); i++) {
            place.setText(place.getText() + String.valueOf(i + 1) + "." + "\n");
            name.setText(name.getText() + winners.get(i).getUsername() + "\n"); // Use toString() to set text from StringBuilder
            points.setText(points.getText() + String.valueOf(winners.get(i).getPoints()) + "\n");
        }
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, MainMenu.class);
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
