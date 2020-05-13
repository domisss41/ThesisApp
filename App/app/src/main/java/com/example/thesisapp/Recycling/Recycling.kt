package com.example.thesisapp.Recycling

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.renderscript.Sampler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.thesisapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recycling.*
import kotlinx.android.synthetic.main.layout_waste.*

class Recycling : AppCompatActivity(), IFirebaseLoadDone {

    override fun onFirebaseLoadSuccess(wasteList: List<Waste>) {
        this.wasteList = wasteList
        //Get all names from list
        val waste_name_titles = getWasteNameList(wasteList)
        //Create Adapter
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,waste_name_titles)
        searchable_spinner.adapter = adapter
    }

    private fun getWasteNameList(wasteList: List<Waste>): List<String> {
        val result = ArrayList<String>()
        for(waste in wasteList)
            result.add(waste.name!!)
        return result
    }

    override fun onFirebaseLoadFailed(message: String) {
        TODO("Not yet implemented")
    }

    lateinit var wasteRef: DatabaseReference
    lateinit var iFirebaseLoadDone: IFirebaseLoadDone

    //Variable
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var waste_image: ImageView
    lateinit var waste_name: TextView
    lateinit var waste_description: TextView
    lateinit var waste_kind: TextView

    var isFirstTimeClick = true

    var wasteList: List<Waste> = ArrayList<Waste>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycling)

        //Init interface
        iFirebaseLoadDone = this
        //Init DB
        wasteRef = FirebaseDatabase.getInstance().getReference("Wastes")
        //Load Data
        wasteRef.addValueEventListener(object:ValueEventListener{
            var wasteList:MutableList<Waste> = ArrayList<Waste>()
            override fun onCancelled(p0: DatabaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for(wasteSnapShot in p0.children)
                    wasteList.add(wasteSnapShot.getValue<Waste>(Waste::class.java)!!)
                iFirebaseLoadDone.onFirebaseLoadSuccess(wasteList)
            }

        })

        //Init bottom sheet
        bottomSheetDialog = BottomSheetDialog(this)
        val bottom_sheet_view = layoutInflater.inflate(R.layout.layout_waste,null)
        waste_description = bottom_sheet_view.findViewById(R.id.waste_description) as TextView
        waste_image = bottom_sheet_view.findViewById(R.id.waste_image) as ImageView
        waste_name = bottom_sheet_view.findViewById(R.id.waste_title) as TextView
        waste_kind = bottom_sheet_view.findViewById(R.id.waste_kind) as TextView

        bottomSheetDialog.setContentView(bottom_sheet_view)

        //Event
        searchable_spinner.onItemSelectedListener = (object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (!isFirstTimeClick) {
                    if (parent != null) {
                        if (parent.getItemAtPosition(position).equals(">> CHECK WHERE TO THROW IT <<")) {

                        } else {
                            val waste = wasteList[position]
                            waste_name.text = waste.name
                            waste_description.text = waste.description
                            waste_kind.text = waste.kind
                            Picasso.get().load(waste.image).into(waste_image)

                            bottomSheetDialog.show()
                        }
                    }
                }else{
                    isFirstTimeClick = false
                }
            }

        })

    }
}
