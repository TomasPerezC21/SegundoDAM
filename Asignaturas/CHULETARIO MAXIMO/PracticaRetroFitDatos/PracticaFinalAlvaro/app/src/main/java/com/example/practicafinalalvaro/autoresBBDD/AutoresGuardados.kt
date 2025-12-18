package com.example.practicafinalalvaro.autoresBBDD
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_autores")
data class AutoresGuardados(
    @PrimaryKey
    var key: String,
    var birth_date: String,
    var name: String
)

