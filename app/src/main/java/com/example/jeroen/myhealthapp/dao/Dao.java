package com.example.jeroen.myhealthapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.res.TypedArrayUtils;

import com.example.jeroen.myhealthapp.models.Measurement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 12-11-2015.
 */
public abstract class Dao<T extends Measurement, D> extends SQLiteOpenHelper {
    protected String TABLE;
    protected String[] COLUMNS;
    protected String[] COLUMN_TYPES;
    protected SQLiteDatabase database;

    public Dao(Context context, String TABLE, int version) {
        super(context, TABLE, null, version);
    }

    public abstract void save(T instance);
    public abstract void update(T instance);
    public abstract T deserialize(Cursor cursor);

    public void open() { database = getWritableDatabase(); }
    public void close() { database.close(); }

    public T getInstance(int id) {
        Cursor cursor = database.query(TABLE, getColumns(), "id = " + id, null, null, null, null);

        cursor.moveToFirst();
        T instance = deserialize(cursor);
        cursor.close();

        return instance;
    }

    public List<Measurement> getAll() {
        List<Measurement> instances = new ArrayList<>();
        Cursor cursor = database.query(TABLE, getColumns(), null, null, null, null, null);

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

    private String[] getColumns() {
        String[] columns = new String[1 + COLUMNS.length];
        columns[0] = "id";

        for(int i = 0; i < COLUMNS.length; i++) { columns[i + 1] = COLUMNS[i]; }

        return columns;
    }
}
