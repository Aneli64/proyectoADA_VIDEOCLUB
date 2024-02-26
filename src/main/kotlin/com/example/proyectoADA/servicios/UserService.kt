package com.example.proyectoADA.servicios

import com.example.proyectoADA.tablas.Usuario
import com.example.proyectoADA.repositorios.UserRepository
import com.example.proyectoADA.tablas.Pelicula
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {
    @Transactional(readOnly = true)
    fun obtAllUsers(): List<Usuario> {
        return userRepository.findAll()
    }

    fun obtById(id: Int): Usuario? = userRepository.findById(id).orElse(null)

    fun save(usuario: Usuario): Usuario = userRepository.save(usuario)

    fun update(id: Int, newUser: Usuario): Usuario {
        val existeUser = obtById(id)
        if (existeUser != null) {
            newUser.id = id
            return save(newUser)
        }
        throw NoSuchElementException("Pelicula no encontrada")
    }

    fun delete(id: Int) {
        userRepository.deleteById(id)
    }
}
