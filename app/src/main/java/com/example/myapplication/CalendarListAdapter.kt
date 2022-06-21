package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

//Starter code from https://stackoverflow.com/questions/61321990/android-listview-add-text-to-the-end-of-row
//create custom adapter
class CalendarListAdapter(context: Context, data: Array<String>) : BaseAdapter() {
    private val context: Context
    private val data: Array<String>
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

   @SuppressLint("InflateParams")
   override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
       //random times for medicine
       val times = arrayOf("9:00am", "5:30pm", "8:00pm", "6:00pm", "11:00am", "9:30pm", "3:15pm")
       var view: View? = view
       if (view == null) {
           view = LayoutInflater.from(context).inflate(R.layout.calendar_list_item, null)
       }
       val name: TextView = view!!.findViewById(R.id.name)
       name.text = data[i]

       //set random time
       val time: TextView = view.findViewById(R.id.time)
       // val randomIndex: Int = Random().nextInt(times.size)
       // Made times non-random so times don't change when switching back and forth between dates
       val medTime: String = times[i % times.size]
       time.text = medTime
       return view
    }

    init {
        this.context = context
        this.data = data
    }
}