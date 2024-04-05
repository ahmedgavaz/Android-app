package com.example.guessthecountry.Database;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.example.guessthecountry.DTO.Winner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static void writeUsersToFile(final Context context, final List<Winner> userList) {
        try {
            File path = context.getExternalFilesDir(null); // Get app-specific directory
            File file = new File(path, "winners.json");
            JSONArray jsonArray = new JSONArray();
            for (Winner winner : userList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", winner.getUsername());
                jsonObject.put("points", winner.getPoints());
                jsonArray.put(jsonObject);
            }
            FileWriter writer = new FileWriter(file);
            writer.write(jsonArray.toString());
            writer.close();
        } catch (JSONException | IOException e) {
            System.err.println("Error writing users to JSON file: " + e.getMessage());
        }
    }

    public static List<Winner> readUsersFromFile(Context context) {
        List<Winner> winners = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Handle permission denied
        }
        try {
            File file = new File(context.getExternalFilesDir(null), "winners.json");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                reader.close();
                JSONArray jsonArray = new JSONArray(json.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String username = jsonObject.getString("username");
                    int points = jsonObject.getInt("points");
                    winners.add(new Winner(username, points));
                }
            }
        } catch (JSONException | IOException e) {
            System.err.println("Error reading users from JSON file: " + e.getMessage());
        }
        return winners;
    }
}
