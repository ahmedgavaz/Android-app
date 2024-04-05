package com.example.guessthecountry.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.guessthecountry.DTO.Country;
import com.example.guessthecountry.DTO.User;
import com.example.guessthecountry.DTO.Winner;
import com.example.guessthecountry.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "USERMANAGER";
    private static final String USERS = "USERS";
    private static final String WINNERS = "WINNERS";
    private static final String KEY_ID = "id";
    private static final String COUNTRIES = "COUNTRIES";
    private static final String KEY_CONTINENT = "continent";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_FLAG = "flag";
    private static final String KEY_MAP = "map";
    private static final String KEY_CAPITAL = "capital";

    private static final String KEY_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_POINTS = "points";
    private  Context context;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + USERS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_WINNERS_TABLE = "CREATE TABLE " + WINNERS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_POINTS + " INTEGER" + ")";
        db.execSQL(CREATE_WINNERS_TABLE);
        String CREATE_COUNTRIES_TABLE = "CREATE TABLE " + COUNTRIES +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_FLAG + " TEXT," + KEY_MAP + " TEXT," + KEY_CAPITAL + " TEXT,"
                + KEY_CONTINENT + " TEXT," + KEY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_COUNTRIES_TABLE);

        // Добавяне на допълнителна проверка, преди да се извика addCountries
        if (!isTableExists(COUNTRIES, db)) {
            addCountries(context);
        }
    }

    // Метод за проверка дали таблицата вече съществува
    private boolean isTableExists(String tableName, SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name=?", new String[] {tableName});
        boolean exists = (cursor != null) && (cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        return exists;
    }

    public void updateUser(String oldUsername, String newUsername, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newUsername);
        db.update(USERS, values, KEY_NAME + " = ?", new String[]{oldUsername});
        values.clear();
        values.put(KEY_PASSWORD, newPassword);
        db.update(USERS, values, KEY_NAME + " = ?", new String[]{newUsername});
        db.close();
    }

    public void deleteUserByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS, KEY_NAME + " = ?", new String[]{username});
        db.close();
    }
    public void addCountries(Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.config);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            String json = stringBuilder.toString();

            JSONArray countriesArray = new JSONArray(json);

            for (int i = 0; i < countriesArray.length(); i++) {
                JSONObject countryObject = countriesArray.getJSONObject(i);
                String name = countryObject.getString("name");
                String flag = countryObject.getString("flag");
                String map = countryObject.getString("map");
                String capital = countryObject.getString("capital");
                String continent = countryObject.getString("continent");
                String level = countryObject.getString("level");

                ContentValues values = new ContentValues();
                values.put(KEY_NAME, name);
                values.put(KEY_FLAG, flag);
                values.put(KEY_MAP, map);
                values.put(KEY_CAPITAL, capital);
                values.put(KEY_CONTINENT, continent);
                values.put(KEY_LEVEL, level);

                db.insert(COUNTRIES, null, values);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e("Database", "An error occurred while adding countries", e);
        } finally {
        }
    }

    public List<Country> getCountriesByFlag(String flag) {
        List<Country> countryList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(COUNTRIES, null, KEY_FLAG + "=?",
                new String[]{flag}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                country.setFlag(cursor.getString(cursor.getColumnIndexOrThrow(KEY_FLAG)));
                country.setMap(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MAP)));
                country.setCapital(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CAPITAL)));
                country.setContinent(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CONTINENT)));
                country.setLevel(cursor.getString(cursor.getColumnIndexOrThrow(KEY_LEVEL)));
                countryList.add(country);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return countryList;
    }
    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(USERS, new String[]{KEY_ID, KEY_NAME, KEY_PASSWORD},
                KEY_NAME + "=?", new String[]{username}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                user.setUserName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            }
            cursor.close();
        }

        return user;
    }
    public List<Country> getCountriesByDifficultyAndContinent(String level, String continent) {
        List<Country> countryList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(COUNTRIES, null, KEY_LEVEL + "=? AND " + KEY_CONTINENT + "=?",
                new String[]{level, continent}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                country.setFlag(cursor.getString(cursor.getColumnIndexOrThrow(KEY_FLAG)));
                country.setMap(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MAP)));
                country.setCapital(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CAPITAL)));
                country.setContinent(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CONTINENT)));
                country.setLevel(cursor.getString(cursor.getColumnIndexOrThrow(KEY_LEVEL)));
                countryList.add(country);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return countryList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        db.execSQL("DROP TABLE IF EXISTS " + WINNERS);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTRIES);
        onCreate(db);
    }
    public void onDeleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        db.execSQL("DROP TABLE IF EXISTS " + WINNERS);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTRIES);
        db.close();
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        db.insert(USERS, null, values);
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS + " WHERE " + KEY_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void addWinner(Winner winner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, winner.getUsername());
        values.put(KEY_POINTS, winner.getPoints());
        db.insert(WINNERS, null, values);
        db.close();
    }

    public List<Winner> getAllWinners() {
        List<Winner> winnersList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + WINNERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Winner winner = new Winner();
                winner.setId(Integer.parseInt(cursor.getString(0)));
                winner.setUsername(cursor.getString(1));
                winner.setPoints(Integer.parseInt(cursor.getString(2)));
                winnersList.add(winner);
            } while (cursor.moveToNext());
        }
        return winnersList;
    }
}
