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
        COLUMNS = new String[]{"heartRate", "timestamp", "synchronized"};
        COLUMN_TYPES = new String[]{"INTEGER NOT NULL", "TEXT NOT NULL", "INTEGER NOT NULL"};
    }

    public static PulseDao getDao(Context context) {
        if(singleton == null) { singleton = new PulseDao(context); }
        return singleton;
    }

    @Override
    public void save(Pulse instance) {
        database.insert(TABLE, null, serialize(instance));
    }

    @Override
    public void update(Pulse instance) {
        database.update(TABLE, serialize(instance), "id = " + instance.getId(), null);
    }

    @Override
    public ContentValues serialize(Pulse instance) {
        ContentValues values = new ContentValues();

        values.put("heartRate", instance.getHeartRate());
        values.put("timestamp", instance.getTimestamp());
        values.put("synchronized", instance.isSynchronized() ? 1 : 0);

        return values;
    }

    @Override
    public Pulse deserialize(Cursor cursor) {
        Pulse pulse = new Pulse();

        pulse.setId((int) cursor.getLong(0));
        pulse.setHeartRate((int) cursor.getLong(cursor.getColumnIndexOrThrow("heartRate")));
        pulse.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow("timestamp")));
        int sync = (int) cursor.getLong(cursor.getColumnIndexOrThrow("synchronized"));
        pulse.setSynchronized(sync == 1 ? true : false);

        return pulse;
    }
}
