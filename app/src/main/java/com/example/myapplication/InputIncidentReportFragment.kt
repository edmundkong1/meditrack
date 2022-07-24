package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input_incident_report.*
import java.util.*


class InputIncidentReportFragment : Fragment() {
    private var inputsList = ArrayList<Inputs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_incident_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        setRecyclerView()
        val chooseDate: EditText = view.findViewById(R.id.incidentDate)
        chooseDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
            val datePickerDialog = this.context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { datePicker, year, month, day -> chooseDate.setText(day.toString() + "/" + (month + 1) + "/" + year)}, year, month, dayOfMonth
                )
            }
            if (datePickerDialog != null) {
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
        }
    }

    private fun setRecyclerView() {
        val inputsAdapter = IncidentsAdapter(inputsList)
        rv_inputs.adapter = inputsAdapter
    }

    private fun initData() {

        inputsList.add(Inputs(
            "Enter Appointments",
            "When is your Appointment",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))

        inputsList.add(Inputs(
            "Enter Medication",
            "What is your new prescription?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))

        inputsList.add(Inputs(
            "Enter Incident Report",
            "What are your symptoms today?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))

        inputsList.add(Inputs(
            "Enter Practitioners",
            "What is your practitioner's info?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))


        inputsList.add(Inputs(
            "Enter Insurance Info",
            "What is your insurance plan?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))


        inputsList.add(Inputs(
            "Enter Conditions",
            "What are your conditions?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))

    }

}