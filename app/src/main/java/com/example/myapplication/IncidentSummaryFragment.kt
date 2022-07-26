package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.*
import kotlin.collections.ArrayList


//incident summary screen

//this link might be useful for creating graphs - https://learntodroid.com/how-to-display-a-line-chart-in-your-android-app/
class IncidentSummaryFragment : Fragment() {

    lateinit var thisView: View
    lateinit var thisInstance: Bundle
    lateinit var listOfGraphs: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incident_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thisView = view
        if (savedInstanceState != null) {
            thisInstance = savedInstanceState
        }
        onResume()
    }

    override fun onResume() {
        super.onResume()
        listOfGraphs = thisView.findViewById(R.id.listChart)
        val fis = FileInputStream(activity?.filesDir.toString() + "incident_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val incidentsList: Array<Incident> =
            ois.readObject() as Array<Incident>

        val symptoms: ArrayList<IncidentSymptom> = ArrayList<IncidentSymptom>()
        //medication taken per week
        for (incident in incidentsList) {
            val date = incident.date
            val day: Int = date.substringBefore("/").toInt()
            val month: Int = date.substringAfter("/").substringBefore("/").toInt()
            val year: Int = date.substringAfter("/").substringAfter("/").toInt()
            val calendarTemp = Calendar.getInstance()
            calendarTemp.set(year, month, day, 12, 0, 0)
            val symptom = incident.symptom
            val severity = incident.severity
            if (symptoms.any { it.symptom == symptom }) {
                val symptomTemp = symptoms.first { it.symptom == symptom }
                val tempEntry = Entry(calendarTemp.timeInMillis.toFloat(), severity.toFloat())
                symptomTemp.symptomData?.add(tempEntry)
            } else {
                val symptomTemp = IncidentSymptom()
                symptomTemp.symptom = symptom
                val tempEntry = Entry(calendarTemp.timeInMillis.toFloat(), severity.toFloat())
                arrayListOf(tempEntry).also { symptomTemp.symptomData = it }
                symptoms.add(symptomTemp)
            }

        }
        ois.close()
        listOfGraphs.adapter = ChartListAdapter(requireActivity(), symptoms)
    }
}