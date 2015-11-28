package com.example.jeroen.myhealthapp.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jeroen.myhealthapp.R;
import com.example.jeroen.myhealthapp.dao.Dao;
import com.example.jeroen.myhealthapp.dao.DaoFactory;
import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.models.Pulse;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Jeroen on 28-11-2015.
 */
public class MeasurementSendHandler implements Callback<Void> {
    private Context context;
    private View button;
    private Measurement measurement;

    public MeasurementSendHandler(Context context, View button, Measurement measurement) {
        this.context = context;
        this.button = button;
        this.measurement = measurement;
    }

    @Override
    public void onResponse(Response<Void> response, Retrofit retrofit) {
        Toast.makeText(context, context.getString(R.string.send_success), Toast.LENGTH_SHORT).show();

        ImageButton button = (ImageButton) this.button;
        button.setColorFilter(ContextCompat.getColor(context, R.color.green_500));

        measurement.setSynchronized(true);
        updateMeasurement(measurement);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(context, context.getString(R.string.send_failure), Toast.LENGTH_SHORT).show();
    }

    public void updateMeasurement(Measurement measurement) {
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
