package com.example.harsha_news_app

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Check if the message contains a data payload
        remoteMessage.data.isNotEmpty().let {
            // Handle the data payload
            val data = remoteMessage.data
            // Access the data values using keys
            val title = data["title"]
            val message = data["message"]
            // ...

            // Show a notification or handle the data payload as needed
            showNotification(title, message)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        // Implement the logic to show a notification
    }
}
