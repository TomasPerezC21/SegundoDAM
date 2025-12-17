package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // EL CÓDIGO MÁGICO
        if (savedInstanceState == null) { // Solo la primera vez (para no recargar al girar pantalla)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ListaFragment()) // <-- Aquí pones tu Fragment
                .commit()
        }
    }
}