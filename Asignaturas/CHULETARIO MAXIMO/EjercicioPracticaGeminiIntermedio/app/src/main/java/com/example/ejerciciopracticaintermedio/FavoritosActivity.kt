package com.example.ejerciciopracticaintermedio

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejerciciopracticaintermedio.BBDD.LibroBBDD
import com.example.ejerciciopracticaintermedio.BBDD.LibroEntidad
import com.example.ejerciciopracticaintermedio.databinding.ActivityFavoritosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritosBinding
    private lateinit var adapter: FavoritosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        adapter = FavoritosAdapter { libroParaBorrar ->
            borrarLibro(libroParaBorrar)
        }

        binding.rvFavoritos.layoutManager = LinearLayoutManager(this)
        binding.rvFavoritos.adapter = adapter

        // Habilitar la flecha de "Volver" en la Toolbar (Opcional pero recomendado)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Mis Libros Guardados"
    }

    // Usamos onResume para cargar los datos cada vez que la pantalla se muestra
    override fun onResume() {
        super.onResume()
        cargarFavoritos()
    }

    private fun cargarFavoritos() {
        lifecycleScope.launch(Dispatchers.IO) {
            // 1. Pedir datos a la BBDD
            val db = LibroBBDD.getInstance(applicationContext)
            val listaLibros = db.libroDao().selectAll()

            // 2. Mostrar en UI
            withContext(Dispatchers.Main) {
                adapter.actualizarLista(listaLibros)

                // (Opcional) Si quieres mostrar un mensaje si está vacío
                /*
                if (listaLibros.isEmpty()) {
                    Toast.makeText(this@FavoritosActivity, "No tienes favoritos aún", Toast.LENGTH_SHORT).show()
                }
                */
            }
        }
    }

    // Para que la flecha de volver funcione
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    // AÑADE ESTA FUNCIÓN NUEVA
    private fun borrarLibro(libro: LibroEntidad) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = LibroBBDD.getInstance(applicationContext)

            // 1. Borramos el libro
            db.libroDao().delete(libro)

            // 2. Volvemos a cargar la lista para que desaparezca visualmente
            val listaActualizada = db.libroDao().selectAll()

            withContext(Dispatchers.Main) {
                adapter.actualizarLista(listaActualizada)
                Toast.makeText(this@FavoritosActivity, "Libro eliminado", Toast.LENGTH_SHORT).show()
            }
        }
    }

}