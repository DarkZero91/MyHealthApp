package com.example.jeroen.myhealthapp.util;

import android.content.Context;

import com.example.jeroen.myhealthapp.dao.Dao;
import com.example.jeroen.myhealthapp.dao.DaoFactory;
import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.models.Pulse;

/**
 * Created by Jeroen on 28-11-2015.
 */
public class MeasurementHelper {
    private MeasurementHelper() {}

    public static void updateMeasurement(Context context, Measurement measurement) {
        Dao dao = null;

        if(measurement instanceof ECG) {
            dao = DaoFactory.getDao(DaoFactory.ECG, context);
        } else if(measurement instanceof BloodPressure) {
            dao = DaoFactory.getDao(DaoFactory.BLOOD_PRESSURE, context);
        } else if(measurement instanceof Pulse) {
            dao = DaoFactory.getDao(DaoFactory.PULSE, context);
        }

        dao.update(measurement);
    }
}
