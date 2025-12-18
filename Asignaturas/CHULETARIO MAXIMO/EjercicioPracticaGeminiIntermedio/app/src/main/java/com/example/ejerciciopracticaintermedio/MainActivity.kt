package com.example.ejerciciopracticaintermedio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejerciciopracticaintermedio.BBDD.LibroBBDD
import com.example.ejerciciopracticaintermedio.BBDD.LibroEntidad
import com.example.ejerciciopracticaintermedio.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: LibroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)//Inflar la vista con el binding (xml)

        setContentView(binding.root)//Importante cambiar esto

        setupRecyclerView()
        setupListeners()

        setSupportActionBar(binding.miToolbar)


    }

    //FUNCIONALIDAD DE LA TOOLBAR
    // 1. Inflar el menú (Dibujarlo)
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    // 2. Gestionar el Click
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favoritos -> {
                // Navegamos a la pantalla de Favoritos
                startActivity(Intent(this, FavoritosActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        // PUNTO EXAMEN 1: Inicializar el Adapter con la LAMBDA de click
        adapter = LibroAdapter { libroPulsado ->
            guardarEnFavoritos(libroPulsado)
        }

        binding.miRecycler.layoutManager = LinearLayoutManager(this)
        binding.miRecycler.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnBuscar.setOnClickListener {
            val textoBusqueda = binding.busquedaET.text.toString()
            if (textoBusqueda.isNotEmpty()) {
                buscarEnInternet(textoBusqueda)
            } else {
                Toast.makeText(this, "Escribe algo...", Toast.LENGTH_SHORT).show()
            }
        }

        // (Opcional) Si tuvieras un botón para ir a favoritos
        /*
        binding.btnIrFavoritos.setOnClickListener {
            startActivity(Intent(this, FavoritosActivity::class.java))
        }
        */
    }

    private fun buscarEnInternet(query: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Llamada real a la API
                val respuesta = RetrofitClient.api.buscarLibros(query)

                withContext(Dispatchers.Main) {

                    // PUNTO EXAMEN 3: Pasar la lista 'docs' al adaptador
                    // Si respuesta.docs es null (porque no hay resultados), pasamos lista vacía
                    adapter.actualizarLista(respuesta.docs ?: emptyList())

                    if (respuesta.docs.isNullOrEmpty()) {
                        Toast.makeText(this@MainActivity, "No se encontraron libros", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // PUNTO EXAMEN 4: Mapeo de API a BBDD (Guardar)
    private fun guardarEnFavoritos(libro: Libro) {
        lifecycleScope.launch(Dispatchers.IO) {

            // ⚠️ TRUCO DEL ALMENDRUCO: Transformar datos
            // Convertimos el objeto de internet (que tiene lista de autores)
            // al objeto de BBDD (que tiene un solo String de autor)

            val libroParaGuardar = LibroEntidad(
                key = libro.key,
                title = libro.title,
                date = libro.first_publish_year,
                // Lógica de seguridad: si es nulo, guardamos "Anónimo"
                author_name = libro.author_name?.firstOrNull() ?: "Anónimo"
            )

            // Insertar en BBDD (Asegúrate de tener creada tu clase LibroBBDD)
            val db = LibroBBDD.getInstance(applicationContext)
            db.libroDao().insert(libroParaGuardar)

            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Libro guardado en View/ToolWindows/AppInspection", Toast.LENGTH_SHORT).show()
            }
        }
    }







}

