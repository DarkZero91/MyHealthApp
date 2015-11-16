package com.example.jeroen.myhealthapp.dao;

import android.content.Context;

/**
 * Created by Jeroen on 16-11-2015.
 */
public class DaoFactory {
    public static final int ECG = 0;
    public static final int PULSE = 1;
    public static final int BLOOD_PRESSURE = 2;

    private DaoFactory() {}

    public static Dao getDao(int type, Context context) {
        switch(type) {
            case ECG:
                return ECGDao.getDao(context);
            case PULSE:
                return PulseDao.getDao(context);
            default:
                return null;
        }
    }
}
