package com.dam.widgetsavanzados

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //bindeo
        spinner = findViewById(R.id.spinner)



        //fuente de datos
        //val datos = arrayOf("ele1", "ele2", "ele3", "ele4", "ele5")


        val adaptador = ArrayAdapter.createFromResource(this, R.array.valores_array, android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //bindear spinner a adaptador
        spinner.adapter = adaptador

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val pos = parent.getItemAtPosition(position)


                Toast.makeText(this@MainActivity, "se ha seleccionado $pos", Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }



    }
}