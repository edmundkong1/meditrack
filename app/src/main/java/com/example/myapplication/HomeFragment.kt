package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //creating a list of items with custom adapter
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("NewApi", "SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todayDate: TextView = view.findViewById(R.id.textview_currentDate)

        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
        val formattedDate = sdf.format(date)
        todayDate.text = "Today: ".plus(formattedDate)

        // TODO: Extract from current day in Calendar
        val fis = FileInputStream(activity?.filesDir.toString() + "medications_list.meditrack")
        val ois = ObjectInputStream(fis)

        val medicationsList: Array<Array<Reminders>> =
            ois.readObject() as Array<Array<Reminders>>

        val day1 = (medicationsList)[0]
        val day2 = (medicationsList)[1]
        val day3 = (medicationsList)[2]
        val day4 = (medicationsList)[3]
        val day5 = (medicationsList)[4]
        val day6 = (medicationsList)[5]
        val day7 = (medicationsList)[6]

        var data: Array<Reminders>
        val calendar = Calendar.getInstance()

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            data = day1
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            data = day2
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            data = day3
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            data = day4
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            data = day5
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            data = day6
        }
        else {
            data = day7
        }
        //val data = arrayOf(Meds("Norvasc", "9:00am", "Dosage: 5mg", "", ""),
        //    Appointments("Chiropractor Appointment", "12:00pm", 2022,
        //        7, 13, "Dr.Good", "4162839172", "291 University Ave"))
        val l: ListView = view.findViewById(R.id.listview_schedule)
        l.adapter = CalendarListAdapter(requireActivity(), data)

        // Would cause errors if below implementation done on empty array
        // Will need to see how the data is going to be structured to check this
        // Will no reminders be and empty array or nothing at all.
        if (l.adapter.count > 0) {
            var totalHeight = 0
            for (i in 0 until l.adapter.count) {
                val listItem = l.adapter.getView(i, null, l)
                listItem.measure(0,0)
                totalHeight += listItem.measuredHeight
            }
            val params = l.layoutParams
            params.height = totalHeight + (l.dividerHeight * l.adapter.count-1)
            l.layoutParams = params
            l.requestLayout()
        }

        //go to calendar fragment when button is clicked
        home_calendar_button.setOnClickListener {
            findNavController().navigate(R.id.action_Home_to_Calendar)
        }

        //go to input activity when this button is clicked
        input_home_button.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    InputActivity::class.java
                )
            )
        }

        (activity as MainActivity).recyclerView = list_news
        val recyclerViewAdapter = NewsListAdapter(null, activity)
        (activity as MainActivity).recyclerView.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL, false
            )
            adapter = recyclerViewAdapter
        }
        (activity as MainActivity).get_news_from_api()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}