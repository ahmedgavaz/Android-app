package com.example.guessthecountry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EndGame extends AppCompatActivity {
    private static final String TAG = "Result";
    private Button mainMenu;
    private TextView congrats;
    private EditText field;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);
        Log.d(TAG, "onCreate: Started.");
        mainMenu = findViewById(R.id.back);
        Intent receiveMode = getIntent();
        String points = receiveMode.getStringExtra("Points");
        congrats=findViewById(R.id.result);
        congrats.setText("Вашият резултат е: "+points);
        List<User> winners = DataRepository.readUsersFromFile(getApplicationContext());
        Collections.sort(winners, Comparator.comparingInt(User::getPoints).reversed());
        field = findViewById(R.id.usernameField);
        if (winners.size()<10){
            congrats.setText("Вашият резултат е: "+points+"\n"+"Моля запишета вашето име:");
            mainMenu.setText("Готово!");
        }else if(Integer.parseInt(points)>winners.get(9).getPoints()){
            congrats.setText("Вашият резултат е: "+points+"\n"+"Моля запишета вашето име:");
            mainMenu.setText("Готово!");
        }else{
            congrats.setText("Вашият резултат е: "+points+"\n");
            field.setVisibility(View.INVISIBLE);
        }
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(field.getText().toString(),Integer.parseInt(points));
                winners.add(user);
                Collections.sort(winners, Comparator.comparingInt(User::getPoints).reversed());
                if(winners.size()>10){
                    winners.remove(10);
                }
                DataRepository.writeUsersToFile(getApplicationContext(),winners);
                Intent intent = new Intent(EndGame.this, MainMenu.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
