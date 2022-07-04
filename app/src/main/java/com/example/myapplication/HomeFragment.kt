package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}