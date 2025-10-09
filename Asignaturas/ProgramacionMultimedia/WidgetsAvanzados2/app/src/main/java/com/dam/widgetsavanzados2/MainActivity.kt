package com.dam.widgetsavanzados2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewPeliculas: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //bindeo
        recyclerViewPeliculas = findViewById(R.id.recyclerViewPeliculas)

        recyclerViewPeliculas.layoutManager = LinearLayoutManager(this)

        recyclerViewPeliculas.addItemDecoration(
            DividerItemDecoration(
            this@MainActivity ,
            LinearLayoutManager.VERTICAL)
        )

        val peliculas= ArrayList<Pelicula>()
        peliculas.add(Pelicula("Star Wars", "George Lucas"))
        peliculas.add(Pelicula("Thor", "Taika Waititi"))
        peliculas.add(Pelicula ("Iron Man", "Jodatos_pelisn Favreau"))
        peliculas.add(Pelicula("Los vengadores", "James Gunn"))
        peliculas.add(Pelicula("Los juegos del hambre", "Gary Ross"))
        peliculas.add(Pelicula ("Oppenheimer", "Christopher Nolan"))
        peliculas.add(Pelicula("Saw", "James Wan"))
        peliculas.add(Pelicula("Dune", "Denis Villeneuve"))
        peliculas.add(Pelicula ("El padrino", "Francis Ford Coppola"))
        peliculas.add(Pelicula("El lobo de Wall Street", "Martin Scorsese"))
        peliculas.add(Pelicula("El exorcista", "Francis Ford Coppola"))
        peliculas.add(Pelicula ("The Batman", "Matt Reeves"))

        val adaptadorRecyclerView = AdaptadorPeliculas(peliculas)



        recyclerViewPeliculas.setAdapter(adaptadorRecyclerView)


    }
}