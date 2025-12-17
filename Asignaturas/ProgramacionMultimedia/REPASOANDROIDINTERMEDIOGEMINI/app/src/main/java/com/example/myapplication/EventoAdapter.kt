package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemEventoBinding

class EventoAdapter(
    // Pasamos la función de click en el constructor (Lambda)
    private val onClick: (Evento) -> Unit
) : RecyclerView.Adapter<EventoAdapter.ViewHolder>() {

    private var lista = listOf<Evento>()

    fun actualizarLista(nuevaLista: List<Evento>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        // Poner datos en pantalla
        holder.binding.tvNombre.text = item.name
        holder.binding.tvCiudad.text = item.details.city // Acceso al anidado

        // Gestionar el click
        holder.itemView.setOnClickListener {
            onClick(item) // Ejecutamos la lambda
        }
    }

    override fun getItemCount() = lista.size

    class ViewHolder(val binding: ItemEventoBinding) : RecyclerView.ViewHolder(binding.root)
}