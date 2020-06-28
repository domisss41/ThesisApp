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
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_statistics_year.*
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

                var dataBarChartPlastic = ArrayList<BarEntry>()
                for (bin in bins)
                    dataBarChartPlastic.add(BarEntry((bin.key).toFloat(), bin.value))
                var plot2 = BarDataSet(dataBarChartPlastic, "Plastic weight [ g ]")

                plot2.valueTextSize = 8F
                plot2.barBorderWidth = 10F
                plot2.barBorderColor = GRAY
                plot2.setDrawValues(false)
                plasticBarChartFirebase.setFitBars(true)

                plasticBarChartFirebase.data = BarData(plot2)
                plasticBarChartFirebase.axisRight.isEnabled = false
                plasticBarChartFirebase.setTouchEnabled(true)
                plasticBarChartFirebase.description.text = "Months"
                plasticBarChartFirebase.description.textSize = 10F

                val xAxisFormatter: IAxisValueFormatter = DateChartFormatter(referenceTimestamp)
                val xAxis: XAxis = plasticBarChartFirebase.xAxis
                val yAxis: YAxis = plasticBarChartFirebase.axisLeft

                xAxis.valueFormatter = xAxisFormatter
                xAxis.setLabelCount(bins.count(), true)
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)

                yAxis.axisMinimum = 0F
                plasticBarChartFirebase.invalidate()
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }

        mDatabase.addListenerForSingleValueEvent(addValueEventListener)

    }
}
