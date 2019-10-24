package com.example.weather.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.weather.Service.RunAfterBootWeatherService;

import static com.example.weather.api.Constants.ANDROID_ACTION;

public class BootDeviceReceiver extends BroadcastReceiver {

    private static final String TAG = BootDeviceReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        String message = BootDeviceReceiver.class.getSimpleName() + " onReceiver" + ", action is " + action;
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        Log.d(TAG, action);

        if (Intent.ACTION_BOOT_COMPLETED.equals(action) || ANDROID_ACTION.equals(intent.getAction())) {
            startServiceByAlarm(context);
        }
    }


    /***
     * Create an repeat Alarm that will invoke the background service for each execution time.
     * The interval time can be specified by your self.
     */
    private void startServiceByAlarm(Context context) {
        PendingIntent pendingIntent;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, RunAfterBootWeatherService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pendingIntent = PendingIntent.getForegroundService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        long startTime = System.currentTimeMillis();
        long intervalTime = 60000;

        String message = "Start Service use repeat alarm";

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        Log.d(TAG, message);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, intervalTime, pendingIntent);

    }
}
