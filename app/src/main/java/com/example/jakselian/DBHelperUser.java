package com.example.jakselian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperUser extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "User";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String STATUS = "status";
    private SQLiteDatabase MyDB;

    public static final String DBNAME = "User.db";

    public DBHelperUser(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table User(id INTEGER primary key autoincrement, username TEXT, email TEXT, password TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists User");
    }

    public Boolean addUser(String username, String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("User", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void updateUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperUser.USERNAME,user.getUsername());
        contentValues.put(DBHelperUser.EMAIL,user.getEmail());
        contentValues.put(DBHelperUser.PASSWORD,user.getPassword());
        contentValues.put(DBHelperUser.STATUS,user.getStatus());
        MyDB = this.getWritableDatabase();
        MyDB.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(user.getId())});
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkstatusadmin(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where username = ? and status = 'admin'", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkstatusvip(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where username = ? and status = 'vip'", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}
