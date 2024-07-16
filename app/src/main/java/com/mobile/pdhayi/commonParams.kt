package com.mobile.pdhayi

import android.content.Context
import org.json.JSONObject

object CommonParams {

    fun buildCommonParams(context: Context?): JSONObject {
        val commonParams = JSONObject()
        // Add common parameters here if needed
        // Example: commonParams.put("timezone", Dependencies.getTimeZoneInMinutes().toString())
        // Add other common parameters as required
        return commonParams
    }
}
