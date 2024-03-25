package com.example.guessthecountry.Screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecountry.DTO.User;
import com.example.guessthecountry.Database.Database;
import com.example.guessthecountry.R;

public class EditProfile  extends AppCompatActivity {
    private Button save, delete, back;
    private static final String TAG = "EditProfile";
    private EditText usernameField;
    private EditText passwordField;
    private String username;
    AlertDialog alertDialog;
    AlertDialog.Builder alertDialogBuilder;
    private final Database db = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        Log.d(TAG, "onCreate: Started.");
        Intent receiveMode = getIntent();
        username = receiveMode.getStringExtra("User");
        User mainUser = db.getUserByUsername(username);

        usernameField = findViewById(R.id.usernameEditText);
        passwordField = findViewById(R.id.passwordEditText);
        usernameField.setText(mainUser.getUserName());
        passwordField.setText(mainUser.getPassword());

        save = findViewById(R.id.saveButton);
        delete = findViewById(R.id.deleteButton);
        back = findViewById(R.id.backButton);

        alertDialogBuilder = new AlertDialog.Builder(this);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usernameText = usernameField.getText().toString().trim();
                String passwordText = passwordField.getText().toString().trim();

                if (!usernameText.isEmpty() && !passwordText.isEmpty()) {
                    if (!db.isUserExists(usernameText) || usernameText.equals(username)) {
                        db.updateUser(usernameText,passwordText);
                        openDialog("Успешна редакция!", true);
                    } else {
                        openDialog("Потребителя вече съществува!", false);
                    }
                } else {
                    openDialog("Моля въведете потребителско име и парола!", false);
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditProfile.this);
                alertDialogBuilder.setMessage("Сигурни ли сте, че искате да изтриете профила си?");
                alertDialogBuilder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(EditProfile.this, Login.class);
                        db.deleteUserByUsername(username);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialogBuilder.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, MainMenu.class);
                intent.putExtra("User", username);
                startActivity(intent);
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
                    Intent intent = new Intent(EditProfile.this, MainMenu.class);
                    db.updateUser(usernameField.getText().toString(), passwordField.getText().toString());
                    intent.putExtra("User", username);
                    startActivity(intent);
                    finish();
                }
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
