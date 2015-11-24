package com.example.jeroen.myhealthapp.util;

import android.view.View;
import android.widget.AdapterView;

import com.example.jeroen.myhealthapp.models.Measurement;

import java.util.List;

/**
 * Created by Jeroen on 24-11-2015.
 */
public class OnMeasurementClickListener implements AdapterView.OnClickListener {
    private List<Measurement> measurements;

    public OnMeasurementClickListener(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
    }
}
