package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class VerificationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var firstName: String
    private lateinit var lastName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val otpDigit1: EditText = findViewById(R.id.otpDigit1)
        val otpDigit2: EditText = findViewById(R.id.otpDigit2)
        val otpDigit3: EditText = findViewById(R.id.otpDigit3)
        val otpDigit4: EditText = findViewById(R.id.otpDigit4)

        setupOtpInput(otpDigit1, otpDigit2)
        setupOtpInput(otpDigit2, otpDigit3)
        setupOtpInput(otpDigit3, otpDigit4)
        setupOtpInput(otpDigit4, null)

        intent.getStringExtra("email")?.let {
            email = it
        }
        intent.getStringExtra("password")?.let {
            password = it
        }
        intent.getStringExtra("firstName")?.let {
            firstName = it
        }
        intent.getStringExtra("lastName")?.let {
            lastName = it
        }

        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener {
            val otp = "${otpDigit1.text}${otpDigit2.text}${otpDigit3.text}${otpDigit4.text}"
            if (otp.length == 4 && email.isNotEmpty() and password.isNotEmpty() and firstName.isNotEmpty() and lastName.isNotEmpty()) {
                verifyOtp(otp)
            } else {
                updateOtpFieldBackground(success = false)
                Toast.makeText(this, "Incorrect OTPm", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifyOtp(otp: String) {
        val client = OkHttpClient()
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        val json = JSONObject().apply {
            put("firstName", firstName)
            put("lastName", lastName)
            put("email", email)
            put("password", password)
            put("otp", otp)
        }
        val body = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
            .url("https://knowledgify-backend.onrender.com/api/verify")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    Log.d("API Response", responseBody.toString())
                    runOnUiThread {
                        updateOtpFieldBackground(success = true)
                        Toast.makeText(this@VerificationActivity, "OTP Verified!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@VerificationActivity, HomeActivity::class.java))
                        finish()
                    }
                } else {
                    runOnUiThread {
                        updateOtpFieldBackground(success = false)
                        Toast.makeText(this@VerificationActivity, "Incorrect OTP $otp" +
                                "$email $password $firstName $lastName", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupOtpInput(currentEditText: EditText, nextEditText: EditText?) {
        currentEditText.addTextChangedListener(OtpTextWatcher(currentEditText, nextEditText))
        currentEditText.setOnKeyListener(OtpKeyListener(currentEditText, nextEditText))
    }

    private inner class OtpTextWatcher(private val currentEditText: EditText, private val nextEditText: EditText?) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.length == 1) {
                nextEditText?.requestFocus()
            }
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private inner class OtpKeyListener(private val currentEditText: EditText, private val nextEditText: EditText?) : View.OnKeyListener {
        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                if (currentEditText.text.isEmpty()) {
                    val previousEditText = getPreviousEditText(currentEditText)
                    previousEditText?.requestFocus()
                }
            }
            return false
        }

        private fun getPreviousEditText(currentEditText: EditText): EditText? {
            return when (currentEditText.id) {
                R.id.otpDigit2 -> findViewById(R.id.otpDigit1)
                R.id.otpDigit3 -> findViewById(R.id.otpDigit2)
                R.id.otpDigit4 -> findViewById(R.id.otpDigit3)
                else -> null
            }
        }
    }

    private fun updateOtpFieldBackground(success: Boolean) {
        val otpDigit1: EditText = findViewById(R.id.otpDigit1)
        val otpDigit2: EditText = findViewById(R.id.otpDigit2)
        val otpDigit3: EditText = findViewById(R.id.otpDigit3)
        val otpDigit4: EditText = findViewById(R.id.otpDigit4)

        val background = if (success) {
            ContextCompat.getDrawable(this, R.drawable.otp_correct_bg)
        } else {
            ContextCompat.getDrawable(this, R.drawable.otp_incorrect_bg)
        }

        otpDigit1.background = background
        otpDigit2.background = background
        otpDigit3.background = background
        otpDigit4.background = background
    }
}
