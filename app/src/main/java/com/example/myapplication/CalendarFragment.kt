package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCalendarBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val listItems = arrayOf("Item 1", "Item 2")
        val l: ListView = view.findViewById(R.id.listCalendar)
        val listViewAdapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            listItems
        )
        l.adapter = listViewAdapter
        return view

//        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
//        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}