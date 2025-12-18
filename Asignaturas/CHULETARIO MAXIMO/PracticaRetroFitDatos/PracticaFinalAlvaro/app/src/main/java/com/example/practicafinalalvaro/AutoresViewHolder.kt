package com.example.practicafinalalvaro

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AutoresViewHolder (view: View): RecyclerView.ViewHolder(view) {
    var nombre: TextView
    var botonGuardar: Button
    init{
        nombre=view.findViewById(R.id.tvAutor)
        botonGuardar= view.findViewById(R.id.button)
    }



}