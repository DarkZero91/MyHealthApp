package com.example.jeroen.myhealthapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 12-11-2015.
 */
public abstract class Dao<T> extends SQLiteOpenHelper {
    protected static String TABLE;
    protected static String[] COLUMNS;
    protected static String[] COLUMN_TYPES;
    protected SQLiteDatabase database;

    public Dao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public abstract T getInstance(int id);
    public abstract void save(T instance);
    public abstract void update(T instance);
    public abstract T deserialize(Cursor cursor);

    public void open() { database = getWritableDatabase(); }
    public void close() { database.close(); }

    public List<T> getAll() {
        List<T> instances = new ArrayList<T>();
        Cursor cursor = database.query(TABLE, COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            T instance = deserialize(cursor);
            instances.add(instance);
            cursor.moveToNext();
        }

        cursor.close();
        return instances;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreate = "CREATE TABLE " + TABLE + " (";
        tableCreate += "id INTEGER PRIMARY KEY AUTOINCREMENT";

        for(int i = 0; i < COLUMNS.length; i++) {
            tableCreate += ", " + COLUMNS[i] + " " + COLUMN_TYPES[i];
        }

        tableCreate += ");";
        db.execSQL(tableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
