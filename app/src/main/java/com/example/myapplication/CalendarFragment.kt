package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

//TODO: NotificationManager/AlarmManager + Hour+minutes + Sort by time

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CalendarFragment : Fragment() {

    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creating a list of items with custom adapter
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val listItems = arrayOf("Item 1", "Item 2")
        val l: ListView = view.findViewById(R.id.listCalendar)
        l.adapter = CalendarListAdapter(requireActivity(), listItems)
        return view
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = view.findViewById(R.id.calendar)
        dateTV = view.findViewById(R.id.textview_date)

        //temp mockup data
        val day1 = arrayOf("Norvasc", "Libitor", "Warfarin", "Brilinta", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9")
        val day2 = arrayOf("Norvasc")
        val day3 = arrayOf("Norvasc", "Libitor")
        val day4 = arrayOf("Norvasc")
        val day5 = arrayOf("Norvasc", "Libitor", "Warfarin")
        val day6 = arrayOf("Norvasc", "Brilinta")
        val day7 = arrayOf("Norvasc", "Libitor")

        //Initialize date
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
        val date = sdf.format(calendarView.date)

        //list of medications
        val l: ListView = view.findViewById(R.id.listCalendar)
        l.adapter = CalendarListAdapter(requireActivity(), day2)
        // set this date in TextView for Display
        dateTV.text = date

        //date change listener for calendar view
        calendarView
            .setOnDateChangeListener(
                CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    calendarView.date = calendar.timeInMillis
                    val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
                    val date = sdf.format(calendarView.date)

                    //show list of medicines
                    val l: ListView = view.findViewById(R.id.listCalendar)
                    var data = arrayOf<String>()

                    //mockup data for one week
                    if (dayOfMonth % 7 == 0) {
                        data = day1
                    }
                    else if (dayOfMonth % 7 == 1) {
                        data = day2
                    }
                    else if (dayOfMonth % 7 == 2) {
                        data = day3
                    }
                    else if (dayOfMonth % 7 == 3) {
                        data = day4
                    }
                    else if (dayOfMonth % 7 == 4) {
                        data = day5
                    }
                    else if (dayOfMonth % 7 == 5) {
                        data = day6
                    }
                    else if (dayOfMonth % 7 == 6) {
                        data = day7
                    }

                    l.adapter = CalendarListAdapter(requireActivity(), data)
                    // set this date in TextView for Display
                    dateTV.text = date
                })
    }
}