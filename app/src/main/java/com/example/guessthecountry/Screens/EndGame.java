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

public class EndGame extends AppCompatActivity {
    private static final String TAG = "Result";
    private Button mainMenu;
    private TextView congrats;
    final Database db = new Database(this);
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);
        Log.d(TAG, "onCreate: Started.");
        mainMenu = findViewById(R.id.back);
        Intent receiveMode = getIntent();
        username = receiveMode.getStringExtra("User");
        String points = receiveMode.getStringExtra("Points");
        congrats = findViewById(R.id.result);
        congrats.setText("Вашият резултат е: " + points);
        List<Winner> winners = FileIO.readUsersFromFile(getApplicationContext());
        Collections.sort(winners, Comparator.comparingInt(Winner::getPoints).reversed());
        congrats.setText("Вашият резултат е: " + points + "\n");
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Winner winner = new Winner(username, Integer.parseInt(points));
                db.addWinner(winner);
                List<Winner> winners = db.getAllWinners();
                Collections.sort(winners, Comparator.comparingInt(Winner::getPoints).reversed());
                FileIO.writeUsersToFile(getApplicationContext(), winners);
                Intent intent = new Intent(EndGame.this, MainMenu.class);
                intent.putExtra("User", username);
                startActivity(intent);
                finish();
            }
        });
    }
}
