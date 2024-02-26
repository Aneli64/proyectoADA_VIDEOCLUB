package com.example.proyectoADA.repositorios

import com.example.proyectoADA.tablas.Pelicula
import org.springframework.data.jpa.repository.JpaRepository

interface FilmRepository : JpaRepository<Pelicula, Int>

