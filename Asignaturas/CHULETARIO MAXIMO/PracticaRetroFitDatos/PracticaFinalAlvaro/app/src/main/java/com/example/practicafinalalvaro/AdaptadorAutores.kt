package com.example.practicafinalalvaro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdaptadorAutores : RecyclerView.Adapter<AutoresViewHolder>{
    private var aut: List<Autores>

    interface OnAutorClickListener {
        fun onGuardarClick(autor: Autores)
    }

    private var listener: OnAutorClickListener? = null

    fun setOnItemClickListener(listener: OnAutorClickListener) {
        this.listener = listener
    }
    constructor(){
        aut=ArrayList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AutoresViewHolder(layoutInflater.inflate(R.layout.fila_autores, parent, false))
    }
    override fun getItemCount(): Int = aut.size
    override fun onBindViewHolder(holder: AutoresViewHolder, position: Int) {
        val item = aut[position]
        holder.nombre.text = item.name
        holder.botonGuardar.setOnClickListener {
            listener?.onGuardarClick(item)

            holder.botonGuardar.text = "Guardado"
    }}
    fun changelist(autores: List<Autores>) {
        aut= autores
    }
}