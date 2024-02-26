package com.example.proyectoADA.servicios

import com.example.proyectoADA.tablas.Pelicula
import com.example.proyectoADA.repositorios.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FilmService @Autowired constructor(private val filmRepository: FilmRepository) {

    @Transactional(readOnly = true)
    fun obtAllFilms(): List<Pelicula> {
        return filmRepository.findAll()
    }

    fun obtById(id: Int): Pelicula? = filmRepository.findById(id).orElse(null)

    fun save(pelicula: Pelicula): Pelicula = filmRepository.save(pelicula)

    fun update(id: Int, newFilm: Pelicula): Pelicula {
        val existeFilm = obtById(id)
        if (existeFilm != null) {
            newFilm.id = id
            return save(newFilm)
        }
        throw NoSuchElementException("Pelicula no encontrada")
    }

    fun delete(id: Int) {
        filmRepository.deleteById(id)
    }
}
