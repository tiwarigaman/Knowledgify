package com.mobile.pdhayi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import org.json.JSONObject

class SignUpNormal : AppCompatActivity() {
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_normal)
        googleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        findViewById<TextView>(R.id.googleSignInOther).setOnClickListener {
            signIn()
        }

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
                            Toast.makeText(this@SignUpNormal, responseBody, Toast.LENGTH_SHORT)
                                .show()
                            val intent =
                                Intent(applicationContext, VerificationActivity::class.java).apply {
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
                                Toast.makeText(
                                    applicationContext,
                                    "Email already exists, Please try to login",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Something went wrong",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun signIn() {
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                val account: GoogleSignInAccount =
                    GoogleSignIn.getSignedInAccountFromIntent(data).result
                if (account != null) {
                    Toast.makeText(
                        applicationContext,
                        "${account.displayName} ${account.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    val firstName: String = account.displayName!!.split(" ")[0]
                    val lastName: String = account.displayName!!.split(" ")[1]
                    val json = JSONObject().apply {
                        put("firstName", firstName)
                        put("lastName", lastName)
                        put("email", account.email)
                    }
                    ApiClient.post(ApiUrls.BETA_GOOGLE_SIGN_UP, json) { success, code, responseBody ->
                        runOnUiThread {
                            if (success) {
                                Toast.makeText(this@SignUpNormal, responseBody, Toast.LENGTH_SHORT)
                                    .show()
                                val intent =
                                    Intent(applicationContext, HomeActivity::class.java).apply {
                                        putExtra("email", account.email)
                                        putExtra("password", "password@123")
                                        putExtra("firstName", firstName)
                                        putExtra("lastName", lastName)
                                    }
                                startActivity(intent)
                                finish()
                            } else {
                                Log.d("API ResponseE", code.toString())
                                if (code == 401) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Email already exists, Please try to login",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "Something went wrong",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            } catch (e: ApiException){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}