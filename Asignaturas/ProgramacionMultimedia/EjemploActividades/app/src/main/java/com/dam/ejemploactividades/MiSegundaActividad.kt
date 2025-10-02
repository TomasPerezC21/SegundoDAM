package com.dam.ejemploactividades

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.content.Intent
class MiSegundaActividad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_segunda_actividad)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var usuarioDesdeActividad = intent.getStringExtra("usuario")
        Log.d("intents", "El usuario es + $usuarioDesdeActividad")

        val data = Intent()
        data.putExtra("stringdevuelta", "he devuelto un valor")
        setResult(RESULT_OK, data)
        finish()
    }
}