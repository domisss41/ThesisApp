package com.example.thesisapp

import android.content.Intent
import android.graphics.Color.GRAY
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.thesisapp.expandableListAddPlastic.plasticValueModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_statystics.*
import java.time.LocalDateTime
import java.time.ZoneOffset


class Statistics : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statystics)

        val yearChart : Button = findViewById(R.id.yearStatistics)

        yearChart.setOnClickListener {
            val intent = Intent(this, statisticsYear::class.java)
            startActivity(intent)
        }

        // chart

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
                            var date = LocalDateTime.parse(pointValue.xvalue).toEpochSecond(ZoneOffset.ofHours(2))
                            if (i == 0)
                                referenceTimestamp = date
                            bins[date - referenceTimestamp] = bins.getOrDefault(date - referenceTimestamp, 0f) + pointValue.yvalue
                        }
                    }

                    // It should look like this...
                    var dataChartPlastic = ArrayList<Entry>()
                    for (bin in bins)
                        dataChartPlastic.add(Entry((bin.key).toFloat(), bin.value))


                    // .. but we needed to make a hot fix
                    var dataChartPlasticWeek = ArrayList<Entry>()
                    for(i in 7 downTo 1)
                        dataChartPlasticWeek.add( Entry(dataChartPlastic[dataChartPlastic.count() - i].x, dataChartPlastic[dataChartPlastic.count() - i].y ) )


//                    val plot2 = LineDataSet(dataChartPlastic, "Plastic weight [ g ]")
                    val plot2 = LineDataSet(dataChartPlasticWeek, "Plastic weight [ g ]")



                    plot2.lineWidth = 3f
                    plot2.circleRadius = 5F
                    plot2.valueTextSize = 8F
                    plot2.color = GRAY
                    plot2.setCircleColor(GRAY)

                    plasticChartFirebase.data = LineData(plot2)
                    plasticChartFirebase.axisRight.isEnabled = false

                    plasticChartFirebase.setTouchEnabled(true)
                    plasticChartFirebase.description.text = "Days"

                    plasticChartFirebase.description.textSize = 10F

                    val xAxisFormatter: IAxisValueFormatter = DateChartFormatter(referenceTimestamp)
                    val xAxis: XAxis = plasticChartFirebase.xAxis
                    val yAxis: YAxis = plasticChartFirebase.axisLeft

                    xAxis.valueFormatter = xAxisFormatter
//                    xAxis.setLabelCount(bins.count(), true)
                    xAxis.setLabelCount(7, true)


                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
                    yAxis.axisMinimum = 0F

                    plasticChartFirebase.invalidate()

                    // count all plastic

                    var totalPlastic = 0f
                    var todayResultValue = 0f
                    for (bin in bins){
                        totalPlastic += bin.value
                        todayResultValue = bin.value
                    }

                    allResult.text = totalPlastic.toInt().toString()
                    todayResult.text = todayResultValue.toInt().toString()
                }



            override fun onCancelled(databaseError: DatabaseError) {
            }


        }

        mDatabase.addListenerForSingleValueEvent(addValueEventListener)

    }
}
