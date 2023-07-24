package com.ics342.labs

import android.Manifest.permission
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import java.util.UUID

class NotificationService: Service() {

    private val notificationManager: NotificationManagerCompat =
        NotificationManagerCompat.from(this)

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // If permission has not been granted, stop the service and return from
        // onStartCommand
        if (ContextCompat.checkSelfPermission(
                this@NotificationService,
                permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
            return START_NOT_STICKY
        }

       // Build notification

        TODO("Build and show notification")
        return START_STICKY_COMPATIBILITY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // No need to implement for lab 8
        return null
    }

    private fun createNotificationChannel() {
        TODO("Create notification channel and register with the system")
    }

    companion object {
        private const val CHANNEL_ID = "LAB_7_CHANNEL_ID"
        private const val NOTIFICATION_ID = 1234
    }
}
