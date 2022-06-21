package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

//Starter code from https://stackoverflow.com/questions/61321990/android-listview-add-text-to-the-end-of-row
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

   override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
        var view: View? = view
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.calendar_list_item, null)
        }
        val name: TextView = view!!.findViewById(R.id.name)
        name.text = data[i]
        val time: TextView = view!!.findViewById(R.id.time)
        time.text = "9:00am"
        return view
    }

    init {
        this.context = context
        this.data = data
    }
}