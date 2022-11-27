package com.example.jakselian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import java.util.ArrayList;
import java.util.List;
public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Kamus";
    public static final String ID = "id";
    public static final String KATA = "kata";
    public static final String ARTI = "arti";
    public static final String KALIMAT = "kalimat";
    private SQLiteDatabase DB;

    public DBHelper(Context context) {
        super(context, "ProjectJakselian", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Kamus(id INTEGER primary key autoincrement, kata TEXT, arti TEXT, kalimat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Kamus");
    }

    public Boolean addKamus(String kata, String arti, String kalimat) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("kata", kata);
        contentValues.put("arti", arti);
        contentValues.put("kalimat", kalimat);
        long result = DB.insert("Kamus", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void updateKamus(Kamus kamus){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KATA,kamus.getKata());
        contentValues.put(DBHelper.ARTI,kamus.getArti());
        contentValues.put(DBHelper.KALIMAT,kamus.getKalimat());
        DB = this.getWritableDatabase();
        DB.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(kamus.getId())});
    }

    public void deleteKamus(int id){
        DB = this.getWritableDatabase();
        DB.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }


    public List<Kamus> getListKata() {
        String sql = "select * from Kamus";
        DB = this.getReadableDatabase();
        List<Kamus> storeKamus = new ArrayList<>();
        Cursor cursor = DB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String kata = cursor.getString(1);
                String arti = cursor.getString(2);
                String kalimat = cursor.getString(3);
                storeKamus.add(new Kamus(id, kata, arti, kalimat));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeKamus;
    }

}
