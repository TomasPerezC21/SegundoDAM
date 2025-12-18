package com.example.practicafinalalvaro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context // <--- NECESARIO para getSystemService
import android.view.inputmethod.InputMethodManager // <--- NECESARIO para el error rojo
import androidx.appcompat.widget.SearchView
import com.example.practicafinalalvaro.autoresBBDD.AutoresGuardados
import com.example.practicafinalalvaro.autoresBBDD.autorsBBDD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var recy: RecyclerView
    val adaptadorRecyclerView = AdaptadorAutores()
    private lateinit var buscador: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //bind
        buscador=findViewById(R.id.svAutores)
        recy = findViewById(R.id.rvAutores)
        recy.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL, false
        )

        recy.adapter = adaptadorRecyclerView

        adaptadorRecyclerView.setOnItemClickListener(object : AdaptadorAutores.OnAutorClickListener {
            override fun onGuardarClick(autor: Autores) {

                // LANZAMOS CORRUTINA PARA GUARDAR
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = autorsBBDD.getInstance(applicationContext)

                    // CONVERTIMOS EL AUTOR DE LA API AL AUTOR DE LA BBDD
                    val autorParaGuardar = AutoresGuardados(
                        key = autor.key,
                        name = autor.name,
                        birth_date = autor.birth_date ?: "Desconocida" // Por si viene null
                    )

                    // GUARDAMOS
                    db.dao().insert(autorParaGuardar)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Autor Guardado!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        buscador.setOnQueryTextListener(this)


    }




    private fun busquedaAutores(query:String){
        lifecycleScope.launch(Dispatchers.IO) {
            val call = RetroFitAuthors.api.getAutores("/search/authors.json?q=$query")

            val autoresAPI = call.body()
            if(call.isSuccessful){
                val autores = autoresAPI?. autores ?: emptyList()
                withContext(Dispatchers.Main) {
                    adaptadorRecyclerView.changelist(autores)
                    adaptadorRecyclerView.notifyDataSetChanged()
                }
            }else{
                Toast.makeText(applicationContext, "error API", Toast.LENGTH_SHORT).show()
            }}
}

    override fun onQueryTextChange(newText: String?): Boolean {
        return true    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if(!query.isNullOrEmpty()){
            busquedaAutores(query.lowercase())
        }
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.buscador.windowToken, 0)
        return true
    }
}