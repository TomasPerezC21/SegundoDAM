package com.example.ejerciciopracticaintermedio

data class Libro(
        var key: String,
        var title: String,
        var first_publish_year: Int,
        var author_name: List<String>?
)