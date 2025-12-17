package com.example.myapplication

//Lo que viene de internet
data class Evento(
    var id: Int,
    var name: String,
    var details: EventoDetalles //Objeto anidado
)

class EventoDetalles(
    var city: String,
    var date: String
)