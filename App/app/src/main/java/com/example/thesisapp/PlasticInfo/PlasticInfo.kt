package com.example.thesisapp.PlasticInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesisapp.R
import kotlinx.android.synthetic.main.activity_plastic_info.*

class PlasticInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plastic_info)

        val user = ArrayList<userDataModel> ()

        user.add(
            userDataModel("Polyethylene Terephthalate", "Read more..", R.drawable.pete)
        )
        user.add(
            userDataModel("High-Density Polyethylene", "Read more..", R.drawable.hdpe)
        )
        user.add(
            userDataModel("Polyvinyl Chloride", "Read more..", R.drawable.pvc)
        )
        user.add(
            userDataModel("Low Density Polyethylene", "Read more..", R.drawable.ldpe)
        )
        user.add(
            userDataModel("Polypropylene", "Read more..", R.drawable.pp)
        )
        user.add(
            userDataModel("Polystyrene", "Read more..", R.drawable.ps)
        )
        user.add(
            userDataModel("Everything Else", "Read more..", R.drawable.other)
        )

        val recyclerViewAdapter = recyclerViewAdapter(user, this)

        recyclerViewPlasticInfo.layoutManager = LinearLayoutManager(this)
        recyclerViewPlasticInfo.adapter = recyclerViewAdapter
    }
}
