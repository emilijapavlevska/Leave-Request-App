package com.example.leaverequestapp;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle the received message
        if (remoteMessage.getNotification() != null) {
            // Handle notification message
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            // Process the notification message
        }

        if (remoteMessage.getData() != null && !remoteMessage.getData().isEmpty()) {
            // Handle data message
            // Access data payload using remoteMessage.getData()
            // Process the data message
        }
    }

    @Override
    public void onNewToken(String token) {
        // Handle token refresh event
        // Send the updated token to your server or perform any other operations
    }
}
