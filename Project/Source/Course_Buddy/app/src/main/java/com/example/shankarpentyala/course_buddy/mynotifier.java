package com.example.shankarpentyala.course_buddy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by usgir on 4/28/2017.
 */

public class mynotifier extends FirebaseMessagingService {
    RemoteMessage message;
    @Override
    public void onCreate() {
        //Toast.makeText(this,"Got it",Toast.LENGTH_LONG).show();
        super.onCreate();
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        NotificationCompat.Builder build = new NotificationCompat.Builder(this);
        build.setSmallIcon(R.drawable.arrow);
        build.setContentTitle(remoteMessage.getData().get("title"));
        build.setContentText(remoteMessage.getData().get("text"));
        build.setAutoCancel(true);
        int mNotificationId = 001;
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(mNotificationId,build.build());
    }
}
