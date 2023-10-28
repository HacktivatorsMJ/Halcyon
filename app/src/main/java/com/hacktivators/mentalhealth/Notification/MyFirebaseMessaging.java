package com.hacktivators.mentalhealth.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hacktivators.mentalhealth.Chat.RandomChatActivity;

import org.jetbrains.annotations.NotNull;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {














                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            sendOreoNotification(remoteMessage);
                        } else {
                            sendNotification(remoteMessage);
                        }

    }



    private void sendOreoNotification(RemoteMessage remoteMessage){
        String chatid = remoteMessage.getData().get("chatid");
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        assert chatid!=null;
        int j = Integer.parseInt(chatid.replaceAll("[\\D]", ""));
        Intent intent = new Intent(this, RandomChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", chatid);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, j, intent, PendingIntent.FLAG_IMMUTABLE);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        OreoNotification oreoNotification = new OreoNotification(this);
        Notification.Builder builder = oreoNotification.getOreoNotification(title, body, pendingIntent,
                defaultSound);

        int i = 0;

        if(j>0){
            i=j;
        }

        oreoNotification.getManager().notify(i, builder.build());


    }

    private void sendNotification(RemoteMessage remoteMessage) {
        String chatid = remoteMessage.getData().get("chatid");

        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        assert chatid != null;
        int j = Integer.parseInt(chatid.replaceAll("[\\D]", ""));
        Intent intent = new Intent(this, RandomChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", chatid);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, j, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        int i = 0;

        if(j>0){
            i=j;
        }

        notificationManager.notify(i, builder.build());
    }
}
