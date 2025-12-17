package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BBDD.EventoEntidad
import com.example.myapplication.BBDD.MiBaseDatos
import com.example.myapplication.databinding.FragmentListaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListaFragment : Fragment(R.layout.fragment_lista) { // Truco: Layout en constructor

    private lateinit var binding: FragmentListaBinding // Tu XML del fragment
    private lateinit var adapter: EventoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListaBinding.bind(view)

        // 1. Configurar RecyclerView
        adapter = EventoAdapter { eventoPulsado ->
            guardarEnFavoritos(eventoPulsado) // Función que definimos abajo
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        // 2. Llamar a la API
        descargarDatos()
    }

    private fun descargarDatos() {
        lifecycleScope.launch(Dispatchers.IO) { // Hilo secundario
            try {
                val respuesta = WordpressRetrofitClient.api.getPosts()

                withContext(Dispatchers.Main) { // Volvemos al hilo principal
                    adapter.actualizarLista(respuesta)
                }
            } catch (e: Exception) {
                // Manejar error (Log o Toast)
            }
        }
    }

    private fun guardarEnFavoritos(evento: Evento) {
        lifecycleScope.launch(Dispatchers.IO) {
            // AQUI LA CLAVE: Convertir de API -> Entidad BBDD
            val favorito = EventoEntidad(
                id = evento.id,
                name = evento.name,
                city = evento.details.city,
                date = evento.details.date
            )

            // Insertar
            val db = MiBaseDatos.getDatabase(requireContext())
            db.dao().guardar(favorito)
        }
    }
}