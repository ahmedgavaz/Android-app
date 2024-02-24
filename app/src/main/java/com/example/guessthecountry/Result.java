package com.example.guessthecountry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Result  extends AppCompatActivity {
    private static final String TAG = "Results";
    private Button mainMenu;
    private TextView place,name,points;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Log.d(TAG, "onCreate: Started.");
        mainMenu = findViewById(R.id.back);
        List<User> winners = DataRepository.readUsersFromFile(getApplicationContext());
        Collections.sort(winners, Comparator.comparingInt(User::getPoints).reversed());
        StringBuilder stringBuilder = new StringBuilder();
        name=findViewById(R.id.name);
        place=findViewById(R.id.place);
        points=findViewById(R.id.points);
        for (int i = 0; i < winners.size(); i++) {
            place.setText(place.getText()+ String.valueOf(i+1) + "."+ "\n");
            name.setText(name.getText()+winners.get(i).getUsername()+"\n"); // Use toString() to set text from StringBuilder
            points.setText(points.getText()+String.valueOf(winners.get(i ).getPoints())+"\n");
        }
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
