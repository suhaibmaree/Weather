package com.example.weather.presenter;

import com.example.weather.base.BasePresenter;
import com.example.weather.data.DataManager;
import com.example.weather.presenter.viewinterface.WeatherView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter extends BasePresenter<WeatherView> {

    public void getWeather() {
        DataManager mDataManager = DataManager.getInstance();

        mDataManager.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                    mMvpView.displayNotification(model);
                });

    }

}
