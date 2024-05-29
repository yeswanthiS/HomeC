package com.example.homecenter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var apiHelper: ApiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiHelper = ApiHelper(this)

        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            val deviceId = findViewById<EditText>(R.id.deviceId).text.toString()
            val deviceName = findViewById<EditText>(R.id.deviceName).text.toString()
            val status = findViewById<EditText>(R.id.status).text.toString()
            val location = findViewById<EditText>(R.id.location).text.toString()

            val device = Device(deviceId, deviceName, status, location)
            apiHelper.registerDevice(device,
                Response.Listener<JSONObject> { response ->
                    Toast.makeText(this, "Device registered: $response", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                })
        }

        val getStatusButton: Button = findViewById(R.id.getStatusButton)
        getStatusButton.setOnClickListener {
            val deviceId = findViewById<EditText>(R.id.deviceId).text.toString()
            apiHelper.getDeviceStatus(deviceId,
                Response.Listener<JSONObject> { response ->
                    Toast.makeText(this, "Device status: ${response.getString("status")}", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                })
        }
    }
}