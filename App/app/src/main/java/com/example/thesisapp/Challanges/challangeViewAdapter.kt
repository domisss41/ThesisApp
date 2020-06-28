package com.example.thesisapp.Challanges

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thesisapp.R
import kotlinx.android.synthetic.main.challengetypes.view.*

class challangeViewAdapter(val ChallengeList: ArrayList<challangeDataModel>, val context: Context) :
        RecyclerView.Adapter<challangeViewAdapter.ViewHolder>() {

    class ViewHolder(challengestypes: View) : RecyclerView.ViewHolder(challengestypes){

        fun bindItems(challangeDataModel: challangeDataModel){

            itemView.titleChallenge.text = challangeDataModel.titleChallege
            itemView.descriptionChallenge.text = challangeDataModel.descChallenge
            itemView.imageChallenge.setImageResource(challangeDataModel.picChallenge)
            challangeDataModel.tickPhoto?.let { itemView.tick.setImageResource(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.challengetypes,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ChallengeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(ChallengeList[position])

        holder.itemView.setOnClickListener {
        }
        }
}