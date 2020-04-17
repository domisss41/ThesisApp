package com.example.thesisapp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_my_account.*

class MyAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

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
