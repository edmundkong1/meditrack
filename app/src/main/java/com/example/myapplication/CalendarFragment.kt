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

        val fis = FileInputStream(activity?.filesDir.toString() + "medications_list.meditrack")
        val ois = ObjectInputStream(fis)

        val medicationsList: Array<Array<Array<String>>> =
            ois.readObject() as Array<Array<Array<String>>>

        val day1 = (medicationsList as Array<Array<Array<String>>>)[0]
        val day2 = (medicationsList as Array<Array<Array<String>>>)[1]
        val day3 = (medicationsList as Array<Array<Array<String>>>)[2]
        val day4 = (medicationsList as Array<Array<Array<String>>>)[3]
        val day5 = (medicationsList as Array<Array<Array<String>>>)[4]
        val day6 = (medicationsList as Array<Array<Array<String>>>)[5]
        val day7 = (medicationsList as Array<Array<Array<String>>>)[6]


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
                    if (dayOfMonth == 20) {
                        val list: MutableList<Array<String>> = data.toMutableList()
                        list.add(0, arrayOf("Norvasc Refill","8:00am", "refill", "Amount: 3000mg"))
                        data = list.toTypedArray()
                    }

                    l.adapter = CalendarListAdapter(requireActivity(), data)
                    // set this date in TextView for Display
                    dateTV.text = date

                    //temp data for demo
                    if (year == 2022 && month == 5 && dayOfMonth == 29) {
                        for (day in data) {
                            (activity as MainActivity).scheduleNotification(
                                month,
                                dayOfMonth,
                                extracthour(day[1]),
                                0,
                                "Reminder: " + day[0]
                            )
                        }
                    }
                })
    }

    private fun extracthour(time: String): Int {
        var hour = 0
        for (i in time) {
            if (i == ':') {
                break
            }
            hour *= 10
            Log.w("I is:", i.toString())
            hour += i.toString().toInt()
        }

        if (time[time.length-2] == 'p') {
            hour += 12
        }
        return hour
    }
}