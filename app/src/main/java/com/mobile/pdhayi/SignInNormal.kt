package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class SignInNormal : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var password : EditText
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_normal)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        findViewById<TextView>(R.id.userSignUp).setOnClickListener {
            startActivity(Intent(this,SignUpNormal::class.java))
        }
        findViewById<TextView>(R.id.forgotPass).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        email = findViewById(R.id.emailAddressFieldLogin)
        password = findViewById(R.id.passwordFieldLogin)
        findViewById<Button>(R.id.signInBtn).setOnClickListener{
            if(email.text.isEmpty() || password.text.isEmpty()){
                Toast.makeText(this, "$email $password", Toast.LENGTH_SHORT).show()
                if(email.text.isEmpty()) {
                    findViewById<TextView>(R.id.emailAddressFieldLogin).error = "Email Address is required"
                }
                if(password.text.isEmpty()) {
                    findViewById<TextView>(R.id.passwordFieldLogin).error = "Password is required"
                }
            }else{
                Toast.makeText(this, "$email $password", Toast.LENGTH_SHORT).show()
                val client = OkHttpClient()
                // JSON media type
                val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
                // Create JSON object
                val json = JSONObject()
                json.put("email", email.text.toString())
                json.put("password", password.text.toString())
                // Create request body
                val body = RequestBody.create(JSON, json.toString())
                // Create request
                val request = Request.Builder()
                    .url("https://knowledgify-backend.onrender.com/api/login")
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
                                Toast.makeText(this@SignInNormal, responseBody, Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@SignInNormal, HomeActivity::class.java))
                                finish()
                            }
                        } else {
                            runOnUiThread {
                                Log.d("API ResponseE" +
                                        "${email.text.toString()} ${password.text.toString()}", response.code.toString())
                                if(response.code == 400){
                                    Toast.makeText(applicationContext, "Email does not exists,Please try to signup", Toast.LENGTH_SHORT).show()
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