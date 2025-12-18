package com.example.ejerciciopracticaintermedio.BBDD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LibroEntidad::class], version = 1)
abstract class LibroBBDD : RoomDatabase() {

    abstract fun libroDao(): LibroDAO // Asegúrate de tener la interfaz LibroDAO creada

    companion object {
        @Volatile
        private var INSTANCE: LibroBBDD? = null

        fun getInstance(context: Context): LibroBBDD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibroBBDD::class.java,
                    "biblioteca.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}