package com.example.jeroen.myhealthapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.jeroen.myhealthapp.models.BloodPressure;

/**
 * Created by Jeroen on 16-11-2015.
 */
public class BloodPressureDao extends Dao<BloodPressure, BloodPressureDao> {
    private static BloodPressureDao singleton;
    private static int VERSION = 2;

    private BloodPressureDao(Context context) {
        super(context, "bloodpressure", VERSION);
        TABLE = "bloodpressure";
        COLUMNS = new String[]{"over", "under", "timestamp", "synchronized"};
        COLUMN_TYPES = new String[]{"INTEGER NOT NULL", "INTEGER NOT NULL", "TEXT NOT NULL", "INTEGER NOT NULL"};
    }

    public static BloodPressureDao getDao(Context context) {
        if(singleton == null) { singleton = new BloodPressureDao(context); }
        return singleton;
    }

    @Override
    public void save(BloodPressure instance) {
        ContentValues values = new ContentValues();

        values.put("over", instance.getOver());
        values.put("under", instance.getUnder());
        values.put("timestamp", instance.getTimestamp());
        values.put("synchronized", instance.isSynchronized());

        database.insert(TABLE, null, values);
    }

    @Override
    public void update(BloodPressure instance) {}

    @Override
    public BloodPressure deserialize(Cursor cursor) {
        BloodPressure pressure = new BloodPressure();

        pressure.setId((int) cursor.getLong(0));
        pressure.setOver((int) cursor.getLong(cursor.getColumnIndexOrThrow("over")));
        pressure.setUnder((int) cursor.getLong(cursor.getColumnIndexOrThrow("under")));
        pressure.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow("timestamp")));
        pressure.setSynchronized(Boolean.valueOf("" + cursor.getLong(cursor.getColumnIndexOrThrow("synchonized"))));

        return pressure;
    }
}
