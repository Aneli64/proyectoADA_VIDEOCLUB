package com.example.proyectoADA.controladores.SpiringBoot

import com.example.proyectoADA.tablas.Usuario
import com.example.proyectoADA.servicios.UserService
import com.example.proyectoADA.tablas.Pelicula
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*



@RestController
@RequestMapping("/usuario")
class UserController @Autowired constructor(private val userService: UserService) {

    //http://localhost:8081/usuario/obtenerUsuarios
    @GetMapping("/obtenerUsuarios")
    fun obtAllUsers(): List<Usuario> {
        return userService.obtAllUsers()
    }

    //http://localhost:8081/usuario/{id}
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int) = userService.obtById(id)

    @PostMapping
    fun guardar(@RequestBody usuario: Usuario) = userService.save(usuario)

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Int, @RequestBody newUser: Usuario) =
        userService.update(id, newUser)

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Int) = userService.delete(id)
}