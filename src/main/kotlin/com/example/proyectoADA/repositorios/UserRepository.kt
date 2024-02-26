package com.example.proyectoADA.repositorios

import com.example.proyectoADA.tablas.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Usuario, Int>