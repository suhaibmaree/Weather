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

    public static final String chanelID = "chanel ID";
    public static final String chanelName = "chanel Name";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChanel();
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createChanel() {
        NotificationChannel channel = new NotificationChannel(
                chanelID,
                chanelName,
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

    public NotificationCompat.Builder getChanelNotification(String s){
        return new NotificationCompat.Builder(getApplicationContext(), chanelID)
                .setContentTitle("Alarm !")
                .setContentText("Temperature is: "+ s)
                .setSmallIcon(R.drawable.ic_notifications);
    }

}

