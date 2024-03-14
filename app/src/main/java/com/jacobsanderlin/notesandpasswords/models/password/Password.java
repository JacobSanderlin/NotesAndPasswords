package com.jacobsanderlin.notesandpasswords.models.password;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

public class Password {

    private int id;

    private String name, login, password;
    private Date updateDate;

    public Password(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.updateDate = new Date();
    }

    public Password() {

    }

    @SuppressLint("Range")
    public static Password fromCursor(Cursor cursor) {
        Password password = new Password();
        password.id = cursor.getInt(cursor.getColumnIndex("id"));
        password.name = cursor.getString(cursor.getColumnIndex("id"));
        password.login = cursor.getString(cursor.getColumnIndex("id"));
        password.password = cursor.getString(cursor.getColumnIndex("id"));
        password.updateDate = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndex("id"))));
        return password;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", updateDate=" + updateDate +
                '}';
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("password", password);
        values.put("login", login);
        values.put("update_date", updateDate.getTime());
        return values;
    }
}
