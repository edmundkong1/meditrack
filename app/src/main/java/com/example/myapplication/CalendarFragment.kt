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

        //Initialize date
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
        val date = sdf.format(calendarView.date)

        //list of medications
        val data: ArrayList<Reminders> = arrayListOf()
        val calendar = Calendar.getInstance()

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

        val l: ListView = view.findViewById(R.id.listCalendar)
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
                    val l: ListView = view.findViewById(R.id.listCalendar)
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
                    for (appointment in appointmentsList) {
                        if (calendar.get(Calendar.YEAR) == appointment.year &&
                            calendar.get(Calendar.MONTH) + 1 == appointment.month &&
                            calendar.get(Calendar.DAY_OF_MONTH) == appointment.day) {
                            data.add(appointment)
                        }
                    }

                    l.adapter = CalendarListAdapter(requireActivity(), data.toTypedArray())
                    // set this date in TextView for Display
                    dateTV.text = date

                    //notifications
                    //temp data for demo
                    //if (year == 2022 && month == 5 && dayOfMonth == 29)
                    //}
                })
    }
}