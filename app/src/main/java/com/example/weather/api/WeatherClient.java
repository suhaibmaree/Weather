package com.example.weather.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    public static final String baseUrl = "https://api.openweathermap.org/data/2.5";
    private static Retrofit mRtrofit;

    public static Retrofit getWeather() {

        if (mRtrofit == null) {
            mRtrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return mRtrofit;
    }

}
