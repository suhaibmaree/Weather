package com.example.weather.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weather.R;
import com.example.weather.data.datamodel.WeatherModel;
import com.example.weather.presenter.WeatherPresenter;
import com.example.weather.presenter.viewinterface.WeatherView;
import com.example.weather.receiver.BootDeviceReceiver;

import static com.example.weather.api.Constants.ANDROID_ACTION;

public class MainActivity extends AppCompatActivity implements WeatherView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView textView;
    private WeatherPresenter presenter = new WeatherPresenter();
    private BootDeviceReceiver receiver = new BootDeviceReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.weather_text);

        presenter.attachView(this);
        presenter.getWeather();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ANDROID_ACTION);
        registerReceiver(receiver, intentFilter);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(this , BootDeviceReceiver.class);
            intent.setAction(ANDROID_ACTION);
            sendBroadcast(intent);
        },1000);

    }

    @Override
    public void displayText(WeatherModel model) {

        Log.d(TAG, model.getMain().getTemp().toString());
        Toast.makeText(MainActivity.this, model.getMain().getTemp().toString(), Toast.LENGTH_LONG).show();
        textView.setText(model.getMain().getTemp() + "°");

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
