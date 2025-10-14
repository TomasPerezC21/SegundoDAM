package com.dam.widgetsavanzados2

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderPelicula(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitulo: TextView
        val textViewDirector: TextView

        init {
            textViewTitulo = itemView.findViewById(R.id.filaTitulocd)
            textViewDirector = itemView.findViewById(R.id.filaDirectorcd)
            itemView.setOnClickListener {
                Log.d("listener", "viewholderlistener"+this.layoutPosition)
            }
        }



    fun bind(peli: Pelicula){

        textViewTitulo.text = peli.titulo
        textViewDirector.text = peli.director

    }

}