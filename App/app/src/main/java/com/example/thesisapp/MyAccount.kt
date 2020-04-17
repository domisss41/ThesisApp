package com.example.thesisapp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_my_account.*
import kotlin.reflect.typeOf

class MyAccount : AppCompatActivity() {

    private val TAG = "MyAccount"
    private lateinit var mUser: User
    private lateinit var  mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference
    private lateinit var userReference: Query
    private lateinit var auth: FirebaseAuth
    //private lateinit var currentUserLogin: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        auth = FirebaseAuth.getInstance()
        val currentUserID = auth.uid
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")


        val usersListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children){
                    val tempID = ds.child("id").getValue(String::class.java)
                    if(tempID.equals(currentUserID)){
                        Log.d(TAG, "Znaleziono!")
                        login.text = tempID
                        login.text = ds.child("login").getValue(String::class.java)
                        email.text = ds.child("email").getValue(String::class.java)
                        birth.text = ds.child("birth").getValue(String::class.java)
                        break
                    }
                }
//
//                if (dataSnapshot.exists()) {
//
//                    login.text = user.login
//                    email.text = user.email
//                    birth.text = user.birth
//                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
                Log.e(TAG, "onCancelled: Failed to read message")

                login.text = ""
                email.text = ""
                birth.text = "onCancelled: Failed to read message!"
            }
        }

        mDatabase.addListenerForSingleValueEvent(usersListener)



        /*mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        fun currentUserReference(): DatabaseReference
                = mDatabase.child("Users").child(mAuth.currentUser!!.uid)

        val ref = FirebaseDatabase.getReference("Users")

        ref.addValueEventListener(Users: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })*/

        buttonAddProfilePicture.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }
    var selectedPhoto: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            selectedPhoto = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhoto)
            val bitmapDrawable = BitmapDrawable(bitmap)
            buttonAddProfilePicture.setBackgroundDrawable(bitmapDrawable)
        }
    }
}
