package com.example.thesisapp.expandableListAddPlastic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.TextView
import com.example.thesisapp.R

class ExpandableListAdapter(
    var context: Context,
    var header: MutableList<String>,
    var body : MutableList<MutableList<plasticTypeModel>>) : BaseExpandableListAdapter() {

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
        return body[groupPosition][childPosition].plasticKind
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



        if (convertView != null) {
            buttonIncrease = convertView.findViewById(R.id.increase)
            buttonDecrease = convertView.findViewById(R.id.decrease)
        }

        buttonIncrease.setOnClickListener(){
            val textViewResult = convertView?.findViewById<TextView>(R.id.result)
            textViewResult?.text = (++body[groupPosition][childPosition].countPlastic).toString()
        }

        buttonDecrease.setOnClickListener(){
            val textViewResult = convertView?.findViewById<TextView>(R.id.result)
            if(body[groupPosition][childPosition].countPlastic > 0) {
                textViewResult?.text = (--body[groupPosition][childPosition].countPlastic).toString()
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
