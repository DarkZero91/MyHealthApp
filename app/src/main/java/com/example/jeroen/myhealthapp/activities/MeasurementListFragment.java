package com.example.jeroen.myhealthapp.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

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
import com.example.jeroen.myhealthapp.network.MyHealthService;
import com.example.jeroen.myhealthapp.util.MeasurementListAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MeasurementListFragment extends Fragment implements Callback<Void> {
    List<Measurement> mMeasurements;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurement_list, container, false);
        int type = getArguments().getInt("measurement_type", DaoFactory.ECG);

        Dao dao = DaoFactory.getDao(type, getActivity());
        dao.open();

        mMeasurements = dao.getAll();
        MyHealthService service = new MyHealthService(this);

        MeasurementListAdapter adapter = new MeasurementListAdapter(
            getActivity(), R.layout.measurement_list_row, mMeasurements, service);

        ListView list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
        dao.close();

        populateDb(20);

        return view;
    }

    private void populateDb(int amount) {
        if(mMeasurements.size() > 0) return;
        Context context = getActivity();

        // ECG Data
        Dao dao = ECGDao.getDao(context);
        dao.open();

        ECG ecg = new ECG();
        int[] data = {0,0,0,2,3,1,0,0,0,-4,25,-8,0,0,0,0,3,4,4,2,0,0,0,0,0,0};
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

        if(--amount > 0) { populateDb(amount); }
    }

    @Override
    public void onResponse(Response<Void> response, Retrofit retrofit) {
        Toast.makeText(getActivity(),getString(R.string.send_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(), getString(R.string.send_failure), Toast.LENGTH_SHORT).show();
    }
}
