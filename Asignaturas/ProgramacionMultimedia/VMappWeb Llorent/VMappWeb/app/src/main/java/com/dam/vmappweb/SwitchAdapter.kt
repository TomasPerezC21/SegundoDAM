package com.dam.vmappweb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
// IMPORTANTE: Importamos la entidad de la BBDD
import com.dam.vmappweb.bbdd.PostFavoritos
// Asumo que usarás el diseño de fila que veo en tu captura: fila_post.xml
import com.dam.vmappweb.databinding.FilaPostBinding

class SwitchAdapter : RecyclerView.Adapter<SwitchAdapter.SwitchViewHolder>() {

    // 1. Usamos la lista de la BBDD (PostFavoritos), no la de la API
    private var lista: List<PostFavoritos> = emptyList()

    fun actualizarLista(nuevaLista: List<PostFavoritos>) {
        this.lista = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchViewHolder {
        val binding = FilaPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SwitchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SwitchViewHolder, position: Int) {
        val item = lista[position]

        // 2. Mapeamos los datos de la BBDD a la vista
        // (Ajusta los nombres tvTitulo/tvContenido según tu XML fila_post.xml)
        holder.binding.tvPostTitulo.text = item.title
        holder.binding.tvPostFecha.text = item.date
        holder.binding.tvPostExtracto.text = item.content

    }

    override fun getItemCount() = lista.size

    // ViewHolder interno
    class SwitchViewHolder(val binding: FilaPostBinding) : RecyclerView.ViewHolder(binding.root)
}