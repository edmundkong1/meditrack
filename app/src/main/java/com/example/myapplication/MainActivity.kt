package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var alarmManager: AlarmManager? = null

    //for health news api
    private lateinit var recyclerView: RecyclerView

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

        //for health news api
        recyclerView = list_news
        val recyclerViewAdapter = NewsListAdapter(null, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL, false
            )
            adapter = recyclerViewAdapter
        }

        //TODO: Dont do this is bad
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        get_news_from_api()
    }
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