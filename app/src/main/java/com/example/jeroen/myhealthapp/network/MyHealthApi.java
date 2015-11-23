package com.example.jeroen.myhealthapp.network;

import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Pulse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Jeroen on 19-11-2015.
 */
public interface MyHealthApi {
    @POST("/measurement/pulse")
    Call<Void> pulseAdd(@Body Pulse pulse);

    @FormUrlEncoded @POST("/measurement/ecg")
    Call<Void> ecgAdd(@Field("data") ECG ecg);

    @FormUrlEncoded @POST("/measurement/bloodpressure")
    Call<Void> bloodPressureAdd(@Field("data") BloodPressure bloodPressure);
}
