package com.example.myapplication.BBDD

import android.content.Context
import androidx.room.*


@Dao
interface EventoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(evento: EventoEntidad) // Fíjate en el 'suspend'

    @Query("SELECT * FROM tabla_favoritos")
    suspend fun obtenerTodos(): List<EventoEntidad>
}

@Database(entities = [EventoEntidad::class], version = 1)
abstract class MiBaseDatos : RoomDatabase() {
    abstract fun dao(): EventoDao

    companion object {
        // Singleton estándar
        @Volatile private var INSTANCE: MiBaseDatos? = null

        fun getDatabase(context: Context): MiBaseDatos {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MiBaseDatos::class.java,
                    "mi_base_datos"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}