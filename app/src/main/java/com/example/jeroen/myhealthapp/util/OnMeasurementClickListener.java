package com.example.jeroen.myhealthapp.util;

import android.view.View;
import android.widget.AdapterView;

import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.models.Pulse;
import com.example.jeroen.myhealthapp.network.MyHealthService;

import java.util.List;

/**
 * Created by Jeroen on 24-11-2015.
 */
public class OnMeasurementClickListener implements AdapterView.OnClickListener {
    private List<Measurement> measurements;
    private MyHealthService service;

    public OnMeasurementClickListener(List<Measurement> measurements, MyHealthService service) {
        this.measurements = measurements;
        this.service = service;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Measurement measurement = measurements.get(position);

        if(measurement instanceof Pulse) {
            service.pulseAdd((Pulse) measurement);
        } else if(measurement instanceof ECG) {
            service.ecgAdd((ECG) measurement);
        } else if(measurement instanceof BloodPressure) {
            service.bloodPressureAdd((BloodPressure) measurement);
        }
    }
}
