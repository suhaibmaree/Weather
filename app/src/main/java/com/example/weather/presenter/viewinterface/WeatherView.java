package com.example.weather.presenter.viewinterface;

import com.example.weather.base.MvpView;
import com.example.weather.data.datamodel.WeatherModel;

public interface WeatherView extends MvpView {

    public void displayNotification(WeatherModel model);
}
