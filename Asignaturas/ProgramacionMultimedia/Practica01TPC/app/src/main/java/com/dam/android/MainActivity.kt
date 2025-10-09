package com.dam.android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    //Creacion de variables
    private lateinit var et1: EditText

    private lateinit var et2: EditText

    private lateinit var result: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asignacion de las partes del layout
        et1 = findViewById(R.id.Texto1)

        et2 = findViewById(R.id.Texto2)

        result = findViewById(R.id.resultado)

        //Asignación a cada botón
        findViewById<Button>(R.id.botonSuma).setOnClickListener { operar("suma") }
        findViewById<Button>(R.id.botonResta).setOnClickListener { operar("resta") }
        findViewById<Button>(R.id.botonMulti).setOnClickListener { operar("multi") }
        findViewById<Button>(R.id.botonDiv).setOnClickListener { operar("div") }

        //Para quitar el texto al hacer clic
        et1.setOnClickListener { et1.text.clear() }
        et2.setOnClickListener { et2.text.clear() }

    }

    //metodo que realiza las operaciones en base a la variable que envía cada botón
    private fun operar(operacion: String) {

        val numero1 = et1.text.toString().toDouble()
        val numero2 = et2.text.toString().toDouble()
        var resultado: Double


        if (operacion == "suma"){
            resultado = numero1 + numero2
            result.text = ("Resultado: $resultado")
        }

        if (operacion == "resta"){
            resultado = numero1 - numero2
            result.text = ("Resultado: $resultado")
        }

        if (operacion == "multi"){
            resultado = numero1 * numero2
            result.text = ("Resultado: $resultado")
        }

        if (operacion == "div"){
            resultado = numero1 / numero2
            result.text = ("Resultado: $resultado")
        }


    }

}