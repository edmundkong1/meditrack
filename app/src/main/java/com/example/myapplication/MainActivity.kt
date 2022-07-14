package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.util.*

//main activity - used for all tabs

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var alarmManager: AlarmManager? = null

    //for health news api
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        //data per week for medications
        val day1 =
            arrayOf(Meds("Norvasc", "9:00am", 5, "", "", 500),
                Meds("Libitor", "11:00am", 40, "Take with Food", "", 4000),
                Meds("Warfarin", "3:00pm", 10, "", "", 1000),
                Meds("Brilinta", "5:00pm", 20, "", "", 2000))
        val day2 = arrayOf(Meds("Norvasc", "9:00am", 5, "", "", 500))
        val day3 =
            arrayOf(Meds("Norvasc","9:00am", 5, "", "", 500),
                Meds("Libitor", "11:00am", 40, "", "", 4000))
        val day4 = arrayOf(Meds("Norvasc", "9:00am", 5, "", "", 500))
        val day5 =
            arrayOf(Meds("Norvasc","9:00am", 5, "", "", 500),
                Meds("Libitor", "11:00am", 40, "", "", 4000),
                Meds("Warfarin", "3:00pm", 10, "", "", 1000))
        val day6 =
            arrayOf(Meds("Norvasc","9:00am", 5, "", "", 500),
                Meds("Brilinta", "5:00pm", 20, "", "", 2000))
        val day7 =
            arrayOf(Meds("Norvasc","9:00am", 5, "", "", 500),
                Meds("Libitor", "11:00am", 40, "", "", 4000))

        val medfos = FileOutputStream(filesDir.toString() + "medications_list.meditrack")
        val medoos = ObjectOutputStream(medfos)

        medoos.writeObject(arrayOf(day1, day2, day3, day4, day5, day6, day7))
        medoos.close()

        //data for appointments
        val appointments =
            arrayOf(Appointments("Chiropractor Appointment", "12:00pm", 2022,
                7, 13, "Dr.Good", "4162839172", "291 University Ave"),
                Appointments("Physician Appointment", "2:00pm", 2022,
                    7, 8, "Dr.Bad", "6472339172", "221 University Ave")
            )

        val appfos = FileOutputStream(filesDir.toString() + "appointments_list.meditrack")
        val appoos = ObjectOutputStream(appfos)

        appoos.writeObject(appointments)
        appoos.close()

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

        // Allow tabs to navigate to corresponding fragments based on nav_menu.xml and nav_graph.xml
        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(navView, navController)

        //added a new activity for input
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    InputActivity::class.java
                )
            )
        }

        //call alarm for notifications in this activity
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //Dummy data for calendar
        /*
        val day1 = arrayOf(arrayOf("Norvasc", "9:00am", "medication", "Dosage: 5mg"), arrayOf("Libitor", "11:00am", "medication", "Dosage: 40mg", "Take with Food"),
            arrayOf("Warfarin", "3:00pm", "medication", "Dosage: 10mg"), arrayOf("Brilinta", "5:00pm", "medication", "Dosage: 20mg"))
        val day2 = arrayOf(arrayOf("Norvasc","9:00am", "medication", "Dosage: 5mg"),
            arrayOf("Chiropractor Appointment", "12:00pm", "appointment", "Dr. Good"))
        val day3 = arrayOf(arrayOf("Norvasc","9:00am", "medication", "Dosage: 5mg"), arrayOf("Libitor", "11:00am", "medication", "Dosage: 40mg", "Take with Food"))
        val day4 = arrayOf(arrayOf("Norvasc","9:00am", "medication", "Dosage: 5mg"), arrayOf("Physician Appointment", "2:00pm", "appointment", "Dr. Bad"))
        val day5 = arrayOf(arrayOf("Norvasc","9:00am", "medication", "Dosage: 5mg"), arrayOf("Libitor", "11:00am", "medication", "Dosage: 40mg", "Take with Food"),
            arrayOf("Warfarin", "3:00pm", "medication", "Dosage: 10mg"))
        val day6 = arrayOf(arrayOf("Norvasc","9:00am", "medication", "Dosage: 5mg"), arrayOf("Brilinta", "5:00pm", "medication", "Dosage: 20mg", "Take with Food"))
        val day7 = arrayOf(arrayOf("Norvasc","9:00am", "medication", "Dosage: 5mg"), arrayOf("Libitor", "11:00am", "medication", "Dosage: 40mg", "Take with Food"))
        */
    }

    //scheduler for notifications
    // https://premsinghsodha7.medium.com/schedule-task-using-alarm-manager-android-36327548cf8e
    @SuppressLint("UnspecifiedImmutableFlag")
    fun scheduleNotification(Month: Int, Day: Int, Hour: Int, Min : Int, NotifMessage: String) {
        val intent = Intent(this@MainActivity, ReminderBroadcast::class.java)
        intent.putExtra("Message", NotifMessage)
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmStartTime = Calendar.getInstance()
        alarmStartTime.timeInMillis = System.currentTimeMillis()
        alarmStartTime[Calendar.MONTH] = Month
        alarmStartTime[Calendar.DAY_OF_MONTH] = Day
        alarmStartTime[Calendar.HOUR_OF_DAY] = Hour
        alarmStartTime[Calendar.MINUTE] = Min
        alarmStartTime[Calendar.SECOND] = 0

        //set exact time of alarm
        alarmManager!!.setExact(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime.timeInMillis, pendingIntent
        )
    }

    //https://www.geeksforgeeks.org/how-to-create-a-news-app-in-android/
    //https://blog.techchee.com/develop-a-simple-news-search-android-app-with-kotlin-newsapi/
    //get articles from api
    fun get_news_from_api() {
        val newsApiClient = NewsApiClient("36eaeaaa4688442ab4ab1f7137e53655")
        newsApiClient.getTopHeadlines(
            //health related news
            TopHeadlinesRequest.Builder()
                .q("health")
                .language("en")
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    val newsadapter = recyclerView.adapter as NewsListAdapter
                    newsadapter.refreshNewsItems(response.articles)
                }

                //if api call fails
                override fun onFailure(throwable: Throwable) {
                    throwable.message?.let { Log.w("API CALL FAILED: ", it) }
                }
            }
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