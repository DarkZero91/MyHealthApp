package com.example.jeroen.myhealthapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;

import com.example.jeroen.myhealthapp.models.ECG;

/**
 * Created by Jeroen on 12-11-2015.
 */
public class ECGDao extends Dao<ECG, ECGDao> {
    protected static ECGDao singleton;
    protected static char DATA_SEPERATOR = ',';
    private static int VERSION = 2;

    private ECGDao(Context context) {
        super(context, "ecg", VERSION);
        TABLE = "ecg";
        COLUMNS = new String[]{"data", "timestamp", "synchonized"};
        COLUMN_TYPES = new String[]{"TEXT NOT NULL", "TEXT NOT NULL", "INTEGER NOT NULL"};
    }

    public static ECGDao getDao(Context context) {
        if(singleton == null) { singleton = new ECGDao(context); }
        return singleton;
    }

    @Override
    public void save(ECG instance) {
        ContentValues values = new ContentValues();

        String data = "";
        for(int val : instance.getData()) {
            data += "" + val + DATA_SEPERATOR;
        }
        data = data.substring(0, data.length() - 1);

        values.put("data", data);
        values.put("timestamp", instance.getTimestamp());
        values.put("synchronized", instance.isSynchronized());

        database.insert(TABLE, null, values);
    }

    @Override
    public void update(ECG instance) {}

    @Override
    public ECG deserialize(Cursor cursor) {
        ECG ecg = new ECG();
        ecg.setId((int) cursor.getLong(0));
        String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
        String[] vals = data.split(DATA_SEPERATOR + "");

        for(String val : vals) {
            ecg.addData(Integer.parseInt(val));
        }

        ecg.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow("timestamp")));
        ecg.setSynchronized(Boolean.getBoolean("" + cursor.getLong(cursor.getColumnIndexOrThrow("synchronized"))));

        return ecg;
    }
}
