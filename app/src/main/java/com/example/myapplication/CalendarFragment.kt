package com.example.myapplication

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
//TODO: BUG: List is cut off by one element

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
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val listItems = arrayOf("Item 1", "Item 2")
        val l: ListView = view.findViewById(R.id.listCalendar)
        //TODO: Custom adapter for subtext for medicine
        val listViewAdapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            listItems
        )
        //l.adapter = listViewAdapter
        l.adapter = CalendarListAdapter(requireActivity(), listItems)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = view.findViewById(R.id.calendar)
        dateTV = view.findViewById(R.id.textview_date)

        val Day1 = arrayOf("Norvasc", "Libitor", "Warfarin", "Brilinta", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9")
        val Day2 = arrayOf("Norvasc")
        val Day3 = arrayOf("Norvasc", "Libitor")
        val Day4 = arrayOf("Norvasc")
        val Day5 = arrayOf("Norvasc", "Libitor", "Warfarin")
        val Day6 = arrayOf("Norvasc", "Brilinta")
        val Day7 = arrayOf("Norvasc", "Libitor")

        //Initialize date
        //val sdf = SimpleDateFormat("dd-MM-yyyy")
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
        val date = sdf.format(calendarView.date)
        //val date = calendarView.date
        val l: ListView = view.findViewById(R.id.listCalendar)
        var data = arrayOf<String>()
        /*
        data = if (calendarView.date  % 2 == 0) {
            Day1
        } else {
            Day2
        }
         */

        data = Day1
        val listViewAdapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            data
        )
        //l.adapter = listViewAdapter
        l.adapter = CalendarListAdapter(requireActivity(), data)
        // set this date in TextView for Display
        dateTV.text = date

        //date change listener for calendar view.
        calendarView
            .setOnDateChangeListener(
                CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    calendarView.date = calendar.getTimeInMillis()
                    val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
                    val date = sdf.format(calendarView.date)
                    //val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                    val l: ListView = view.findViewById(R.id.listCalendar)
                    var data = arrayOf<String>()
                    if (dayOfMonth % 7 == 0) {
                        data = Day1
                    }
                    else if (dayOfMonth % 7 == 1){
                        data = Day2
                    }
                    else if (dayOfMonth % 7 == 2){
                        data = Day3
                    }
                    else if (dayOfMonth % 7 == 3){
                        data = Day4
                    }
                    else if (dayOfMonth % 7 == 4){
                        data = Day5
                    }
                    else if (dayOfMonth % 7 == 5){
                        data = Day6
                    }
                    else if (dayOfMonth % 7 == 6){
                        data = Day7
                    }
                    val listViewAdapter = ArrayAdapter(
                        requireActivity(),
                        android.R.layout.simple_list_item_1,
                        data
                    )
                    //l.adapter = listViewAdapter
                    l.adapter = CalendarListAdapter(requireActivity(), data)
                    // set this date in TextView for Display
                    dateTV.text = date
                })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}