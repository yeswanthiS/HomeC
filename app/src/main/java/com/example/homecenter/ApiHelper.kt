package com.example.homecenter
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ApiHelper(context: Context) {
    private val baseUrl = "https://homec.onrender.com"
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun registerDevice(device: Device, callback: Response.Listener<JSONObject>, errorCallback: Response.ErrorListener) {
        val url = "$baseUrl/register"
        val jsonObject = JSONObject().apply {
            put("deviceId", device.deviceId)
            put("deviceName", device.deviceName)
            put("status", device.status)
            put("location", device.location)
        }

        val request = JsonObjectRequest(Request.Method.POST, url, jsonObject, callback, errorCallback)
        requestQueue.add(request)
    }

    fun getDeviceStatus(deviceId: String, callback: Response.Listener<JSONObject>, errorCallback: Response.ErrorListener) {
        val url = "$baseUrl/status?deviceId=$deviceId"
        val request = JsonObjectRequest(Request.Method.GET, url, null, callback, errorCallback)
        requestQueue.add(request)
    }
}