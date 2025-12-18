package com.example.ejerciciopracticaintermedio

import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService{
    @GET("search.json")
    suspend fun buscarLibros(@Query("q") query: String): RespuestaBusqueda
}

object RetrofitClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIService = retrofit.create(APIService::class.java)
}

