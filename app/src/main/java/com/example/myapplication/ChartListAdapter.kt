package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.text.SimpleDateFormat
import java.util.*

class ChartListAdapter(context: Context, data: Array<Pair<String, MutableList<Entry>>>) : BaseAdapter(){
    private val context: Context
    private val data: Array<Pair<String, MutableList<Entry>>>
    private var lineChart: LineChart? = null
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
        //random times for medicine
        var view: View? = view
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.chart_list_item, null)
        }

        val lineChartTitle : TextView? = view?.findViewById(R.id.lineChartTitle)
        lineChartTitle?.text = data[i].first

        lineChart = view?.findViewById(R.id.linechart)

        configureLineChart()
        setLineChartData(data[i].second)

        return view
    }

    private fun configureLineChart() {
        lineChart?.description!!.isEnabled = false
        val xAxis: XAxis = lineChart!!.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat: SimpleDateFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)
            override fun getFormattedValue(value: Float): String {
                val millis = value.toLong()
                return mFormat.format(Date(millis))
            }
        }
    }

    private fun setLineChartData(pricesHigh: MutableList<Entry>) {
        val dataSets: ArrayList<ILineDataSet> = ArrayList()

        val highLineDataSet = LineDataSet(
            pricesHigh,
            "Symptom Level"
        )
        highLineDataSet.setDrawCircles(true)
        highLineDataSet.circleRadius = 4f
        highLineDataSet.setDrawValues(false)
        highLineDataSet.lineWidth = 3f
        highLineDataSet.color = Color.GREEN
        highLineDataSet.setCircleColor(Color.GREEN)
        dataSets.add(highLineDataSet)

        val lineData = LineData(dataSets)
        lineChart!!.data = lineData
        lineChart!!.invalidate()
    }

    init {
        this.context = context
        this.data = data
    }
}