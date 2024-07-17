package com.mobile.pdhayi

import android.content.Intent
import android.os.Bundle
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

class SignInNormal : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var googleSignInOptions : GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_normal)
        //google sign in
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        findViewById<TextView>(R.id.googleSignIn).setOnClickListener {
            signIn()
        }

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
                UserRepository.loginUser(applicationContext, json, onSuccess = { responseBody ->
                    runOnUiThread {
                        Toast.makeText(applicationContext, responseBody, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, HomeActivity::class.java))
                        finish()
                    }
                }, onFailure = { code, responseBody ->
                    runOnUiThread {
                        when (code) {
                            404 -> Toast.makeText(applicationContext, "User does not exist, please try to signup", Toast.LENGTH_SHORT).show()
                            401 -> Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(applicationContext, "Server error: $responseBody", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    private fun signIn() {
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent,RC_SIGN_IN)
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==RC_SIGN_IN){
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                val account : GoogleSignInAccount = GoogleSignIn.getSignedInAccountFromIntent(data).result
                if(account != null){
                    Toast.makeText(applicationContext,"${account.displayName} ${account.email}",Toast.LENGTH_SHORT).show()
                }

            }catch (e: ApiException){
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
}