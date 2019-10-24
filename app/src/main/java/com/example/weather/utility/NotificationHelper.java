package com.example.weather.utility;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.weather.R;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANEL_ID = "chanel ID";
    private static final String CHANEL_NAME = "chanel Name";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel();
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(
                CHANEL_ID,
                CHANEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
        );

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null)
            mManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE
            );
        return mManager;
    }

    public NotificationCompat.Builder getChanelNotification(String s) {
        return new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID)
                .setContentTitle(getString(R.string.alarm))
                .setContentText(getString(R.string.tempreture) + s + " °")
                .setSmallIcon(R.drawable.ic_notifications);
    }

}

