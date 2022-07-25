package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityInputBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*

//new Activity created because Input tab is a new screen
//TODO: need to add either the action bar at the bottom of this new screen, or a back button to go to previous screen
class InputActivity : AppCompatActivity() {
    //TODO: add all input content here, and layout info to activity_input.xml file
    private lateinit var binding: ActivityInputBinding
    private var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //scheduler for notifications
    // https://premsinghsodha7.medium.com/schedule-task-using-alarm-manager-android-36327548cf8e
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UnspecifiedImmutableFlag")
    fun scheduleNotification(Year: Int, Month: Int, Day: Int, Hour: Int, Min : Int, NotifMessage: String) {
        val intent = Intent(this@InputActivity, ReminderBroadcast::class.java)
        intent.putExtra("Message", NotifMessage)
        val pendingIntent = PendingIntent.getBroadcast(this@InputActivity, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmStartTime = Calendar.getInstance()
        alarmStartTime.timeInMillis = System.currentTimeMillis()
        alarmStartTime[Calendar.YEAR] = Year
        alarmStartTime[Calendar.MONTH] = Month - 1
        alarmStartTime[Calendar.DAY_OF_MONTH] = Day
        alarmStartTime[Calendar.HOUR_OF_DAY] = Hour
        alarmStartTime[Calendar.MINUTE] = Min
        alarmStartTime[Calendar.SECOND] = 0

        //set exact time of alarm
        alarmManager!!.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime.timeInMillis, pendingIntent
        )
    }

    fun scheduleRepeatingNotification(Dayofweek: Int, Hour: Int, Min : Int, NotifMessage: String) {
        val intent = Intent(this@InputActivity, ReminderBroadcast::class.java)
        intent.putExtra("Message", NotifMessage)
        val pendingIntent = PendingIntent.getBroadcast(this@InputActivity, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmStartTime = Calendar.getInstance()
        alarmStartTime.timeInMillis = System.currentTimeMillis()
        alarmStartTime[Calendar.DAY_OF_WEEK] = Dayofweek
        alarmStartTime[Calendar.HOUR_OF_DAY] = Hour
        alarmStartTime[Calendar.MINUTE] = Min
        alarmStartTime[Calendar.SECOND] = 0

        if (alarmStartTime.time.before(Date(System.currentTimeMillis()))){
            alarmStartTime.add(Calendar.WEEK_OF_YEAR,1)
        }

        //set exact time of alarm
        alarmManager!!.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime.timeInMillis, 1000 * 60 * 60 * 24 * 7, pendingIntent
        )
    }

}