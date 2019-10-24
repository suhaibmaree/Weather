package com.example.weather.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.weather.data.DataManager;
import com.example.weather.data.datamodel.WeatherModel;
import com.example.weather.receiver.AlarmReceiver;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RunAfterBootWeatherService extends Service {

    private static final String TAG = RunAfterBootWeatherService.class.getSimpleName();

    public RunAfterBootWeatherService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Run after boot service onCreate() method");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String message = "Run after boot service onStartCommand() method";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        Log.d(TAG, message);

        getWeather();

        return super.onStartCommand(intent, flags, startId);
    }


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
                        Log.d(TAG, model.getMain().getTemp().toString());
                        Intent intent = new Intent(RunAfterBootWeatherService.this, AlarmReceiver.class);
                        intent.putExtra("temp", model.getMain().getTemp());
                        sendBroadcast(intent);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
