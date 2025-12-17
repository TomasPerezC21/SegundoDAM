package com.example.ejerciciopracticaintermedio


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciopracticaintermedio.BBDD.LibroEntidad
import com.example.ejerciciopracticaintermedio.databinding.FilaLibroBinding

class FavoritosAdapter(

    //Funcion que recibe el libro pulsado
    private val onLibroClick: (LibroEntidad) -> Unit) : RecyclerView.Adapter<FavoritosAdapter.FavViewHolder>() {

    // Lista de ENTIDADES (BBDD)
    private var lista = listOf<LibroEntidad>()

    fun actualizarLista(nuevaLista: List<LibroEntidad>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        // Reutilizamos el mismo diseño XML 'item_libro.xml'
        val binding = FilaLibroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val libro = lista[position]

        holder.binding.tituloLibroTV.text = libro.title
        holder.binding.fechaTV.text = "Año: ${libro.date}"
        holder.binding.autorTV.text = libro.author_name // Aquí ya es un String directo

        holder.itemView.setOnClickListener {
            // Al pulsar, ejecutamos la función que nos pasa la Activity
            onLibroClick(libro)
        }

    }

    override fun getItemCount() = lista.size

    class FavViewHolder(val binding: FilaLibroBinding) : RecyclerView.ViewHolder(binding.root)
}