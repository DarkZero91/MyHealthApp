package com.example.jeroen.myhealthapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.jeroen.myhealthapp.models.Pulse;

/**
 * Created by Jeroen on 16-11-2015.
 */
public class PulseDao extends Dao<Pulse, PulseDao> {
    private static PulseDao singleton;
    private static int VERSION = 1;

    private PulseDao(Context context) {
        super(context, "pulse", VERSION);
        TABLE = "pulse";
        COLUMNS = new String[]{"heartRate", "timestamp"};
        COLUMN_TYPES = new String[]{"INTEGER NOT NULL", "TEXT NOT NULL"};
    }

    public static PulseDao getDao(Context context) {
        if(singleton == null) { singleton = new PulseDao(context); }
        return singleton;
    }

    @Override
    public void save(Pulse instance) {
        ContentValues values = new ContentValues();

        values.put("heartRate", instance.getHeartRate());
        values.put("timestamp", instance.getTimestamp());

        database.insert(TABLE, null, values);
    }

    @Override
    public void update(Pulse instance) {}

    @Override
    public Pulse deserialize(Cursor cursor) {
        Pulse pulse = new Pulse();

        pulse.setId((int) cursor.getLong(0));
        pulse.setHeartRate((int) cursor.getLong(cursor.getColumnIndexOrThrow("heartRate")));
        pulse.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow("timestamp")));

        return pulse;
    }
}
