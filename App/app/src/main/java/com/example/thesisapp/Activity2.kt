package com.example.thesisapp

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.thesisapp.PlasticInfo.PlasticInfo
import com.example.thesisapp.Recycling.Recycling
import com.example.thesisapp.expandableListAddPlastic.PlasticExpandableListView

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val profile : Button = findViewById(R.id.buttonMyAccount)

        profile.setOnClickListener {
            val intent = Intent(this, MyAccount:: class.java)
            startActivity(intent)
        }

        val howToRecycle : Button = findViewById(R.id.buttonRecycling)

        howToRecycle.setOnClickListener {
            val intent = Intent(this, Recycling:: class.java)
            startActivity(intent)
        }

        val plasticAddition : Button = findViewById(R.id.buttonAddPlastic)

        plasticAddition.setOnClickListener {
            val intent = Intent(this, PlasticExpandableListView:: class.java)
            startActivity(intent)
        }

        val addChallenges : Button = findViewById(R.id.buttonChallenge)

        addChallenges.setOnClickListener {
            val intent = Intent(this, AddPlastic:: class.java)
            startActivity(intent)
        }

        val statysticsButton : Button = findViewById(R.id.buttonStatistics)

        statysticsButton.setOnClickListener {
            val intent = Intent(this, Statystics :: class.java)
            startActivity(intent)
        }

        val information : Button = findViewById(R.id.buttonPlasticInfo)

        information.setOnClickListener {
            val intent = Intent(this, PlasticInfo:: class.java)
            startActivity(intent)
        }
    }
}
