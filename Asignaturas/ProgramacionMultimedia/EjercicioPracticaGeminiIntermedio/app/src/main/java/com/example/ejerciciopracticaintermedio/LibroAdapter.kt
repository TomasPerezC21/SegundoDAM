package com.example.ejerciciopracticaintermedio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciopracticaintermedio.databinding.FilaLibroBinding

class LibroAdapter(
    // Pasamos una función lambda para manejar el click (nos servirá para guardar en BBDD luego)
    private val onClick: (Libro) -> Unit
) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    // Nuestra lista de datos (empieza vacía)
    private var lista = listOf<Libro>()

    fun actualizarLista(nuevaLista: List<Libro>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        // Inflamos el XML que acabamos de crear (item_libro.xml)
        val binding = FilaLibroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = lista[position]

        // 1. Poner Título
        holder.binding.tituloLibroTV.text = libro.title

        // 2. Poner Año (Convertimos el Int a String)
        holder.binding.fechaTV.text = "Año: ${libro.first_publish_year}"

        // 3. Poner Autor (¡OJO! Es una lista, hay que sacar el primero)
        // La lógica es: "Si la lista no es nula Y no está vacía, coge el primero. Si no, pon 'Anónimo'"
        val primerAutor = libro.author_name?.firstOrNull() ?: "Anónimo"
        holder.binding.autorTV.text = primerAutor

        // 4. Configurar el Click
        holder.itemView.setOnClickListener {
            onClick(libro) // Llamamos a la Activity avisando del click
        }
    }

    override fun getItemCount() = lista.size

    class LibroViewHolder(val binding: FilaLibroBinding) : RecyclerView.ViewHolder(binding.root)
}