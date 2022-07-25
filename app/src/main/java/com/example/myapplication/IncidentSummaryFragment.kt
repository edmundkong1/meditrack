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
        var incidentsList: Array<Incident> =
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
                var tempEntry = Entry(calendarTemp.timeInMillis.toFloat(), severity.toFloat())
                symptomTemp.symptomData?.add(tempEntry)
            } else {
                val symptomTemp = IncidentSymptom()
                symptomTemp.symptom = symptom
                var tempEntry = Entry(calendarTemp.timeInMillis.toFloat(), severity.toFloat())
                arrayListOf(tempEntry).also { symptomTemp.symptomData = it }
                symptoms.add(symptomTemp)
            }

        }
        ois.close()

//
//        //temp dates for line charts
//        val calendar1 = Calendar.getInstance()
//        calendar1.set(2022, 6, 13, 12, 0, 0)
//
//        val calendar2 = Calendar.getInstance()
//        calendar2.set(2022, 6, 15, 12, 0, 0)
//
//        val calendar3 = Calendar.getInstance()
//        calendar3.set(2022, 6, 17, 12, 0, 0)
//
//        //temp data for line charts
//        val symptom1 = Pair(
//            "Cough",
//            mutableListOf(
//                Entry(calendar1.timeInMillis.toFloat(), 3.0F),
//                Entry(calendar2.timeInMillis.toFloat(), 1.0F),
//                Entry(calendar3.timeInMillis.toFloat(), 2.0F)
//            )
//        )
//        val symptom2 = Pair(
//            "Cough",
//            mutableListOf(
//                Entry(calendar1.timeInMillis.toFloat(), 3.0F),
//                Entry(calendar2.timeInMillis.toFloat(), 1.0F),
//                Entry(calendar3.timeInMillis.toFloat(), 2.0F)
//            )
//        )
//        val symptom3 = Pair(
//            "Cough",
//            mutableListOf(
//                Entry(calendar1.timeInMillis.toFloat(), 3.0F),
//                Entry(calendar2.timeInMillis.toFloat(), 1.0F),
//                Entry(calendar3.timeInMillis.toFloat(), 2.0F)
//            )
//        )

        //val symptom2 = Pair("Knee Pain", mutableListOf(Entry(0.0F,0.0F), Entry(1.0F,1.0F), Entry(2.0F,2.0F)))
        //val symptom3 = Pair("Sneeze", mutableListOf(Entry(0.0F,0.0F), Entry(1.0F,1.0F), Entry(2.0F,2.0F), Entry(3.0F,3.0F), Entry(4.0F,4.0F), Entry(5.0F,5.0F), Entry(6.0F,6.0F), Entry(7.0F,7.0F), Entry(8.0F,8.0F), Entry(9.0F,9.0F), Entry(10.0F,10.0F), Entry(11.0F,11.0F), Entry(12.0F,12.0F), Entry(13.0F,13.0F), Entry(14.0F,14.0F), Entry(15.0F,15.0F)))

        listOfGraphs.adapter = ChartListAdapter(requireActivity(), symptoms)
    }
}