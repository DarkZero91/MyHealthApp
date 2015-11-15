package com.example.jeroen.myhealthapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import com.example.jeroen.myhealthapp.models.Pulse;

import org.w3c.dom.Comment;

/**
 * Created by Jeroen on 12-11-2015.
 */
public class PulseDao extends Dao<Pulse> {
    protected static String TABLE = "pulse";
    protected static String[] COLUMNS = {"data"};
    protected static String[] COLUMN_TYPES = {"TEXT NOT NULL"};

    public PulseDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public Pulse getInstance(int id) {
        return null;
    }

    @Override
    public void save(Pulse instance) {
        
    }

    @Override
    public void update(Pulse instance) {

    }

    @Override
    public Pulse deserialize(Cursor cursor) {
        return null;
    }
}
