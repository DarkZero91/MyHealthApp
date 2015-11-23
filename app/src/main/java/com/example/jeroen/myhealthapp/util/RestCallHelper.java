package com.example.jeroen.myhealthapp.util;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Jeroen on 19-11-2015.
 */
public class RestCallHelper {
    /**
     * Returns an api object of type typeParameterClass.
     * @param baseUrl
     * @param typeParameterClass
     * @return api
     */
    public static <T> T getApi(String baseUrl, Class<T> typeParameterClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        T api = retrofit.create(typeParameterClass);
        return api;
    }
}
