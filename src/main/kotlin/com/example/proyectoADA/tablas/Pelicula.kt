package com.example.proyectoADA.tablas

import jakarta.persistence.*

@Entity
@Table(name = "pelicula")
data class Pelicula(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var nombre: String,
    var director: String,
    val fech_lanz: String
)