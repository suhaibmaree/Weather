package com.example.weather.presenter;

import android.util.Log;

import com.example.weather.base.BasePresenter;
import com.example.weather.data.DataManager;
import com.example.weather.data.datamodel.WeatherModel;
import com.example.weather.presenter.viewinterface.WeatherView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private static final String TAG = WeatherPresenter.class.getSimpleName();

    public void getWeather() {
        DataManager mDataManager = DataManager.getInstance();

        mDataManager.getWeather()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherModel model) {
                        Log.d(TAG , model.getMain().getTemp().toString());
                        mMvpView.displayText(model);
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
