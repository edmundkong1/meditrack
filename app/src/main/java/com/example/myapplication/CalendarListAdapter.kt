package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.*

//Starter code from https://stackoverflow.com/questions/61321990/android-listview-add-text-to-the-end-of-row
//create custom adapter
class CalendarListAdapter(context: Context, data: Array<Reminders>) : BaseAdapter() {
    private val context: Context
    private val data: Array<Reminders>
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
       var view: View? = view
       if (view == null) {
           view = LayoutInflater.from(context).inflate(R.layout.calendar_list_item, null)
       }
       val name: TextView = view!!.findViewById(R.id.name)
       name.text = data[i].name

       //set random time
       val time: TextView = view.findViewById(R.id.time)
       time.text = data[i].time

       //display directions as subtext
       val directions: TextView = view.findViewById(R.id.directions)
       val subTextOutput = data[i].messageAdapter()
       directions.text = subTextOutput

       //cardview for displaying extra information for calendar reminders
       val cardView: CardView = view.findViewById(R.id.base_cardview);
       val hiddenView: LinearLayout = view.findViewById(R.id.hidden_view);
       view.setOnClickListener(View.OnClickListener {
           if (hiddenView.visibility == View.VISIBLE) {
               TransitionManager.beginDelayedTransition(cardView)
               hiddenView.visibility = View.GONE
           } else {
               TransitionManager.beginDelayedTransition(cardView)
               hiddenView.visibility = View.VISIBLE
           }
       })

       cardView.setBackgroundColor(Color.parseColor(data[i].colourGetter()))

       return view
    }

    init {
        this.context = context
        this.data = data
    }
}