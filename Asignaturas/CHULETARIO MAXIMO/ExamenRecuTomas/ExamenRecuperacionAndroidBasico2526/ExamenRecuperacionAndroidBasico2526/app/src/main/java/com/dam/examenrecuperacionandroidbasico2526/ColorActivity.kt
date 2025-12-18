package com.dam.examenrecuperacionandroidbasico2526

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.toColorInt

class ColorActivity : AppCompatActivity() {

    private lateinit var color: TextView
    private lateinit var code: TextView

    private lateinit var milinear: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_color)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        color = findViewById(R.id.color)
        code = findViewById(R.id.code)
        milinear = findViewById(R.id.linear)

        val nombreColor = intent.getStringExtra("Nombre")
        val codeColor = intent.getStringExtra("Code") ?:""
        color.text = nombreColor
        code.text = codeColor


        cambiarColorFonDo(codeColor)

    }

    private fun cambiarColorFonDo( hex: String  ) {
        val container = findViewById<ConstraintLayout>(R.id.main)
        try {
            container.setBackgroundColor(hex.toColorInt())
        } catch (e: Exception) {
            container.setBackgroundColor(Color.WHITE)
        }
    }
}