package com.example.guessthecountry.Screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecountry.DTO.User;
import com.example.guessthecountry.Database.Database;
import com.example.guessthecountry.R;

public class Register extends AppCompatActivity {
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    final Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        final EditText userName = findViewById(R.id.usernameEditText);
        final EditText password = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);
        Button backButton = findViewById(R.id.backButton);

        alertDialogBuilder = new AlertDialog.Builder(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (!username.isEmpty() && !pass.isEmpty()) {
                    if (username.length() < 3) {
                        openDialog("Потребителското име трябва да бъде поне 3 знака!", false);
                    } else if (pass.length() < 6) {
                        openDialog("Паролата трябва да бъде поне 6 знака!", false);
                    } else if (!db.isUserExists(username)) {
                        User newUser = new User(username, pass);
                        db.addUser(newUser);
                        openDialog("Успешна регистрация!", true);
                    } else {
                        openDialog("Потребителя вече съществува!", false);
                    }
                } else {
                    openDialog("Моля въведете потребителско име и парола!", false);
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });
    }

    private void openDialog(String message, boolean bool) {
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (bool) {
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
