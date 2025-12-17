package com.example.ejerciciopracticaintermedio

data class RespuestaBusqueda(
    var numFound: Int,
    var docs: List<Libro>
)
