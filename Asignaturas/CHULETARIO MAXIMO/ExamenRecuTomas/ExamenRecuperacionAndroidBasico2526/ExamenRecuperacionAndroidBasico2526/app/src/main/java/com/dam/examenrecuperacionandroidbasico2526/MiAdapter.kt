package com.dam.examenrecuperacionandroidbasico2526

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView

class MiAdapter(private val lista: List<ColorItem>) : RecyclerView.Adapter<MiAdapter.MiViewHolder>() {
    class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreColor: TextView = itemView.findViewById(R.id.nombreColor)
        val codeColor: TextView = itemView.findViewById(R.id.codeColor)
        val card: CardView = itemView.findViewById(R.id.miCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_fragment, parent, false)
        return MiViewHolder(view)
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.nombreColor.text = lista[position].nombre
        holder.codeColor.text = lista[position].hex
        holder.card.setBackgroundColor(lista[position].hex.toColorInt())

        holder.card.setOnClickListener {
            val intent = Intent(holder.itemView.context, ColorActivity::class.java)
            intent.putExtra("Nombre", holder.nombreColor.text)
                intent.putExtra("Code", holder.codeColor.text)

            holder.itemView.context.startActivity(intent)
        }



    }
    override fun getItemCount() = lista.size


}