package com.example.niksior.astro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Baza extends SQLiteOpenHelper {
    private static final String dataBaseName = "Pogoda";
    private static final String tableName = "Ulubione";

    public Baza(Context context) {
        super(context, dataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tableName +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CITY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public boolean insertData(String ulub) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("CITY", ulub);

        if ((db.insertWithOnConflict(tableName, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)) == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteData(String ulub) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db.delete(tableName, "ULUBIONE=?", new String[]{ulub}) == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + tableName, null);

        return data;
    }
}
