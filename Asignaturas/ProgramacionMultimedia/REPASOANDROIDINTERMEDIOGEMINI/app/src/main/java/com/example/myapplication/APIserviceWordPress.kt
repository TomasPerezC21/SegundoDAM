package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query




interface APIserviceWordPress{
    @GET("events") // el endpoint del examen
    suspend fun getPosts(): List<Evento>
}

object WordpressRetrofitClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.ficticia.com/") //La URL base del examen
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIserviceWordPress = retrofit.create(APIserviceWordPress::class.java)
}

