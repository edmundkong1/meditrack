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
import com.example.myapplication.databinding.FragmentCalendarBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
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

//        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
//        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initializing variables of
        // list view with their ids.
        calendarView = view.findViewById(R.id.calendar)
        dateTV = view.findViewById(R.id.textview_date)

        val Day1 = arrayOf("Medicine 1", "Reminder 2", "Medicine 2")
        val Day2 = arrayOf("Medicine 1", "Medicine 3")

        // on below line we are adding set on
        // date change listener for calendar view.
        calendarView
            .setOnDateChangeListener(
                CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
                    // In this Listener we are getting values
                    // such as year, month and day of month
                    // on below line we are creating a variable
                    // in which we are adding all the cariables in it.
                    val date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)

                    val l: ListView = view.findViewById(R.id.listCalendar)

                    var data = arrayOf<String>()
                    if (dayOfMonth % 2 == 0) {
                        data = Day1

                    } else {
                        data = Day2
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
        _binding = null
    }
}