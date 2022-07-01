package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {


    // @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creating a list of items with custom adapter
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("NewApi", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todayDate: TextView = view.findViewById(R.id.textview_currentDate)


        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
        val formattedDate = sdf.format(date)
        todayDate.text = "Today: ".plus(formattedDate)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}