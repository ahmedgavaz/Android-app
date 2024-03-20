package com.example.guessthecountry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                    if (!db.isUserExists(username)) {
                        User newUser = new User(username, pass);
                        db.addUser(newUser);
                        Toast.makeText(Register.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Register.this,
                                Login.class));
                    } else {
                        showAlert("Username already exists !");
                    }
                } else {
                    showAlert("Please enter username and password !");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Register.this,
                        Login.class));
            }
        });
    }

    private void showAlert(String message) {
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
