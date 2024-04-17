package com.example.guessthecountry.Screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecountry.DTO.Country;
import com.example.guessthecountry.Database.Database;
import com.example.guessthecountry.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestScreen extends AppCompatActivity {
    private TextView question;
    private static final String TAG = "TestScreen";
    final Database db = new Database(this);
    private Country correctAnswer;
    private String mode, level, area, username;
    private static int questionCount = 1, errors = 0;
    private static int points = 0;
    private static List<String> askedCountries = new ArrayList<>();
    private static List<Button> wrongChoices = new ArrayList<>();
    private static List<String> answeredCountries = new ArrayList<>();

    private void setQuestionAndOptionsCapitals(List<Country> countries, String mode, int pointsForMode) {
        do {
            Random random = new Random();
            int index = random.nextInt(countries.size());
            correctAnswer = countries.get(index);
            answeredCountries.add(correctAnswer.getName());
            if (mode.equals("Capital")) {
                question.setText(countries.get(index).getCapital() + " е столица на коя държава?");
            } else if (mode.equals("Flag")) {
                question.setText("На коя държава принадлежи този флаг?");
                ImageView img = findViewById(R.id.imageView5);
                String drawableName = countries.get(index).getFlag();
                int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                img.setImageResource(resourceId);
            } else if (mode.equals("Map")) {
                question.setText("Коя държава има следната форма?");
                ImageView img = findViewById(R.id.imageView5);
                String drawableName = countries.get(index).getMap();
                int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                img.setImageResource(resourceId);
            }
        } while (askedCountries.contains(correctAnswer.getName()));
        final int[] receivedPoints = {pointsForMode};
        askedCountries.add(correctAnswer.getName());
        List<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.first));
        buttons.add(findViewById(R.id.second));
        buttons.add(findViewById(R.id.third));
        buttons.add(findViewById(R.id.fourth));
        Collections.shuffle(buttons);

        Button correctButton = buttons.get(0);
        correctButton.setText(correctAnswer.getName());

        List<String> options = new ArrayList<>();
        for (Country country : countries) {
            if (!country.equals(correctAnswer) && !answeredCountries.contains(country.getName())) {
                options.add(country.getName());
            }
        }
        Collections.shuffle(options);
        for (int i = 1; i < 4; i++) {
            try{
                buttons.get(i).setText(options.get(i-1));
            }catch (Exception e){
                System.out.println();
            }
        }
        View.OnClickListener answerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String selectedAnswer = clickedButton.getText().toString();

                if (!selectedAnswer.equals(correctAnswer.getName()) && !wrongChoices.contains(clickedButton)) {
                    clickedButton.setBackgroundColor(Color.RED);
                    clickedButton.setTextColor(Color.RED);
                    errors++;
                    wrongChoices.add(clickedButton);
                    if (errors < 3) {
                        receivedPoints[0] = receivedPoints[0] / 2;
                    } else {
                        receivedPoints[0] = 0;
                    }
                } else if (selectedAnswer.equals(correctAnswer.getName())) {
                    clickedButton.setBackgroundColor(Color.GREEN);
                    Intent intent = new Intent(TestScreen.this, TestScreen.class);
                    intent.putExtra("Mode", mode);
                    intent.putExtra("Level", level);
                    intent.putExtra("User", username);
                    intent.putExtra("Area", area);
                    questionCount++;
                    points += receivedPoints[0];
                    errors = 0;
                    startActivity(intent);
                    finish();
                }
            }
        };
        for (Button button : buttons) {
            button.setOnClickListener(answerClickListener);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Log.d(TAG, "onCreate: Started.");
        TextView askingCount = findViewById(R.id.AskedQuestions);
        askingCount.setText(questionCount + "/8");
        TextView pointsCount = findViewById(R.id.Points);
        pointsCount.setText("Точки:" + points);
        question = findViewById(R.id.Question);
        Intent receiveMode = getIntent();
        mode = receiveMode.getStringExtra("Mode");
        level = receiveMode.getStringExtra("Level");
        username = receiveMode.getStringExtra("User");
        area = receiveMode.getStringExtra("Area");
        if (questionCount > 8) {
            questionCount = 1;
            askedCountries.clear();
            Intent intent = new Intent(TestScreen.this, EndGame.class);
            intent.putExtra("Points", String.valueOf(points));
            intent.putExtra("User", this.username);
            points = 0;
            startActivity(intent);
            finish();
            wrongChoices.clear();
        }
        if (mode.equals("Capital")) {
            if (level.equals("Easy")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Africa"), "Capital", 120);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Asia"), "Capital", 120);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Europe"), "Capital", 120);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "America"), "Capital", 120);
                }
            } else if (level.equals("Medium")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Africa"), "Capital", 240);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Asia"), "Capital", 240);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Europe"), "Capital", 240);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "America"), "Capital", 240);
                }
            } else if (level.equals("Hard")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Africa"), "Capital", 360);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Asia"), "Capital", 360);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Europe"), "Capital", 360);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "America"), "Capital", 360);
                }
            }
        } else if (mode.equals("Flag")) {
            if (level.equals("Easy")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Africa"), "Flag", 100);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Asia"), "Flag", 100);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Europe"), "Flag", 100);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "America"), "Flag", 100);
                }
            } else if (level.equals("Medium")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Africa"), "Flag", 200);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Asia"), "Flag", 200);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Europe"), "Flag", 200);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "America"), "Flag", 200);
                }
            } else if (level.equals("Hard")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Africa"), "Flag", 300);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Asia"), "Flag", 300);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Europe"), "Flag", 300);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "America"), "Flag", 300);
                }
            }
        } else if (mode.equals("Map")) {
            if (level.equals("Easy")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Africa"), "Map", 160);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Asia"), "Map", 160);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "Europe"), "Map", 160);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Easy", "America"), "Map", 160);
                }
            } else if (level.equals("Medium")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Africa"), "Map", 320);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Asia"), "Map", 320);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "Europe"), "Map", 320);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Medium", "America"), "Map", 320);
                }
            } else if (level.equals("Hard")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Africa"), "Map", 480);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Asia"), "Map", 480);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "Europe"), "Map", 480);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(db.getCountriesByDifficultyAndContinent("Hard", "America"), "Map", 480);
                }
            }
        }
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
