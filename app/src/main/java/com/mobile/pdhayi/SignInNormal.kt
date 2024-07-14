package com.mobile.pdhayi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class SignInNormal : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_normal)
        findViewById<TextView>(R.id.userSignUp).setOnClickListener {
            startActivity(Intent(this, SignUpNormal::class.java))
        }
        findViewById<TextView>(R.id.forgotPass).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        email = findViewById(R.id.emailAddressFieldLogin)
        password = findViewById(R.id.passwordFieldLogin)
        findViewById<Button>(R.id.signInBtn).setOnClickListener {
            if (email.text.isEmpty() || password.text.isEmpty()) {
                if (email.text.isEmpty()) {
                    email.error = "Email Address is required"
                }
                if (password.text.isEmpty()) {
                    password.error = "Password is required"
                }
            } else {
                val json = JSONObject().apply {
                    put("email", email.text.toString())
                    put("password", password.text.toString())
                }
                ApiClient.post(ApiUrls.LOGIN, json) { success, code, responseBody ->
                    runOnUiThread {
                        if (success) {
                            Toast.makeText(this, responseBody, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        } else {
                            if (code == 404) {
                                Toast.makeText(applicationContext, "User does not exist, please try to signup", Toast.LENGTH_SHORT).show()
                            } else if (code == 401) {
                                Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(applicationContext, "Server error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}