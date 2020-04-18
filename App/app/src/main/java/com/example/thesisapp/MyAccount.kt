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
                        nickname.text = tempID
                        nickname.text = ds.child("login").getValue(String::class.java)
                        email.text = ds.child("email").getValue(String::class.java)
                        birth.text = ds.child("birth").getValue(String::class.java)
                        break
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
                Log.e(TAG, "onCancelled: Failed to read message")

                nickname.text = ""
                email.text = ""
                birth.text = "onCancelled: Failed to read message!"
            }
        }

        mDatabase.addListenerForSingleValueEvent(usersListener)


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
