package com.example.myapplication

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        //removes back arrows
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.HomeFragment, R.id.CalendarFragment, R.id.IncidentSummaryFragment, R.id.PractitionersFragment, R.id.AboutMeFragment
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)

        // TODO: Don't hardcode name of tabs

        // Allow tabs to navigate to corresponding fragments based on nav_menu.xml and nav_graph.xml
        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(navView, navController)

        //TODO: INPUT tab - action should lead to input fragment
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Please Input", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //call alarm in this activity
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //time alarm will ring
        //TODO: need to change this so it shows for the time of when to take medication (depends on input data)
        scheduleNotification(2, 16);
    }
    // https://premsinghsodha7.medium.com/schedule-task-using-alarm-manager-android-36327548cf8e
    @SuppressLint("UnspecifiedImmutableFlag")
    fun scheduleNotification(Hour: Int, Min : Int) {
        val intent = Intent(this@MainActivity, ReminderBroadcast::class.java)
        intent.putExtra("ARG_REQUEST_CODE_KEY", 11)
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, 11, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val alarmStartTime = Calendar.getInstance()
        alarmStartTime.timeInMillis = System.currentTimeMillis()
        alarmStartTime[Calendar.HOUR_OF_DAY] = Hour
        alarmStartTime[Calendar.MINUTE] = Min
        //set exact time of alarm
        alarmManager!!.setExact(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime.timeInMillis, pendingIntent
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}