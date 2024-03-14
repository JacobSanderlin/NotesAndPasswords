package com.jacobsanderlin.notesandpasswords.models.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jacobsanderlin.notesandpasswords.models.password.Password;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "PASSWORDS";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PASSWORDS" +
                "(id INTEGER PRIMARY KEY, name TEXT, password TEXT, login TEXT, update_date DATE);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PASSWORDS");
        onCreate(db);
    }

    public boolean insert(Password password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = password.getContentValues();
        return db.insert("PASSWORDS", null, values) != 1;
    }

    public List<Password> getPasswordList() {
        List<Password> passwords = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PASSWORDS", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Password pass = Password.fromCursor(cursor);
            passwords.add(pass);
            cursor.moveToNext();
        }

        return passwords;

    }
}
