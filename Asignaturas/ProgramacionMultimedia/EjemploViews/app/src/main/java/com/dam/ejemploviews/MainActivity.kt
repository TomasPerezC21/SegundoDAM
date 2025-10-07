package com.dam.ejemploviews

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup

    private lateinit var rbSeleccionado: RadioButton

    private lateinit var botonRG: Button

    private lateinit var sw: MaterialSwitch

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bind
        radioGroup = findViewById(R.id.radioGroup1)
        botonRG = findViewById(R.id.radioButton)
        sw = findViewById(R.id.btnSwitch)

        botonRG.setOnClickListener {
            val id_rb:Int= radioGroup.checkedRadioButtonId
            rbSeleccionado = findViewById(id_rb)
            Log.d("RadioGroup", rbSeleccionado.text.toString())

        }

        sw.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Toggle pulsado a on",
                    Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "deslizador ON")
            } else {
                Toast.makeText(this, "Toggle pulsado a OFF",
                    Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "deslizador OFF")
            }
        }

    }
}