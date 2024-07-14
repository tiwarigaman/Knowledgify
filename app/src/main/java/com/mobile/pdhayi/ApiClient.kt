package com.mobile.pdhayi

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

object ApiClient {
    private val client = OkHttpClient()
    private val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    fun post(url: String, json: JSONObject, callback: (Boolean, Int, String?) -> Unit) {
        val body = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(false, 500, null) // Pass 500 as code when there's a failure
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful) {
                    Log.d("API Response", responseBody.toString())
                    callback(true, response.code, responseBody)
                } else {
                    Log.d("API ResponseE", response.code.toString())
                    callback(false, response.code, responseBody)
                }
            }
        })
    }
}
