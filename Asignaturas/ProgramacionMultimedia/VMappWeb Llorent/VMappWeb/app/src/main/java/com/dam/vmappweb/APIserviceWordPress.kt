package com.dam.vmappweb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query




interface APIserviceWordPress{
    @GET("wp/v2/posts")
    suspend fun getPosts(@Query("categories") categoryId:Int): List<Post>
}

object WordpressRetrofitClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://iesvicentemedina.murciaeduca.es/wp-json/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val api: APIserviceWordPress = retrofit.create(APIserviceWordPress::class.java)
}

