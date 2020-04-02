package com.example.thesisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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

        if (login.text.toString().isEmpty()) {
            login.error = "Please enter login"
            login.requestFocus()
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

        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressBar.visibility = View.VISIBLE
                    Toast.makeText(baseContext, "Registration completed, check your email box", Toast.LENGTH_SHORT).show()

                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, Login::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again", Toast.LENGTH_SHORT).show()
                }

            }
    }
}

