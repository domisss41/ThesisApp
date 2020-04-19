package com.example.thesisapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class AddPlastic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plastic)

            val decrease: Button = findViewById(R.id.minus)
            val increase: Button = findViewById(R.id.plus)
            val value: TextView = findViewById(R.id.textView1)
            var counter = 0
            var stringVal: String

        decrease.setOnClickListener {
            if(counter > 0) {
                counter--
                stringVal = counter.toString()
                value.setText(stringVal)
            }
        }

        increase.setOnClickListener {
                counter++
                stringVal = counter.toString()
                value.text = stringVal
        }

    }
}
