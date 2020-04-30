package com.example.thesisapp.expandableListAddPlastic

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.TextView
import com.example.thesisapp.R
import com.example.thesisapp.plasticTypeModel

class ExpandableListAdapter(
    var context: Context,
    var header: MutableList<String>,
    var body : MutableList<MutableList<String>>) : BaseExpandableListAdapter() {

    lateinit var buttonIncrease: Button
    lateinit var buttonDecrease: Button
    lateinit var buttonSubmit: Button
    lateinit var allItemsArray: ArrayList<DataReceive>

    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group,null)
        }

        val title = convertView?.findViewById<TextView>(R.id.plasticTitle)
        title?.text = getGroup(groupPosition)
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_child,null)
        }

        val title = convertView?.findViewById<TextView>(R.id.plasticTitleChild)
        title?.text = getChild(groupPosition, childPosition)

        var itemsAmount = 0

        if (convertView != null) {
            buttonIncrease = convertView.findViewById(R.id.increase)
            buttonDecrease = convertView.findViewById(R.id.decrease)
        }

        buttonIncrease.setOnClickListener(){
            val textViewResult = convertView?.findViewById<TextView>(R.id.result)
            textViewResult?.text = (textViewResult?.text.toString().toInt() + 1).toString()
            body
        }

        buttonDecrease.setOnClickListener(){
            val textViewResult = convertView?.findViewById<TextView>(R.id.result)
            if(textViewResult?.text.toString().toInt() > 0) {
                textViewResult?.text = (textViewResult?.text.toString().toInt() - 1).toString()
            }
        }



        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }
}

class DataReceive(val name: String, val amount: Int){}
