package com.dam.retrofitautores

import com.google.gson.annotations.SerializedName


class AuthorsResponse(var numFound:Int, @SerializedName("docs") var autores:List<Autores>) {




}


class Autores(var key: String, var birth_date: String, var name: String) {



}