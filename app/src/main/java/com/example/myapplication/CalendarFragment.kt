package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//calendar tab

class CalendarFragment : Fragment() {

    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView
    lateinit var thisView: View
    lateinit var thisInstance: Bundle

    //when tab is clicked
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creating a list of items with custom adapter
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val listItems : Array<Reminders> = emptyArray()
        val l: ListView = view.findViewById(R.id.listCalendar)
        l.adapter = CalendarListAdapter(requireActivity(), listItems)
        return view
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thisView = view
        if (savedInstanceState != null) {
            thisInstance = savedInstanceState
        }
        onResume()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onResume() {
        super.onResume()
        calendarView = thisView.findViewById(R.id.calendar)
        dateTV = thisView.findViewById(R.id.textview_date)

        //get medications
        val fis = FileInputStream(activity?.filesDir.toString() + "medications_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val medicationsList: Array<Array<Meds>> =
            ois.readObject() as Array<Array<Meds>>

        //medication taken per week
        val day1 = (medicationsList)[0]
        val day2 = (medicationsList)[1]
        val day3 = (medicationsList)[2]
        val day4 = (medicationsList)[3]
        val day5 = (medicationsList)[4]
        val day6 = (medicationsList)[5]
        val day7 = (medicationsList)[6]
        ois.close()

        //appointments for user
        val fis1 = FileInputStream(activity?.filesDir.toString() + "appointments_list.meditrack")
        val ois1 = ObjectInputStream(fis1)
        val appointmentsList: Array<Appointments> =
            ois1.readObject() as Array<Appointments>

        ois1.close()

        //refills for user
        val fis2 = FileInputStream(activity?.filesDir.toString() + "refills_list.meditrack")
        val ois2 = ObjectInputStream(fis2)
        val refillsList: Array<Refills> =
            ois2.readObject() as Array<Refills>
        ois2.close()

        //Initialize date
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
        val date = sdf.format(calendarView.date)

        //list of medications
        val data: ArrayList<Reminders> = arrayListOf()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = calendarView.date

        //reminders to show in calendar
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            data.addAll(day1)
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            data.addAll(day2)
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            data.addAll(day3)
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            data.addAll(day4)
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            data.addAll(day5)
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            data.addAll(day6)
        }
        else {
            data.addAll(day7)
        }

        //display appointments on specific day on calendar
        for (appointment in appointmentsList) {
            if (calendar.get(Calendar.YEAR) == appointment.year &&
                calendar.get(Calendar.MONTH) + 1 == appointment.month &&
                calendar.get(Calendar.DAY_OF_MONTH) == appointment.day) {
                data.add(appointment)
            }
        }

        //display refills
        for (refill in refillsList) {
            if (calendar.get(Calendar.YEAR) == refill.year &&
                calendar.get(Calendar.MONTH) + 1 == refill.month &&
                calendar.get(Calendar.DAY_OF_MONTH) == refill.day) {
                data.add(refill)
            }
        }

        val l: ListView = thisView.findViewById(R.id.listCalendar)
        data.sortWith(compareBy({it.timeHour}, {it.timeMin}))
        l.adapter = CalendarListAdapter(requireActivity(), data.toTypedArray())
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
                    val l: ListView = thisView.findViewById(R.id.listCalendar)
                    val data: ArrayList<Reminders> = arrayListOf()

                    calendar.get(Calendar.DAY_OF_WEEK)

                    //data for a week
                    if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        data.addAll(day1)
                    }
                    else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        data.addAll(day2)
                    }
                    else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        data.addAll(day3)
                    }
                    else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        data.addAll(day4)
                    }
                    else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        data.addAll(day5)
                    }
                    else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        data.addAll(day6)
                    }
                    else {
                        data.addAll(day7)
                    }

                    //iterate through appts
                    for (appointment in appointmentsList) {
                        if (calendar.get(Calendar.YEAR) == appointment.year &&
                            calendar.get(Calendar.MONTH) + 1 == appointment.month &&
                            calendar.get(Calendar.DAY_OF_MONTH) == appointment.day) {
                            data.add(appointment)
                        }
                    }

                    //iterate through refills
                    for (refill in refillsList) {
                        if (calendar.get(Calendar.YEAR) == refill.year &&
                            calendar.get(Calendar.MONTH) + 1 == refill.month &&
                            calendar.get(Calendar.DAY_OF_MONTH) == refill.day) {
                            data.add(refill)
                        }
                    }

                    //sort by time
                    data.sortWith(compareBy({it.timeHour}, {it.timeMin}))

                    l.adapter = CalendarListAdapter(requireActivity(), data.toTypedArray())
                    // set this date in TextView for Display
                    dateTV.text = date

                }
            )
    }

}