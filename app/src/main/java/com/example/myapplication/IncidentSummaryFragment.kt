package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import java.util.*


//incident summary screen

//this link might be useful for creating graphs - https://learntodroid.com/how-to-display-a-line-chart-in-your-android-app/
class IncidentSummaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incident_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val l: ListView = view.findViewById(R.id.listChart)

        val calendar1 = Calendar.getInstance()
        calendar1.set(2022,6, 13, 12, 0, 0)

        val calendar2 = Calendar.getInstance()
        calendar2.set(2022,6,15, 12 ,0 ,0)

        val calendar3 = Calendar.getInstance()
        calendar3.set(2022,6,17, 12, 0, 0)

        val symptom1 = Pair("Cough", mutableListOf(Entry(calendar1.timeInMillis.toFloat(),3.0F), Entry(calendar2.timeInMillis.toFloat(),1.0F), Entry(calendar3.timeInMillis.toFloat(),2.0F)))
        val symptom2 = Pair("Cough", mutableListOf(Entry(calendar1.timeInMillis.toFloat(),3.0F), Entry(calendar2.timeInMillis.toFloat(),1.0F), Entry(calendar3.timeInMillis.toFloat(),2.0F)))
        val symptom3 = Pair("Cough", mutableListOf(Entry(calendar1.timeInMillis.toFloat(),3.0F), Entry(calendar2.timeInMillis.toFloat(),1.0F), Entry(calendar3.timeInMillis.toFloat(),2.0F)))

        //val symptom2 = Pair("Knee Pain", mutableListOf(Entry(0.0F,0.0F), Entry(1.0F,1.0F), Entry(2.0F,2.0F)))
        //val symptom3 = Pair("Sneeze", mutableListOf(Entry(0.0F,0.0F), Entry(1.0F,1.0F), Entry(2.0F,2.0F), Entry(3.0F,3.0F), Entry(4.0F,4.0F), Entry(5.0F,5.0F), Entry(6.0F,6.0F), Entry(7.0F,7.0F), Entry(8.0F,8.0F), Entry(9.0F,9.0F), Entry(10.0F,10.0F), Entry(11.0F,11.0F), Entry(12.0F,12.0F), Entry(13.0F,13.0F), Entry(14.0F,14.0F), Entry(15.0F,15.0F)))

        l.adapter = ChartListAdapter(requireActivity(), arrayOf(symptom1, symptom2, symptom3))
        super.onViewCreated(view, savedInstanceState)
    }
}