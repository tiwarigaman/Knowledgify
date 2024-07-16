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

// Singleton object to handle API calls using OkHttp library
object ApiClient {
    // OkHttpClient instance to manage network requests
    private val client = OkHttpClient()

    // Media type for JSON data
    private val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    // Function to make a POST request
    // Takes a URL, JSON payload, and a callback function as parameters
    fun post(url: String, json: JSONObject, callback: (Boolean, Int, String?) -> Unit) {
        // Create request body with JSON payload
        val body = RequestBody.create(JSON, json.toString())

        // Build the request with the given URL and request body
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        // Execute the request asynchronously
        client.newCall(request).enqueue(object : Callback {
            // Handle failure in making the request
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                // Invoke the callback with success as false and code as 500
                callback(false, 500, null)
            }

            // Handle response from the server
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful) {
                    // Log successful response
                    Log.d("API Response", responseBody.toString())
                    // Invoke the callback with success as true, response code, and response body
                    callback(true, response.code, responseBody)
                } else {
                    // Log error response
                    Log.d("API ResponseE", response.code.toString())
                    // Invoke the callback with success as false, response code, and response body
                    callback(false, response.code, responseBody)
                }
            }
        })
    }
}
