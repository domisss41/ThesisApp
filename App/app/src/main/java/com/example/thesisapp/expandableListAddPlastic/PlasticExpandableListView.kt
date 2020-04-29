package com.example.thesisapp.expandableListAddPlastic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.thesisapp.PlasticKindsSet
import com.example.thesisapp.R
import kotlinx.android.synthetic.main.activity_plastic_expandable_list_view.*

class PlasticExpandableListView : AppCompatActivity() {

    val header : MutableList<String> = ArrayList()
    val body: MutableList<MutableList<String >> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plastic_expandable_list_view)

        val dataRaw = PlasticKindsSet()
        val arrayData = dataRaw.getPlasticTypes()

        val oneComplex = arrayData.get(0).toMutableList()
        val One : MutableList<String> = ArrayList()
        for(item in oneComplex){
            One.add(item.plasticKind)
        }

        val twoComplex = arrayData.get(1).toMutableList()
        val Two : MutableList<String> = ArrayList()
        for(item in twoComplex){
            Two.add(item.plasticKind)
        }

        val Three :MutableList<String> = ArrayList()
        Three.add("Straw")
        Three.add("Plastic cub")
        Three.add("Plastic plate")

        header.add("Food packaging and containers")
        header.add("Cosmetics and detergents")
        header.add("Disposable plastic")



        body.add(One)
        body.add(Two)
        body.add(Three)

        expandableListView.setAdapter(
            ExpandableListAdapter(
                this,
                header,
                body
            )
        )

    }
}
