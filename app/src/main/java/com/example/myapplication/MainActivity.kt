package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        //data per week for medications
        val wmbPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true)
        if (isFirstRun) {

            //data per week for medications
            val day1 = emptyArray<Meds>()
            val day2 = emptyArray<Meds>()
            val day3 = emptyArray<Meds>()
            val day4 = emptyArray<Meds>()
            val day5 = emptyArray<Meds>()
            val day6 = emptyArray<Meds>()
            val day7 = emptyArray<Meds>()
            //create file output stream for meds data
            val medfos = FileOutputStream(filesDir.toString() + "medications_list.meditrack")
            val medoos = ObjectOutputStream(medfos)

            val medicationList = arrayOf(day1, day2, day3, day4, day5, day6, day7)

            medoos.writeObject(medicationList)
            medoos.close()

            //data for appointments
            val appointments = emptyArray<Appointments>()

            //create file output stream for appointments data
            val appfos = FileOutputStream(filesDir.toString() + "appointments_list.meditrack")
            val appoos = ObjectOutputStream(appfos)

            appoos.writeObject(appointments)
            appoos.close()

            val NorvascDays = arrayOf(0, 1, 2, 3, 4, 5, 6)
            val LibitorDays = arrayOf(0, 2, 4, 6)
            val WarfarinDays = arrayOf(0, 4)
            val BrilintaDays = arrayOf(0, 5)

            //used for calculating when to let user know about refills
            val refillReminders: ArrayList<Refills> = arrayListOf()

            //create file output stream for Refills data
            val reffos = FileOutputStream(filesDir.toString() + "refills_list.meditrack")
            val refoos = ObjectOutputStream(reffos)

            refoos.writeObject(refillReminders.toTypedArray())
            refoos.close()

            val condReminders: ArrayList<Conditions> = arrayListOf()

            val condfos = FileOutputStream(filesDir.toString() + "conditions_list.meditrack")
            val condoos = ObjectOutputStream(condfos)

            condoos.writeObject(condReminders.toTypedArray())
            condoos.close()

            //call alarm for notifications in this activity
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            for (appointment in appointments) {
                scheduleNotification(
                    appointment.year!!,
                    appointment.month!!,
                    appointment.day!!,
                    appointment.timeHour!!,
                    appointment.timeMin!!,
                    "Reminder: " + appointment.messageAdapter()
                )
            }

            var day = 1
            for (medday in medicationList){
                for(med in medday) {
                    scheduleRepeatingNotification(
                        day,
                        med.timeHour!!,
                        med.timeMin!!,
                        "Reminder: " + med.messageAdapter()
                    )
                }
                day++
            }

            val editor = wmbPreference.edit()
            editor.putBoolean("FIRSTRUN", false)
            //editor.commit();
            editor.apply()
        }

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
    }

    //scheduler for notifications
    // https://premsinghsodha7.medium.com/schedule-task-using-alarm-manager-android-36327548cf8e
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UnspecifiedImmutableFlag")
    fun scheduleNotification(Year: Int, Month: Int, Day: Int, Hour: Int, Min : Int, NotifMessage: String) {
        val intent = Intent(this@MainActivity, ReminderBroadcast::class.java)
        intent.putExtra("Message", NotifMessage)
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
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
        val intent = Intent(this@MainActivity, ReminderBroadcast::class.java)
        intent.putExtra("Message", NotifMessage)
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
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

    //https://www.geeksforgeeks.org/how-to-create-a-news-app-in-android/
    //https://blog.techchee.com/develop-a-simple-news-search-android-app-with-kotlin-newsapi/
    //get articles from api
    fun get_news_from_api() {
        val newsApiClient = NewsApiClient("36eaeaaa4688442ab4ab1f7137e53655")
        //val newsApiClient = NewsApiClient("d4682a94c76c49d5a6cefc1358232e7f")
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