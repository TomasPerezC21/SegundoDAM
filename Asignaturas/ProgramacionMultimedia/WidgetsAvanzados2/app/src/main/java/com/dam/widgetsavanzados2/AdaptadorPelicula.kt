package com.dam.widgetsavanzados2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPeliculas(datosPelis: MutableList<Pelicula>) :
    RecyclerView.Adapter<ViewHolderPelicula>() {
    private lateinit var peliculas: MutableList<Pelicula>


    init {
        this.peliculas = datosPelis
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPelicula {
        // inflamos la vista (fila_pelicula.xml)
        val vista: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fila_pelicula, parent, false)
        // Crear el viewholder a partir de esta vista.
        val viewHolder = ViewHolderPelicula(vista)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return this.peliculas.size
    }


    override fun onBindViewHolder(holder: ViewHolderPelicula, position: Int) {
        // Dibujar la fila de la peli con los datos de la peli actualmente solicitada según la variable position
        val peli: Pelicula = this.peliculas[position]
        holder.bind(peli)
        Log.d("recycler", position.toString())
        holder.textViewTitulo.setOnClickListener {
            Log.d("recycler", peli.titulo)
        }
    }
}
