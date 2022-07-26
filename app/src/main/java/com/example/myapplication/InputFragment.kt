package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_input.*

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
        //when user wants to enter appointments
        InputAppointmentsButton.setOnClickListener{
            findNavController().navigate(R.id.action_InputFragment_to_InputAppointmentFragment)
        }
        //when user wants to enter medications
        InputMedicationsButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputMedicationsFragment)
        }
        //when user wants to enter incidents
        InputIncidentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputIncidentReportFragment)
        }
        //when user wants to enter practitioners
        InputPractitionersButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputPractitionersFragment)
        }
        //when user wants to enter conditions
        InputConditionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_InputFragment_to_InputConditionsFragment)
        }
    }
}