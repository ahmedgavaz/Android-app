package com.example.guessthecountry.Database;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.example.guessthecountry.DTO.Winner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static void writeUsersToFile(final Context context, final List<Winner> userList) {
        try {
            File path = context.getExternalFilesDir(null); // Get app-specific directory
            File file = new File(path, "winners.txt");
            FileOutputStream writer = new FileOutputStream(file);
            for (Winner winner : userList) {
                String userString = winner.getUsername() + "," + winner.getPoints() + "\n";
                writer.write(userString.getBytes());
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing users to file: " + e.getMessage());
        }
    }

    public static List<Winner> readUsersFromFile(Context context) {
        List<Winner> winners = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
        }
        try {
            File file = new File(context.getExternalFilesDir(null), "winners.txt");
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 2) {
                            String username = parts[0];
                            int points = Integer.parseInt(parts[1]);
                            winners.add(new Winner(username, points));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
        }
        return winners;
    }
}
