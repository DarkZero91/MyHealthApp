package com.example.jeroen.myhealthapp.network;

import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Pulse;
import com.example.jeroen.myhealthapp.util.RestCallHelper;

import retrofit.Call;
import retrofit.Callback;

/**
 * Created by Jeroen on 24-11-2015.
 */
public class MyHealthService {
    public static final String BASE_URL = "http://jeroenhoekstra.no-ip.org:5000";
    private MyHealthApi api;

    public MyHealthService() {
        api = RestCallHelper.getApi(BASE_URL, MyHealthApi.class);
    }

    public void pulseAdd(Pulse pulse, Callback<Void> handler) {
        Call<Void> call = api.pulseAdd(pulse);
        call.enqueue(handler);
    }

    public void ecgAdd(ECG ecg, Callback<Void> handler) {
        Call<Void> call = api.ecgAdd(ecg);
        call.enqueue(handler);
    }

    public void bloodPressureAdd(BloodPressure bloodPressure, Callback<Void> handler) {
        Call<Void> call = api.bloodPressureAdd(bloodPressure);
        call.enqueue(handler);
    }

    public void pulseRemove(Pulse pulse, Callback<Void> handler) {
        Call<Void> call = api.pulseRemove(pulse.getId());
        call.enqueue(handler);
    }

    public void ecgRemove(ECG ecg, Callback<Void> handler) {
        Call<Void> call = api.ecgRemove(ecg.getId());
        call.enqueue(handler);
    }

    public void bloodPressureRemove(BloodPressure bloodPressure, Callback<Void> handler) {
        Call<Void> call = api.bloodPressureRemove(bloodPressure.getId());
        call.enqueue(handler);
    }
}
