package com.example.thesisapp

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.thesisapp.expandableListAddPlastic.plasticValueModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_statystics.*
import java.time.LocalDateTime
import java.time.ZoneOffset


class Statystics : AppCompatActivity() {

    lateinit var Temp_linechart: LineChart
    lateinit var yData:ArrayList<Entry>
    lateinit var valueEventListener: ValueEventListener

    lateinit var database: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statystics)

//plot
        val xLabel  = ArrayList<String>()
        val yArray= ArrayList<Entry>()

        yArray.add(Entry(1f,4.03f))
        yArray.add(Entry(2f, 50.7f))
        yArray.add(Entry(3f, 30.7f))
        yArray.add(Entry(4f, 3.7f))
        yArray.add(Entry(5f, 16.7f))
        yArray.add(Entry(6f, 11.7f))
        yArray.add(Entry(7f, 41.7f))
        yArray.add(Entry(8f, 13.7f))

        val plot = LineDataSet(yArray, "Used plastic")

        plot.setDrawValues(false)
        plot.setDrawFilled(true)
        plot.lineWidth = 3f
        plot.fillColor = R.color.colorAccent


        plasticLineChart.data = LineData(plot)
        plasticLineChart.axisRight.isEnabled = false

        plasticLineChart.setTouchEnabled(true)
        plasticLineChart.setPinchZoom(true)

        plasticLineChart.description.text = "Days"
        plasticLineChart.description.textSize = 10F

        val xaxis: XAxis = plasticLineChart.xAxis
        //xaxis.granularity = 1f

        // chart 2

        Temp_linechart = findViewById(R.id.plasticChartFirebase) as LineChart

        mDatabase = FirebaseDatabase.getInstance().getReference("chartPlastic")

            val addValueEventListener= object : ValueEventListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onDataChange(dataSnapshot:DataSnapshot) {
                    var bins = mutableMapOf<Long, Float>()
                    var referenceTimestamp = 0L
                    for ((i, myDataSnapshot) in dataSnapshot.children.withIndex())
                    {
                        val pointValue = myDataSnapshot.getValue(plasticValueModel::class.java)
                        if (pointValue != null) {
                            var date = LocalDateTime.parse(pointValue!!.xvalue).toEpochSecond(ZoneOffset.ofHours(2))
                            if (i == 0)
                                referenceTimestamp = date
                            bins[date - referenceTimestamp] = bins.getOrDefault(date - referenceTimestamp, 0f) + pointValue.yvalue
                        }
                    }



                    var dataChartPlastic = ArrayList<Entry>()
                    for (bin in bins)
                        dataChartPlastic.add(Entry((bin.key).toFloat(), bin.value))
                    val plot2 = LineDataSet(dataChartPlastic, "Minute plastic")



                    plasticChartFirebase.data = LineData(plot2)

                    val xAxisFormatter: IAxisValueFormatter = DateChartFormatter(referenceTimestamp)
                    val xAxis: XAxis = plasticChartFirebase.xAxis
                    xAxis.valueFormatter = xAxisFormatter
                    xAxis.setLabelCount(bins.count(), true)
                    plasticChartFirebase.invalidate()
                }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }

        mDatabase.addListenerForSingleValueEvent(addValueEventListener)

    }
}
