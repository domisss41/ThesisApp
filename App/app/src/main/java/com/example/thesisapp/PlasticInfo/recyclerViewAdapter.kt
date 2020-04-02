package com.example.thesisapp.PlasticInfo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thesisapp.PlasticInfo.PlasticTypes.*
import com.example.thesisapp.R
import kotlinx.android.synthetic.main.plastictypes.view.*


class recyclerViewAdapter(val userList: ArrayList<userDataModel>, val context: Context) :
    RecyclerView.Adapter<recyclerViewAdapter.ViewHolder>() {

    class ViewHolder(plastictypes: View) : RecyclerView.ViewHolder(plastictypes){

        fun bindItems(userDataModel: userDataModel){

            itemView.title.text = userDataModel.title
            itemView.description.text = userDataModel.desc
            itemView.imagePlasticInfo.setImageResource(userDataModel.pic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plastictypes,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])

        holder.itemView.setOnClickListener{
            if (position == 0){
                val intent = Intent(context, PETE ::class.java)
                context.startActivity(intent)
            }
            if (position == 1){
                val intent = Intent(context, HDPE ::class.java)
                context.startActivity(intent)
            }
            if (position == 2){
                val intent = Intent(context, PCV::class.java)
                context.startActivity(intent)
            }
            if (position == 3){
                val intent = Intent(context, PCV::class.java)
                context.startActivity(intent)
            }
            if (position == 4){
                val intent = Intent(context, LDPE::class.java)
                context.startActivity(intent)
            }
            if (position == 5){
                val intent = Intent(context, PP::class.java)
                context.startActivity(intent)
            }
            if (position == 6){
                val intent = Intent(context, PS::class.java)
                context.startActivity(intent)
            }
            if (position == 7){
                val intent = Intent(context, OTHER::class.java)
                context.startActivity(intent)
            }
        }
    }

}
