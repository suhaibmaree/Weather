package com.example.weather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.R;
import com.example.weather.Service.MyServeice;
import com.example.weather.data.datamodel.WeatherModel;
import com.example.weather.presenter.WeatherPresenter;
import com.example.weather.presenter.viewinterface.WeatherView;

public class MainActivity extends AppCompatActivity implements WeatherView {

    private static final String TAG = "MainActivity";
    private TextView textView;
    private WeatherPresenter presenter = new WeatherPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.weather_text);


        presenter.attachView(this);
        presenter.getWeather();
        //startService(new Intent(this , MyServeice.class));

    }

    @Override
    public void displayText(WeatherModel model) {

        Log.d(TAG , model.getMain().getTemp().toString());
        Toast.makeText(this, model.getMain().getTemp().toString() , Toast.LENGTH_LONG).show();
        textView.setText(String.valueOf(model.getMain().getTemp()) + "°");

    }


    @Override
    protected void onStop() {
        presenter.detachView();
        super.onStop();
    }
}
