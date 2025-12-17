package com.example.practicafinalalvaro

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAutores(@Url url:String): Response<AuthorsResponse>
}

object RetroFitAuthors {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIService = retrofit.create(APIService::class.java)
}