package com.dam.examenrecuperacionandroidbasico2526

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {


    private lateinit var cartaSelec: CardView
    val listaColores = listOf(
        ColorItem("Rojo", "#FF0000"),
        ColorItem("Verde", "#00FF00"),
        ColorItem("Azul", "#0000FF"),
        ColorItem("Amarillo", "#FFFF00"),
        ColorItem("Magenta", "#FF00FF"),
        ColorItem("Cyan", "#00FFFF"),
        ColorItem("Naranja", "#FFA500"),
        ColorItem("Rosa", "#FFC0CB"),
        ColorItem("Morado", "#800080"),
        ColorItem("Marrón", "#8B4513"),
        ColorItem("Gris", "#808080"),
        ColorItem("Negro", "#000000"),
        ColorItem("Blanco", "#FFFFFF"),
        ColorItem("Turquesa", "#40E0D0"),
        ColorItem("Lima", "#BFFF00"),
        ColorItem("Violeta", "#EE82EE"),
        ColorItem("Beige", "#F5F5DC"),
        ColorItem("Oliva", "#808000"),
        ColorItem("Salmon", "#FA8072"),
        ColorItem("Lavanda", "#E6E6FA")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.miRecycler)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = MiAdapter(listaColores)



        return view

    }


}
