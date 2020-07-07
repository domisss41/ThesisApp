package com.example.thesisapp.expandableListAddPlastic

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.thesisapp.R
import com.example.thesisapp.SubmitPlastic
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_plastic_expandable_list_view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class PlasticExpandableListView : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    /*@RequiresApi(Build.VERSION_CODES.O)
    var date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern("hh:mm:ss")
    } else {
        TODO("VERSION.SDK_INT < O")
    }*/

    val header : MutableList<String> = ArrayList()
    val body: MutableList<MutableList<plasticTypeModel>> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plastic_expandable_list_view)


        database = FirebaseDatabase.getInstance()
        mDatabase=database.getReference("chartPlastic")



        val dataRaw = PlasticKindsSet()
        val arrayData = dataRaw.getPlasticTypes()

        val oneComplex = arrayData.get(0).toMutableList()
        val One : MutableList<plasticTypeModel> = ArrayList()
        for(item in oneComplex){
            One.add(item)
        }

        val twoComplex = arrayData.get(1).toMutableList()
        val Two : MutableList<plasticTypeModel> = ArrayList()
        for(item in twoComplex){
            Two.add(item)
        }

        val threeComplex = arrayData.get(2).toMutableList()
        val Three : MutableList<plasticTypeModel> = ArrayList()
        for(item in threeComplex){
            Three.add(item)
        }

        val fourComplex = arrayData.get(3).toMutableList()
        val Four : MutableList<plasticTypeModel> = ArrayList()
        for(item in fourComplex){
            Four.add(item)
        }

        header.add("Food packaging and containers")
        header.add("Cosmetics and detergents")
        header.add("Disposable plastic")
        header.add("E-waste")

        body.add(One)
        body.add(Two)
        body.add(Three)
        body.add(Four)


        expandableListView.setAdapter(
            ExpandableListAdapter(
                this,
                header,
                body
            )
        )

        val submitSubmit : Button = findViewById(R.id.addPlasticUsedToday)

        submitSubmit.setOnClickListener {
            Toast.makeText(baseContext, "Plastic was successfully added", Toast.LENGTH_SHORT).show()

//            val intent = Intent(this, SubmitPlastic :: class.java)
//            startActivity(intent)

            var count = 0f

            for (group in body){
                for (item in group)
                    count += item.unitWeight*item.countPlastic
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setValueChartXandY(count)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setValueChartXandY(count: Float) {
        var id = mDatabase.push().getKey()

        var current = LocalDateTime.now()
        var x = LocalDateTime.of(current.year, current.month, current.dayOfMonth, 0, 0).toString()
        var y = count

        val pointValue = plasticValueModel(x, y)
        if (id != null) {
            mDatabase.child(id).setValue(pointValue)
        }

        mDatabase.child(id.toString()).setValue(pointValue)
    }


}
