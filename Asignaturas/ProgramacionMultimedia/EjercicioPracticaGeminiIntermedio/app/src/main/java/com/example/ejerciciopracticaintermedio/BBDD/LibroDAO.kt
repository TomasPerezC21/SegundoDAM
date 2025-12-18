package com.example.ejerciciopracticaintermedio.BBDD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LibroDAO {

    // 1. Insertar un libro
    // OnConflictStrategy.REPLACE: Si intentas guardar un libro que ya existe (misma key), lo sobrescribe.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libro: LibroEntidad)

    // 2. Recuperar todos los favoritos
    @Query("SELECT * FROM tabla_libros")
    suspend fun selectAll(): List<LibroEntidad>

    // 3. Borrar todo (útil para el menú de "Papelera")
    @Query("DELETE FROM tabla_libros")
    suspend fun deleteAll()

    // (Extra) Por si te piden borrar solo uno al hacer click largo
    @Delete
    suspend fun delete(libro: LibroEntidad)
}