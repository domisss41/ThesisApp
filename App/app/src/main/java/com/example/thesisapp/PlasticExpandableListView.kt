package com.example.thesisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_plastic_expandable_list_view.*

class PlasticExpandableListView : AppCompatActivity() {

    val header : MutableList<String> = ArrayList()
    val body: MutableList<MutableList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plastic_expandable_list_view)

        val One : MutableList<String> = ArrayList()
        One.add("Plastic bottle")
        One.add("Plastic bag")
        One.add("Yogurt cub")
        One.add("Chips bag")

        val Two : MutableList<String> = ArrayList()
        Two.add("Shampoo")
        Two.add("Shower gel")

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

        expandableListView.setAdapter(ExpandableListAdapter(this,header,body))

    }
}
