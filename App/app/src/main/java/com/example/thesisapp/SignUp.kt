package com.example.thesisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.view.*

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        val backSignUp : TextView = findViewById(R.id.backToSignUp)

        backSignUp.setOnClickListener {
            val intent = Intent(this, Login:: class.java)
            startActivity(intent)
        }

        buttonJoinIn.setOnClickListener{
            signUpUser()
        }
    }

    fun signUpUser() {

        if (nickname.text.toString().isEmpty()) {
            nickname.error = "Please enter login"
            nickname.requestFocus()
            return
        }

        if (email.text.toString().isEmpty()) {
            email.error = "Please enter email"
            email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please valid enter email"
            email.requestFocus()
            return
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return
        }

        if (password.text.toString() != repeatPassword.text.toString()) {
            repeatPassword.error = "Passwords do not match"
            repeatPassword.requestFocus()
            return
        }

        if (password.length() < 4) {
            password.error = "Password is too short, must contains at least 6 characters"
            password.requestFocus()
            return
        }

        if (birth.text.toString().isEmpty()) {
            birth.error = "Please enter year of birth"
            birth.requestFocus()
            return
        }



        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressBar.visibility = View.VISIBLE
                    Toast.makeText(baseContext, "Registration completed", Toast.LENGTH_SHORT).show()

                    val user = auth.currentUser

                    saveUser()

                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { tasksecond ->
                            if (tasksecond.isSuccessful) {
                                startActivity(Intent(this, Login::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again", Toast.LENGTH_SHORT).show()
                }

            }
    }

    private fun saveUser(){

        var login = nickname.text.toString()
        var email = email.text.toString()
        var password = password.text.toString()
        var birth = birth.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        //val userId = ref.push().key
        val userId = auth.uid
        val userKey = auth.uid

        val user = User(userKey.toString(), login.toString(), email.toString(), password.toString(), birth.toString())

        ref.child(userId.toString()).setValue(user)
    }

}

