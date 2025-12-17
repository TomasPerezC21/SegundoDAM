package com.dam.vmappweb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.vmappweb.bbdd.PostBBDD
import com.dam.vmappweb.databinding.ActivityFavoritosBinding
import com.dam.vmappweb.databinding.ActivitySwitchFavoritosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SwitchFavoritos : AppCompatActivity() {

    private lateinit var binding: ActivitySwitchFavoritosBinding
    private lateinit var adapter: SwitchAdapter // Usamos tu adaptador nuevo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwitchFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Configurar RecyclerView
        adapter = SwitchAdapter()
        binding.rvFavoritos.layoutManager = LinearLayoutManager(this) // Asegúrate que el ID en XML sea rvFavoritos
        binding.rvFavoritos.adapter = adapter

        // 2. Lógica del Switch
        // Asegúrate que el ID en tu activity_favoritos.xml sea 'switchMostrar' (o cámbialo aquí)
        binding.switchMostrar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cargarFavoritosDesdeBBDD()
            } else {
                // Si apaga el switch, limpiamos la lista
                adapter.actualizarLista(emptyList())
            }
        }
    }

    private fun cargarFavoritosDesdeBBDD() {
        lifecycleScope.launch(Dispatchers.IO) {
            // Instanciamos la BBDD usando tu clase 'PostBBDD'
            val database = PostBBDD.getInstance(applicationContext)

            // Llamamos al DAO (asumo que el método se llama selectAll o similar en PostDAO)
            val listaFavoritos = database.favDAO().selectAll()

            withContext(Dispatchers.Main) {
                // Pasamos la lista al adaptador
                adapter.actualizarLista(listaFavoritos)
            }
        }
    }
}