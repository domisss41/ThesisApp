package com.example.thesisapp

import android.os.Build
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class DateChartFormatter: IAxisValueFormatter {
    private var referenceTimestamp: Long = 0

    constructor(referenceTimestamp: Long) {
        this.referenceTimestamp = referenceTimestamp
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        axis!!.granularity = 1f
        val formatter = DateTimeFormatter.ofPattern("dd/MM")
        var label = LocalDateTime.ofEpochSecond(value.toLong() + referenceTimestamp, 0, ZoneOffset.ofHours(2)).format(formatter)
        return label
    }
}