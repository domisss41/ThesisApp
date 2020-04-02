package com.example.thesisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.password

class Login : AppCompatActivity() {

 private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            auth = FirebaseAuth.getInstance()

        buttonLogIn.setOnClickListener {
            logIn()
        }

        val signUp : TextView = findViewById(R.id.signup)

        signUp.setOnClickListener {
            val intent = Intent(this, SignUp:: class.java)
            startActivity(intent)
        }

        val passwordReminder : TextView = findViewById(R.id.forgotPassword)

        passwordReminder.setOnClickListener {
            val intent = Intent(this, ForgotPassword :: class.java)
            startActivity(intent)
        }

    }
        private fun logIn(){

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

            if (password.length() < 4) {
                password.error = "Password is too short, must contains at least 4 characters"
                password.requestFocus()
                return
            }

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        updateUI(null)
                    }
                }
        }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser != null){
            if (currentUser.isEmailVerified){
                startActivity(Intent(this, MainActivity:: class.java))
                finish()
            } else {
                Toast.makeText(baseContext, "Please verify your email address",Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(baseContext, "Login failed",Toast.LENGTH_SHORT).show()
        }
    }

}

