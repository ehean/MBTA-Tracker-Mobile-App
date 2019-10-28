package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager;
import android.content.Context;
import android.os.Build;
import android.app.KeyguardManager;
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.VolleyError

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.responseText)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
       // val url = "https://www.google.com"
        val url = "https://httpbin.org/get"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response}"
            },
            Response.ErrorListener { error ->
                textView.text = error.message
            }
        )

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    /*** Called when the user taps the Send button ***/
    fun sendMessage(view: View) {
        // Do something in response button
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}

/**
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
setShowWhenLocked(true)
setTurnScreenOn(true)
val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
keyguardManager.requestDismissKeyguard(this, null)
} else {
this.window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
}
 */
