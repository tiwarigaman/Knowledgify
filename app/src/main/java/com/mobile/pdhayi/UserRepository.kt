package com.mobile.pdhayi

import android.content.Context
import android.util.Log
import org.json.JSONObject

object UserRepository {

    private const val BASE_URL = "https://knowledgify-backend.onrender.com/api"

    fun registerUser(context: Context?, json: JSONObject, onSuccess: (String) -> Unit, onFailure: (String, Int) -> Unit) {
        val commonParams = CommonParams.buildCommonParams(context)
        json.put("commonParams", commonParams)

        val url = BASE_URL + ApiUrls.REGISTER
        ApiClient.post(url, json) { success, code, response ->
            if (success) {
                onSuccess(response ?: "")
            } else {
                onFailure("Registration failed", code)
            }
        }
    }
    fun verifyUser(context: Context?, json: JSONObject, onSuccess: (String) -> Unit, onFailure: (String, Int) -> Unit) {
        val commonParams = CommonParams.buildCommonParams(context)
        json.put("commonParams", commonParams)
        val url = BASE_URL + ApiUrls.VERIFY
        ApiClient.post(url, json) { success, code, response ->
            if (success) {
                onSuccess(response ?: "")
            } else {
                onFailure("Verification failed", code)
                Log.e("UserRepository", "Verification failed. Error code: $code")
            }
        }
    }

    fun googleSignUp(
        context: Context,
        json: JSONObject,
        onSuccess: (String) -> Unit,
        onFailure: (Int, String) -> Unit
    ) {
        ApiClient.post(ApiUrls.BETA_GOOGLE_SIGN_UP, json) { success, code, responseBody ->
            if (success) {
                onSuccess.invoke(responseBody ?: "")
            } else {
                onFailure.invoke(code, responseBody ?: "Unknown error")
                Log.e("UserRepository", "Google sign-up failed. Error code: $code")
            }
        }
    }


}
