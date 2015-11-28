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
    private static int VERSION = 1;

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
        database.insert(TABLE, null, serialize(instance));
    }

    @Override
    public void update(BloodPressure instance) {
        database.update(TABLE, serialize(instance), "id = " + instance.getId(), null);
    }

    @Override
    public ContentValues serialize(BloodPressure instance) {
        ContentValues values = new ContentValues();

        values.put("over", instance.getOver());
        values.put("under", instance.getUnder());
        values.put("timestamp", instance.getTimestamp());
        values.put("synchronized", instance.isSynchronized() ? 1 : 0);

        return values;
    }

    @Override
    public BloodPressure deserialize(Cursor cursor) {
        BloodPressure pressure = new BloodPressure();

        pressure.setId((int) cursor.getLong(0));
        pressure.setOver((int) cursor.getLong(cursor.getColumnIndexOrThrow("over")));
        pressure.setUnder((int) cursor.getLong(cursor.getColumnIndexOrThrow("under")));
        pressure.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow("timestamp")));
        int sync = (int) cursor.getLong(cursor.getColumnIndexOrThrow("synchronized"));
        pressure.setSynchronized(sync == 1 ? true : false);

        return pressure;
    }
}
