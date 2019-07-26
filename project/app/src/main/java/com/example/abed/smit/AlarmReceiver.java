package com.example.abed.smit;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;


public class AlarmReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onReceive(Context context, Intent intent)
    {
        int notificationId = intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("todo");

        Intent mainIntent = new Intent(context, AlarmActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,mainIntent,0);

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_medicine)
                .setContentTitle("It's Time!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);


        notificationManager.notify(notificationId, builder.build());

    }
}
