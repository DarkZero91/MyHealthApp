package com.example.jeroen.myhealthapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.util.Log;

import com.example.jeroen.myhealthapp.models.ECG;

/**
 * Created by Jeroen on 12-11-2015.
 */
public class ECGDao extends Dao<ECG, ECGDao> {
    protected static ECGDao singleton;
    protected static char DATA_SEPERATOR = ',';
    private static int VERSION = 1;

    private ECGDao(Context context) {
        super(context, "ecg", VERSION);
        TABLE = "ecg";
        COLUMNS = new String[]{"data", "timestamp", "synchronized"};
        COLUMN_TYPES = new String[]{"TEXT NOT NULL", "TEXT NOT NULL", "INTEGER NOT NULL"};
    }

    public static ECGDao getDao(Context context) {
        if(singleton == null) { singleton = new ECGDao(context); }
        return singleton;
    }

    @Override
    public void save(ECG instance) {
        database.insert(TABLE, null, serialize(instance));
    }

    @Override
    public void update(ECG instance) {
        database.update(TABLE, serialize(instance), "id = " + instance.getId(), null);
    }

    @Override
    public ContentValues serialize(ECG instance) {
        ContentValues values = new ContentValues();

        String data = "";
        for(int val : instance.getData()) {
            data += "" + val + DATA_SEPERATOR;
        }
        data = data.substring(0, data.length() - 1);

        values.put("data", data);
        values.put("timestamp", instance.getTimestamp());
        values.put("synchronized", instance.isSynchronized() ? 1 : 0);

        return values;
    }

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
        int sync = (int) cursor.getLong(cursor.getColumnIndexOrThrow("synchronized"));
        ecg.setSynchronized(sync == 1 ? true : false);

        return ecg;
    }
}
