package com.example.proyectoADA.controladores.SpiringBoot

import com.example.proyectoADA.tablas.Pelicula
import com.example.proyectoADA.servicios.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/pelicula")
class PeliculaController @Autowired constructor(private val filmService: FilmService) {

    //http://localhost:8081/pelicula/obtenerPeliculas
    @GetMapping("obtenerPeliculas")
    fun obtAllFilms(): List<Pelicula> {
        return filmService.obtAllFilms()
    }

    //http://localhost:8081/pelicula/{id}
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int) = filmService.obtById(id)

    @PostMapping
    fun guardar(@RequestBody pelicula: Pelicula) = filmService.save(pelicula)

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Int, @RequestBody newFilm: Pelicula) =
        filmService.update(id, newFilm)

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Int) = filmService.delete(id)
}