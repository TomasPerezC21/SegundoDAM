package com.dam.holamundogit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
    private val TAG= "StateChange"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.i(TAG, "estoy en onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "estoy en onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "estoy en onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "estoy en onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "estoy en onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "estoy en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "estoy en onDestroy")
    }

}