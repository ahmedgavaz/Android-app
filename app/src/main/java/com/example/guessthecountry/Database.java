package com.example.guessthecountry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Име на базата
    private static final String DATABASE_NAME = "uSERmANAGER";
    // Име на Таблицата
    private static final String TABLE_CONTACTS = "USERS";
    // Имена на колоните на Таблицата
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "UserName";
    private static final String KEY_PH_NO = "Password";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Повторно създаване на базата от данни
        onCreate(db);
    }
    public void onDeleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        db.delete(TABLE_CONTACTS,null,null);
        // db.close();
    }
    void addUser(User contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getUserName());
        values.put(KEY_PH_NO, contact.getPassword());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }
        User getUser(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                            KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null,
                    null);
            if (cursor != null)
                cursor.moveToFirst();
            User user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            return user;
        }
        public List<User> getAllUsers() {
            List<User> UserList = new ArrayList<User>();
            String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setID(Integer.parseInt(cursor.getString(0)));
                    user.setUserName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    UserList.add(user);
                } while (cursor.moveToNext());
            }
            return UserList;
        }
    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + KEY_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

}
