package com.mobile.pdhayi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class SignUpNormal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_normal)

        val firstName: EditText = findViewById(R.id.firstName)
        val lastName: EditText = findViewById(R.id.lastName)
        val email: EditText = findViewById(R.id.emailAddressFieldSignUp)
        val password: EditText = findViewById(R.id.passwordField)
        val passwordConfirm: EditText = findViewById(R.id.confirmPassword)

        findViewById<Button>(R.id.signUpBtn).setOnClickListener {
            if (firstName.text.isNullOrEmpty() || lastName.text.isNullOrEmpty() || email.text.isNullOrEmpty() || password.text.isNullOrEmpty() || passwordConfirm.text.isNullOrEmpty()) {
                if (firstName.text.isNullOrEmpty()) {
                    firstName.error = "First Name is required"
                }
                if (lastName.text.isNullOrEmpty()) {
                    lastName.error = "Last Name is required"
                }
                if (email.text.isNullOrEmpty()) {
                    email.error = "Email Address is required"
                }
                if (password.text.isNullOrEmpty()) {
                    password.error = "Password is required"
                }
                if (passwordConfirm.text.isNullOrEmpty()) {
                    passwordConfirm.error = "Please confirm your password"
                }
            } else if (password.text.toString() != passwordConfirm.text.toString()) {
                passwordConfirm.error = "Password does not match"
            } else {
                val json = JSONObject().apply {
                    put("firstName", firstName.text.toString())
                    put("lastName", lastName.text.toString())
                    put("email", email.text.toString())
                    put("password", password.text.toString())
                }

                ApiClient.post(ApiUrls.REGISTER, json) { success, code, responseBody ->
                    runOnUiThread {
                        if (success) {
                            Toast.makeText(this@SignUpNormal, responseBody, Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, VerificationActivity::class.java).apply {
                                putExtra("email", email.text.toString())
                                putExtra("password", password.text.toString())
                                putExtra("firstName", firstName.text.toString())
                                putExtra("lastName", lastName.text.toString())
                            }
                            startActivity(intent)
                            finish()
                        } else {
                            Log.d("API ResponseE", code.toString())
                            if (code == 400) {
                                Toast.makeText(applicationContext, "Email already exists, Please try to login", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}
