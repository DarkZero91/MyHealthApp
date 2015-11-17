package com.example.jeroen.myhealthapp.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import com.example.jeroen.myhealthapp.R;
import com.example.jeroen.myhealthapp.dao.BloodPressureDao;
import com.example.jeroen.myhealthapp.dao.Dao;
import com.example.jeroen.myhealthapp.dao.DaoFactory;
import com.example.jeroen.myhealthapp.dao.ECGDao;
import com.example.jeroen.myhealthapp.dao.PulseDao;
import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.models.Pulse;

import java.util.List;

public class MeasurementListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurement_list, container, false);
//        int type = savedInstanceState.getInt("measurement_type", DaoFactory.PULSE);
//
//        //populateDb(null);
//
//        Dao dao = DaoFactory.getDao(type, null);
//        dao.open();
//
//        List<Measurement> values = dao.getAll();
//        ArrayAdapter<Measurement> adapter = new ArrayAdapter<Measurement>(null, R.layout.measurement_list_row, R.id.text1, values);
//
//        ListView list = (ListView) container.findViewById(R.id.list);
//        list.setAdapter(adapter);
//        dao.close();

        return view;
    }

    private void populateDb(Context context) {
        // ECG Data
        Dao dao = ECGDao.getDao(context);
        dao.open();

        ECG ecg = new ECG();
        int[] data = {100, 1000, 200, 300, 150};
        for(int i = 0; i < 10; i++) { ecg.addData(data); }
        dao.save(ecg);

        dao.close();

        // Pulse data
        dao = PulseDao.getDao(context);
        dao.open();

        Pulse pulse = new Pulse();
        pulse.setHeartRate((new Double(Math.random() * (100 - 60) + 60)).intValue());
        dao.save(pulse);

        dao.close();

        // Blood pressure data
        dao = BloodPressureDao.getDao(context);
        dao.open();

        BloodPressure pressure = new BloodPressure();
        pressure.setOver((new Double(Math.random() * (190 - 110) + 110)).intValue());
        pressure.setUnder((new Double(Math.random() * (120 - 70) + 70)).intValue());
        dao.save(pressure);

        dao.close();
    }
}
