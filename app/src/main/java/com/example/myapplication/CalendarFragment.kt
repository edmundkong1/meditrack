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
        val listItems = arrayOf(arrayOf("Item 1","1:00pm"), arrayOf("Item 2","1:00pm"))
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
        val day1 = arrayOf(arrayOf("Norvasc","9:00am"), arrayOf("Libitor", "11:00am"),
            arrayOf("Warfarin", "3:00pm"), arrayOf("Brilinta", "5:00pm"))
        val day2 = arrayOf(arrayOf("Norvasc","9:00am"),
            arrayOf("Chiropractor Appointment", "12:00pm"))
        val day3 = arrayOf(arrayOf("Norvasc","9:00am"), arrayOf("Libitor", "11:00am"))
        val day4 = arrayOf(arrayOf("Norvasc","9:00am"), arrayOf("Physician Appointment", "2:00pm"))
        val day5 = arrayOf(arrayOf("Norvasc","9:00am"), arrayOf("Libitor", "11:00am"),
            arrayOf("Warfarin", "3:00pm"))
        val day6 = arrayOf(arrayOf("Norvasc","9:00am"), arrayOf("Brilinta", "5:00pm"))
        val day7 = arrayOf(arrayOf("Norvasc","9:00am"), arrayOf("Libitor", "11:00am"))

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
                    var data : Array<Array<String>>

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
                    else {
                        data = day7
                    }

                    l.adapter = CalendarListAdapter(requireActivity(), data)
                    // set this date in TextView for Display
                    dateTV.text = date

                    //Set notification for date/time
                    //MainActivity.scheduleNotification
                    //for (i in data){
                    //    scheduleNotification(1, 2)
                    //}
                })
    }
}