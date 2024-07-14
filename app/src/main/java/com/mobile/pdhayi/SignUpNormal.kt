package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class SignUpNormal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_normal)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val firstName : EditText = findViewById(R.id.firstName)
        val lastName : EditText = findViewById(R.id.lastName)
        val email : EditText = findViewById(R.id.emailAddressFieldSignUp)
        val password : EditText = findViewById(R.id.passwordField)

        findViewById<Button>(R.id.signUpBtn).setOnClickListener {
            if(firstName.text.isNullOrEmpty() || lastName.text.isNullOrEmpty() || email.text.isNullOrEmpty() || password.text.isNullOrEmpty()){
                if(firstName.text.isNullOrEmpty()) {
                    firstName.error = "First Name is required"
                }
                if(lastName.text.isNullOrEmpty()) {
                    lastName.error = "Last Name is required"
                }
                if(email.text.isNullOrEmpty()) {
                    email.error = "Email Address is required"
                }
                if(password.text.isNullOrEmpty()) {
                    password.error = "Password is required"
                }
            } else {
                val client = OkHttpClient()
                // JSON media type
                val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
                // Create JSON object
                val json = JSONObject()
                json.put("firstName", firstName.text.toString())
                json.put("lastName", lastName.text.toString())
                json.put("email", email.text.toString())
                json.put("password", password.text.toString())
                // Create request body
                val body = RequestBody.create(JSON, json.toString())
                // Create request
                val request = Request.Builder()
                    .url("https://knowledgify-backend.onrender.com/api/register")
                    .post(body)
                    .build()
                // Execute request
                client.newCall(request).enqueue(object : okhttp3.Callback {
                    override fun onFailure(call: okhttp3.Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: okhttp3.Call, response: Response) {
                        if (response.isSuccessful) {
                            val responseBody = response.body?.string()
                            Log.d("API Response", responseBody.toString())
                            runOnUiThread {
                                Toast.makeText(this@SignUpNormal, responseBody, Toast.LENGTH_SHORT).show()
                            }
                            val intent = Intent(applicationContext, VerificationActivity::class.java)
                            intent.putExtra("email", email.text.toString())
                            intent.putExtra("password", password.text.toString())
                            intent.putExtra("firstName", firstName.text.toString())
                            intent.putExtra("lastName", lastName.text.toString())
                            startActivity(intent)
                            finish()
                        } else {
                            runOnUiThread {
                                Log.d("API ResponseE", response.code.toString())
                                if(response.code == 400){
                                    Toast.makeText(applicationContext, "Email already exists,Please try to login", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                })
            }
        }
    }
}
