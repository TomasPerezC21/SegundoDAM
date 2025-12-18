package com.example.myapplication.BBDD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_favoritos")
data class EventoEntidad(
    @PrimaryKey
    var id: Int,
    var name: String,
    var city: String, // Sacado de details
    var date: String  // Sacado de details
)