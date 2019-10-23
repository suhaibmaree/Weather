package com.example.weather.data;

import com.example.weather.api.WeatherClient;
import com.example.weather.api.WeatherService;
import com.example.weather.data.datamodel.WeatherModel;

import io.reactivex.Observer;

public class DataManager {

    public Observer<WeatherModel> getWeather(){
        return WeatherClient.getWeather().create(WeatherService.class).getWeather();
    }

}
