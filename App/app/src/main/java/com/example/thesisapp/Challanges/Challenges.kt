package com.example.thesisapp.Challanges

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesisapp.R
import kotlinx.android.synthetic.main.activity_challenges.*

class Challenges : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)

        val challenge = ArrayList<challangeDataModel> ()

        challenge.add(
                challangeDataModel("Recommendation", "Tell about this app to family and friends", R.drawable.family, R.drawable.tick)
        )
        challenge.add(
                challangeDataModel("Activity", "Keep using this app for 30 days", R.drawable.phone, R.drawable.tick)
        )
        challenge.add(
                challangeDataModel("Plastic bag reduction", "Take your own bag for shopping", R.drawable.grocery,null)
        )
        challenge.add(
                challangeDataModel("PET bottle reduction", "Take your drinks from home with you", R.drawable.plastic,null)
        )
        challenge.add(
                challangeDataModel("Plastic reduction", "Do not use plastic straws", R.drawable.straw, null)
        )
        challenge.add(
                challangeDataModel("Cosmetics change", "Use bar soap and shampoo bar", R.drawable.soap,null)
        )

        val challengeViewAdapter = challangeViewAdapter(challenge, this)

        recyclerViewChallenges.layoutManager = LinearLayoutManager(this)
        recyclerViewChallenges.adapter = challengeViewAdapter
    }
}
