package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*


//incident summary screen

//this link might be useful for creating graphs - https://learntodroid.com/how-to-display-a-line-chart-in-your-android-app/
class IncidentSummaryFragment : Fragment() {
    private var lineChart: LineChart? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //lineChart = view?.findViewById(R.id.linechart)

        //configureLineChart()
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_incident_summary, container, false)
    }

    private fun configureLineChart() {
        val desc = Description()
        desc.text = "Symptoms"
        desc.textSize = 28F
        lineChart?.description = desc
        val xAxis: XAxis = lineChart!!.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat: SimpleDateFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)
            override fun getFormattedValue(value: Float): String {
                val millis = value.toLong() * 1000L
                return mFormat.format(Date(millis))
            }
        }
    }
}