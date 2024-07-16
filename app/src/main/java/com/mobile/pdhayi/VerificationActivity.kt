package com.mobile.pdhayi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.json.JSONObject

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
            if (otp.length == 4 && email.isNotEmpty() && password.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty()) {
                verifyOtp(otp)
            } else {
                updateOtpFieldBackground(success = false)
                Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifyOtp(otp: String) {
        val json = JSONObject().apply {
            put("firstName", firstName)
            put("lastName", lastName)
            put("email", email)
            put("password", password)
            put("otp", otp)
        }

        UserRepository.verifyUser(this,json,
            onSuccess = {
                updateOtpFieldBackground(success = true)
                runOnUiThread {
                    Toast.makeText(this@VerificationActivity, "OTP Verified!", Toast.LENGTH_SHORT).show()
                }
//                Toast.makeText(this@VerificationActivity, "OTP Verified!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@VerificationActivity, HomeActivity::class.java))
                finish()
            },
            onFailure = { code, message ->
                updateOtpFieldBackground(success = false)
                runOnUiThread {
                    Toast.makeText(this@VerificationActivity, "Incorrect OTP: $message", Toast.LENGTH_SHORT).show()
                }
//                Toast.makeText(this@VerificationActivity, "Incorrect OTP: $message", Toast.LENGTH_SHORT).show()
            }
        )
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
