package com.example.guessthecountry.Screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecountry.DTO.Country;
import com.example.guessthecountry.DTO.User;
import com.example.guessthecountry.DTO.Winner;
import com.example.guessthecountry.Database.Database;
import com.example.guessthecountry.R;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    final Database db = new Database(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final TextView userName = (TextView) findViewById(R.id.usernameEditText);
        final TextView password = (TextView) findViewById(R.id.passwordEditText);
        final Button login = (Button) findViewById(R.id.loginButton);
        final Button register = (Button) findViewById(R.id.registrationButton);
        alertDialogBuilder = new AlertDialog.Builder(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = db.getAllUsers();
                boolean log = true;
                for (User us : users) {
                    if (userName.getText().toString().equals(us.getUserName()) &&
                            password.getText().toString().equals(us.getPassword())) {
                        log = false;
                        open("Успешен вход!", log, userName.getText().toString());
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    }
                }
                if (log) {
                    open("Грешно потребителско име/парола!", log, null);
                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,
                        Register.class));
                finish();
            }
        });
    }

    public void open(String ss, final boolean log, String username) {
        alertDialogBuilder.setMessage(ss);
        alertDialogBuilder.setPositiveButton("Ок", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (log == false) {
                            Intent intent = new Intent(Login.this, MainMenu.class);
                            intent.putExtra("User", username);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
