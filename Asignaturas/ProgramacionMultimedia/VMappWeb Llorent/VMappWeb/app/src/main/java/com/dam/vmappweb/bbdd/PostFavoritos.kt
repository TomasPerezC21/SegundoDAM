package com.dam.vmappweb.bbdd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostFavoritos")
data class PostFavoritos(

    @PrimaryKey
    var id: Int,
    var date: String,
    var link: String,
    var title:String,
    var content:String


)



