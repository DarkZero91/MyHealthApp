package com.example.jeroen.myhealthapp.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
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
import com.example.jeroen.myhealthapp.network.MyHealthApi;
import com.example.jeroen.myhealthapp.util.MeasurementListAdapter;
import com.example.jeroen.myhealthapp.util.RestCallHelper;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MeasurementListFragment extends Fragment implements Callback<Void> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurement_list, container, false);
        int type = getArguments().getInt("measurement_type", DaoFactory.ECG);

        //getActivity().requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        //populateDb(null);
        //sendMeasurement();

        Dao dao = DaoFactory.getDao(type, getActivity());
        dao.open();

        List<Measurement> values = dao.getAll();

        MeasurementListAdapter adapter = new MeasurementListAdapter(
            getActivity(), R.layout.measurement_list_row, values);

        ListView list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
        dao.close();

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

    private void sendMeasurement() {
        BloodPressure b = new BloodPressure();
        b.setId(19);
        b.setUnder(89);
        b.setOver(109);
        b.setTimestamp("23-11-2015 16:41");

        MyHealthApi api = RestCallHelper.getApi("http://jeroenhoekstra.no-ip.org:5000", MyHealthApi.class);
        Call<Void> call = api.bloodPressureAdd(b);
        call.enqueue(this);
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
