package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

//https://premsinghsodha7.medium.com/schedule-task-using-alarm-manager-android-36327548cf8e
class ReminderBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //temp data for notification
        showNotification(
            context,
            "Reminder 1",
            "This is the example reminder 1 from alarm manager"
        )
    }

    //method for displaying the notification
    private fun showNotification(context: Context, title: String, message: String) {
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "1",
                "CH_ID",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Example Alarm"
            mNotificationManager.createNotificationChannel(channel)
        }
        val mBuilder = NotificationCompat.Builder(context, "1")
            // notification icon
            .setSmallIcon(R.mipmap.ic_launcher)
            // title for notification
            .setContentTitle(title)
            // message for notification
            .setContentText(message)
            // clear notification after click
            .setAutoCancel(true)
        //intent used for performing action on screen
        val intent = Intent(context, MainActivity::class.java)
        val pi = PendingIntent.getActivity(context, 0, intent, 0)
        mBuilder.setContentIntent(pi)
        //show notification
        mNotificationManager.notify(1001, mBuilder.build())
    }
}