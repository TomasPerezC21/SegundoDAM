package com.example.practicafinalalvaro.autoresBBDD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AutorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Autores : AutoresGuardados ): Long


    @Query("SELECT * FROM tabla_autores")
    fun selectAll(): List<AutoresGuardados>

}