package com.example.practicafinalalvaro.autoresBBDD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [AutoresGuardados::class], version = 1, exportSchema = false)
abstract class autorsBBDD: RoomDatabase() {

    abstract fun dao (): AutorDAO


    companion object{
        @Volatile

        private var INSTANCE : autorsBBDD? = null
        fun getInstance (context:Context): autorsBBDD {
            if (INSTANCE == null) synchronized (AutoresGuardados:: class){
                INSTANCE = buildRoomDB(context)
            }
            return INSTANCE !!
        }
        private fun buildRoomDB (contexto :Context) =
            Room.databaseBuilder(
                contexto.applicationContext, autorsBBDD:: class.java, "posts.db"
            ).build()

    }





}