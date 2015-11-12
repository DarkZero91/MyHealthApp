package com.example.jeroen.myhealthapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeroen on 12-11-2015.
 */
public abstract class Dao<T> extends SQLiteOpenHelper {
    protected static String TABLE;
    protected static String TABLE_CREATE;

    public Dao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public abstract T getInstance(int id);
    public abstract void save(T instance);
    public abstract void update(T instance);

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
