package com.dam.widgetsavanzados2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderPelicula(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitulo: TextView
        val textViewDirector: TextView

        init {
            textViewTitulo = itemView.findViewById(R.id.filaTitulo)
            textViewDirector = itemView.findViewById(R.id.filaDirector)

        }



}