package com.example.guessthecountry;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestScreen extends AppCompatActivity {
    private TextView question;
    private Button first,second,third,fourth;
    private static final String TAG = "TestScreen";
    private Country  correctAnswer;
    private String mode,level,area;
    private static int questionCount = 1,errors=0;
    private static int points=0;
    private static List<String> askedCountries = new ArrayList<>();
    private static List<Button> wrongChoices = new ArrayList<>();

    private void setQuestionAndOptionsCapitals(List<Country> countries, String mode,int pointsForMode) {
        do {
            Random random = new Random();
            int index = random.nextInt(countries.size());
            correctAnswer = countries.get(index);
            if (mode.equals("Capital")) {
                question.setText(countries.get(index).getCapital() + " е столица на коя държава?");
            } else if (mode.equals("Flag")){
                question.setText("На коя държава принадлежи този флаг?");
                ImageView img = findViewById(R.id.imageView5);
                String drawableName = countries.get(index).getFlag();
                int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                img.setImageResource(resourceId);
            }else if (mode.equals("Map")){
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
            if (!country.equals(correctAnswer)) {
                options.add(country.getName());
            }
        }
        Collections.shuffle(options);
        for (int i = 1; i < 4; i++) {
            buttons.get(i).setText(options.get(i));
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
                } else if(selectedAnswer.equals(correctAnswer.getName())){
                    clickedButton.setBackgroundColor(Color.GREEN);
                    Intent intent = new Intent(TestScreen.this, TestScreen.class);
                    intent.putExtra("Mode", mode);
                    intent.putExtra("Level", level);
                    intent.putExtra("Area", area);
                    questionCount++;
                    points += receivedPoints[0];
                    errors = 0;
                    finish();
                    startActivity(intent);
                }
            }
        };
        for (Button button : buttons) {
            button.setOnClickListener(answerClickListener);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (questionCount>8){
            questionCount=1;
            askedCountries.clear();
            Intent intent = new Intent(TestScreen.this, EndGame.class);
            intent.putExtra("Points",String.valueOf(points));
            points=0;
            finish();
            startActivity(intent);
            wrongChoices.clear();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Log.d(TAG, "onCreate: Started.");
        TextView askingCount=findViewById(R.id.AskedQuestions);
        askingCount.setText(questionCount+"/8");
        TextView pointsCount=findViewById(R.id.Points);
        pointsCount.setText("Точки:"+points);
        question = findViewById(R.id.Question);
        Intent receiveMode = getIntent();
        mode = receiveMode.getStringExtra("Mode");
        level = receiveMode.getStringExtra("Level");
        area = receiveMode.getStringExtra("Area");
        if (mode.equals("Capital")) {
            if (level.equals("Easy")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAfrica(),"Capital",120);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAsia(),"Capital",120);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyEurope(),"Capital",120);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAmerica(),"Capital",120);
                }
            } else if (level.equals("Medium")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAfrica(),"Capital",240);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAsia(),"Capital",240);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumEurope(),"Capital",240);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAmerica(),"Capital",240);
                }
            } else if (level.equals("Hard")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAfrica(),"Capital",360);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAsia(),"Capital",360);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardEurope(),"Capital",360);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAmerica(),"Capital",360);
                }
            }
        }else if (mode.equals("Flag")) {
            if (level.equals("Easy")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAfrica(),"Flag",100);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAsia(),"Flag",100);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyEurope(),"Flag",100);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAmerica(),"Flag",100);
                }
            } else if (level.equals("Medium")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAfrica(),"Flag",200);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAsia(),"Flag",200);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumEurope(),"Flag",200);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAmerica(),"Flag",200);
                }
            } else if (level.equals("Hard")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAfrica(),"Flag",300);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAsia(),"Flag",300);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardEurope(),"Flag",300);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAmerica(),"Flag",300);
                }
            }
        }else if (mode.equals("Map")) {
            if (level.equals("Easy")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAfrica(),"Map",160);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAsia(),"Map",160);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyEurope(),"Map",160);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.easyAmerica(),"Map",160);
                }
            } else if (level.equals("Medium")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAfrica(),"Map",320);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAsia(),"Map",320);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumEurope(),"Map",320);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.mediumAmerica(),"Map",320);
                }
            } else if (level.equals("Hard")) {
                if (area.equals("Africa")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAfrica(),"Map",480);
                } else if (area.equals("Asia")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAsia(),"Map",480);
                } else if (area.equals("Europe")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardEurope(),"Map",480);
                } else if (area.equals("America")) {
                    setQuestionAndOptionsCapitals(DataRepository.hardAmerica(),"Map",480);
                }
            }
        }
    }
}
