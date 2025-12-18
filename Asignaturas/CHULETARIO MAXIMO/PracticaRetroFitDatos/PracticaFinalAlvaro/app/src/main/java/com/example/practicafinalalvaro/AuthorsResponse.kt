package com.example.practicafinalalvaro

import com.google.gson.annotations.SerializedName

data class AuthorsResponse (var numFound: Int, @SerializedName("docs") var autores:
List<Autores>){
}