package com.example.weather.data;

import com.example.weather.api.WeatherClient;
import com.example.weather.api.WeatherService;
import com.example.weather.data.datamodel.WeatherModel;

import io.reactivex.Observable;

public class DataManager {

    private static DataManager mDataManager;

    public static DataManager getInstance(){
        if (mDataManager == null)
            mDataManager = new DataManager();
        return mDataManager;
    }

    public Observable<WeatherModel> getWeather(){
        return WeatherClient.getWeather().create(WeatherService.class).getWeather();
    }

}
