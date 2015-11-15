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
    private static char DATA_SEPERATOR = '|';

    public PulseDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void save(Pulse instance) {
        ContentValues values = new ContentValues();

        String data = "";
        for(int val : instance.getData()) {
            data += val + DATA_SEPERATOR;
        }
        data = data.substring(0, data.length() - 1);

        values.put("data", data);
        database.insert(TABLE, null, values);
    }

    @Override
    public void update(Pulse instance) {}

    @Override
    public Pulse deserialize(Cursor cursor) {
        Pulse pulse = new Pulse();
        pulse.setId((int) cursor.getLong(0));
        String data = cursor.getString(1);

        for(String val : data.split(DATA_SEPERATOR + "")) {
            pulse.addData(Integer.parseInt(val));
        }

        return pulse;
    }
}
