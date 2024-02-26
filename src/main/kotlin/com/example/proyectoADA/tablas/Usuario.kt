package com.example.proyectoADA.tablas


import jakarta.persistence.*


@Entity
@Table(name = "usuario")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var nombre: String,
    var apellido: String,
    var direccion: String
    )