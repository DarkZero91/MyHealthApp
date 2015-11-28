package com.example.jeroen.myhealthapp.network;

import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Pulse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Jeroen on 19-11-2015.
 */
public interface MyHealthApi {
    @POST("/measurement/pulse")
    Call<Void> pulseAdd(@Body Pulse pulse);

    @POST("/measurement/ecg")
    Call<Void> ecgAdd(@Body ECG ecg);

    @POST("/measurement/bloodpressure")
    Call<Void> bloodPressureAdd(@Body BloodPressure bloodPressure);

    @DELETE("/measurement/pulse/{id}")
    Call<Void> pulseRemove(@Path("id") int id);

    @DELETE("/measurement/ecg/{id}")
    Call<Void> ecgRemove(@Path("id") int id);

    @DELETE("/measurement/bloodpressure/{id}")
    Call<Void> bloodPressureRemove(@Path("id") int id);
}
