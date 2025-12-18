package com.dam.vmappweb.bbdd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDAO {
    @Query("SELECT * FROM PostFavoritos")
    fun selectAll(): List<PostFavoritos>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostFavoritos): Long

    @Delete
    fun delete(post: PostFavoritos)
}