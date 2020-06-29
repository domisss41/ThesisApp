package com.example.thesisapp

import android.content.Intent
import android.graphics.Color.GRAY
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.thesisapp.expandableListAddPlastic.plasticValueModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_statistics_year.*
import kotlinx.android.synthetic.main.activity_statistics_year.allResult
import kotlinx.android.synthetic.main.activity_statistics_year.todayResult
import kotlinx.android.synthetic.main.activity_statystics.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class statisticsYear : AppCompatActivity() {


    lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics_year)

        val weekChart : Button = findViewById(R.id.weekStatistics)

        weekChart.setOnClickListener {
            val intent = Intent(this, Statistics::class.java)
            startActivity(intent)
        }


        mDatabase = FirebaseDatabase.getInstance().getReference("chartPlastic")

        val addValueEventListener= object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(dataSnapshot: DataSnapshot) {
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
                val January = 1577836800
                val February = 1580515200
                val March = 1583020800
                val April = 1585699200
                val May = 1588291200
                val June = 1590969600
                val July = 1593561600
                val August = 1596240000
                val September = 1598918400
                val October = 1601510400
                val November = 1604188800
                val December = 1606780800
                val January2021 = 1609372800
                val February2021 = 1612051200

                var dataBarChartPlastic = ArrayList<BarEntry>()
                for (i in 0 until 12 ) {
                    dataBarChartPlastic.add(BarEntry(i.toFloat(), 0.toFloat()))
                }

                for (bin in bins){
                    if( January < bin.key && bin.key < March)
                        dataBarChartPlastic[1].y += bin.value.toFloat()

                    else if( February < bin.key && bin.key < April)
                        dataBarChartPlastic[2].y += bin.value.toFloat()

                    else if( March < bin.key && bin.key < May)
                        dataBarChartPlastic[3].y += bin.value.toFloat()

                    else if( April < bin.key && bin.key < June)
                        dataBarChartPlastic[4].y += bin.value.toFloat()

                    else if( May < bin.key && bin.key < July)
                        dataBarChartPlastic[5].y += bin.value.toFloat()

                    else if( June < bin.key && bin.key < August)
                        dataBarChartPlastic[6].y += bin.value.toFloat()

                    else if( July < bin.key && bin.key < September)
                        dataBarChartPlastic[7].y += bin.value.toFloat()

                    else if( August < bin.key && bin.key < October)
                        dataBarChartPlastic[8].y += bin.value.toFloat()

                    else if( September < bin.key && bin.key < November)
                        dataBarChartPlastic[9].y += bin.value.toFloat()

                    else if( October < bin.key && bin.key < December)
                        dataBarChartPlastic[10].y += bin.value.toFloat()

                    else if( November < bin.key && bin.key < January2021)
                        dataBarChartPlastic[11].y += bin.value.toFloat()

                    else if( December < bin.key && bin.key < February2021)
                        dataBarChartPlastic[12].y += bin.value.toFloat()

                }

                var plot2 = BarDataSet(dataBarChartPlastic, "Plastic weight [ g ]")

                plot2.valueTextSize = 8F
                plot2.barBorderWidth = 1F
                plot2.color = GRAY
                plot2.setDrawValues(false)
//                plasticBarChartFirebase.setFitBars(true)

                plasticBarChartFirebase.data = BarData(plot2)
                plasticBarChartFirebase.axisRight.isEnabled = false
                plasticBarChartFirebase.setTouchEnabled(true)
                plasticBarChartFirebase.description.text = "Months"
                plasticBarChartFirebase.description.textSize = 10F

//                val xAxisFormatter: IAxisValueFormatter = DateChartFormatter(referenceTimestamp)
                val xAxis: XAxis = plasticBarChartFirebase.xAxis
                val yAxis: YAxis = plasticBarChartFirebase.axisLeft

                val xAxisLabel: ArrayList<String> = ArrayList()
                xAxisLabel.add("Jan")
                xAxisLabel.add("Feb")
                xAxisLabel.add("Mar")
                xAxisLabel.add("Apr")
                xAxisLabel.add("May")
                xAxisLabel.add("Jun")
                xAxisLabel.add("Jul")
                xAxisLabel.add("Aug")
                xAxisLabel.add("Sep")
                xAxisLabel.add("Oct")
                xAxisLabel.add("Nov")
                xAxisLabel.add("Dec")
//
                xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)

                xAxis.labelRotationAngle = -0f
                xAxis.labelCount = 12
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
                yAxis.axisMinimum = 1F
                yAxis.axisMinimum = 0F
                plasticBarChartFirebase.invalidate()

                // total and today's results

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
