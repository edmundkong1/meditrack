package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
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

        //get line chart title
        val lineChartTitle : TextView? = view?.findViewById(R.id.lineChartTitle)
        lineChartTitle?.text = data[i].first

        lineChart = view?.findViewById(R.id.linechart)

        configureLineChart()
        setLineChartData(data[i].second)

        return view
    }

    //used for configuring line chart - x axis
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

    //set data for line charts
    private fun setLineChartData(symptomsPoint: MutableList<Entry>) {
        val dataSets: ArrayList<ILineDataSet> = ArrayList()

        //dataset contains points, and a title
        val lineChartDataset = LineDataSet(
            symptomsPoint,
            "Symptom Level"
        )

        //create datapoints
        lineChartDataset.setDrawCircles(true)
        lineChartDataset.circleRadius = 4f
        lineChartDataset.setDrawValues(false)
        lineChartDataset.lineWidth = 3f
        lineChartDataset.color = Color.GREEN
        lineChartDataset.setCircleColor(Color.GREEN)
        dataSets.add(lineChartDataset)

        val lineData = LineData(dataSets)
        lineChart!!.data = lineData
        lineChart!!.invalidate()
    }

    init {
        this.context = context
        this.data = data
    }
}