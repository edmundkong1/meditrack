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
class CalendarListAdapter(context: Context, data: Array<Array<String>>) : BaseAdapter() {
    private val context: Context
    private val data: Array<Array<String>>
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
       //val times = arrayOf("9:00am", "11:00am", "3:15pm", "5:30pm", "6:00pm", "8:00pm", "9:30pm")
       var view: View? = view
       if (view == null) {
           view = LayoutInflater.from(context).inflate(R.layout.calendar_list_item, null)
       }
       val name: TextView = view!!.findViewById(R.id.name)
       name.text = data[i][0]

       //set random time
       val time: TextView = view.findViewById(R.id.time)
       // val randomIndex: Int = Random().nextInt(times.size)
       // Made times non-random so times don't change when switching back and forth between dates
       // val medTime: String = times[i % times.size]
       time.text = data[i][1]
       if (data[i].size > 3) {
           val directions: TextView = view.findViewById(R.id.directions)
           var directionText = ""

           for (j in 3 until data[i].size) {
               directionText = directionText.plus(data[i][j]).plus("\n")
           }

           directions.text = directionText
       }

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

       if (data[i][2] == "appointment") {
           cardView.setBackgroundColor(Color.parseColor("#f0faa7"))
       } else if (data[i][2] == "refill") {
           cardView.setBackgroundColor(Color.parseColor("#b0d3f7"))
       } else {
           cardView.setBackgroundColor(Color.parseColor("#a7fad7"))
       }

       return view
    }

    init {
        this.context = context
        this.data = data
    }
}