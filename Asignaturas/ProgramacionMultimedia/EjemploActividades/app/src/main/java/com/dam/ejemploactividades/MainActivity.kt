package com.dam.ejemploactividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    private lateinit var boton: Button
    private lateinit var navegador: Button


    private lateinit var startForResult: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Bindeos de botones
        boton = findViewById(R.id.boton)
        navegador = findViewById(R.id.navegador)


        //Acciones de los botones
        boton.setOnClickListener {

            val intent = Intent(this, MiSegundaActividad::class.java)
            intent.putExtra("usuario", "kalewi")
            startForResult.launch(intent)

        }

        navegador.setOnClickListener {
            val browserIntent = Intent (Intent.ACTION_VIEW, "https://www.xataka.com".toUri())
            if (browserIntent.resolveActivity(packageManager) != null){
                startActivity(browserIntent)
            }
        }

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data = result.data
                if (data != null) {
                    val returnString = data.extras?.getString("stringdevuelta")
                    Log.d("intent", "Resultado devuelto: $returnString")
                }
            }
        }

    }
}