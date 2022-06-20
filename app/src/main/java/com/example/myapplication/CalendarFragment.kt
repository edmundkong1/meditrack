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
        val listViewAdapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            listItems
        )
        l.adapter = listViewAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = view.findViewById(R.id.calendar)
        dateTV = view.findViewById(R.id.textview_date)

        val Day1 = arrayOf("Medicine 1", "Reminder 2", "Medicine 2")
        val Day2 = arrayOf("Medicine 1", "Medicine 3")

        //date change listener for calendar view.
        calendarView
            .setOnDateChangeListener(
                CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
                    val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                    val l: ListView = view.findViewById(R.id.listCalendar)
                    var data = arrayOf<String>()
                    data = if (dayOfMonth % 2 == 0) {
                        Day1
                    } else {
                        Day2
                    }
                    val listViewAdapter = ArrayAdapter(
                        requireActivity(),
                        android.R.layout.simple_list_item_1,
                        data
                    )
                    l.adapter = listViewAdapter
                    // set this date in TextView for Display
                    dateTV.text = date
                })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}