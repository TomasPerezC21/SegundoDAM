package com.example.ejerciciopracticaintermedio.BBDD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_libros")
data class LibroEntidad(

    @PrimaryKey
    var key: String,
    var title:String,
    var date:Int,
    var author_name: String
)