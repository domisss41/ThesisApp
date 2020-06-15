package com.example.thesisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewFlipper: ViewFlipper
    var image = intArrayOf(R.drawable.turtle, R.drawable.bear, R.drawable.bottle,
            R.drawable.fish, R.drawable.turtletwo, R.drawable.oceanplastic, R.drawable.laptop)//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloUser.text = "Welcome User!"

        val mainScreen : Button = findViewById(R.id.buttonHowToSafeWorld)

        mainScreen.setOnClickListener {
            val intent = Intent(this, Activity2 :: class.java)
            startActivity(intent)
        }

        viewFlipper = findViewById(R.id.mainScreenImages)
        for(i in 0 until image.size) {
            flip_image(image[i])
        }
    }

    fun flip_image(i : Int) {
        val view = ImageView (this)
        view.setBackgroundResource(i)
        viewFlipper.addView(view)
        viewFlipper.setFlipInterval(4000)
        viewFlipper.setAutoStart(true)
        viewFlipper.setInAnimation(this, android.R.anim.fade_in)
        viewFlipper.setOutAnimation(this, android.R.anim.fade_out)
    }


}
