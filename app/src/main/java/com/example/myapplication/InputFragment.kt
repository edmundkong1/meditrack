package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.android.synthetic.main.fragment_input.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creating a list of items with custom adapter
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InputAppointmentsButton.setOnClickListener{
            findNavController().navigate(R.id.action_InputFragment_to_InputAppointmentFragment)
        }
        InputMedicationsButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputMedicationsFragment)
        }
        InputIncidentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputIncidentReportFragment)
        }
        InputPractitionersButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputPractitionersFragment)
        }
        InputInsuranceButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputInsuranceFragment)
        }
        InputConditionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputConditionsFragment)
        }
    }
}