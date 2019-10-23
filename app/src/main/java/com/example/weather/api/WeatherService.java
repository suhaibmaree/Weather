package com.example.weather.api;

import com.example.weather.data.datamodel.WeatherModel;

import io.reactivex.Observer;
import retrofit2.http.GET;

public interface WeatherService {
    @GET("/weather?id=284893&units=metric&appid=836855985b1cf9e113bdbe8a2c6a05ab")
    Observer<WeatherModel> getWeather();
}
