package com.example.jeroen.myhealthapp.handlers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jeroen.myhealthapp.R;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.util.MeasurementHelper;

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
        MeasurementHelper.updateMeasurement(context, measurement);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(context, context.getString(R.string.send_failure), Toast.LENGTH_SHORT).show();
    }
}
