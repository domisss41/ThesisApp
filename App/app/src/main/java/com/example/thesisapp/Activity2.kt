package com.example.thesisapp

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.thesisapp.PlasticInfo.PlasticInfo

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val profile : Button = findViewById(R.id.buttonMyAccount)

        profile.setOnClickListener {
            val intent = Intent(this, MyAccount:: class.java)
            startActivity(intent)
        }

        val recycling : Button = findViewById(R.id.buttonRecycling)

        recycling.setOnClickListener {
            val intent = Intent(this, plasticRecycling:: class.java)
            startActivity(intent)
        }

        val information : Button = findViewById(R.id.buttonPlasticInfo)

        information.setOnClickListener {
            val intent = Intent(this, PlasticInfo:: class.java)
            startActivity(intent)
        }
    }
}
