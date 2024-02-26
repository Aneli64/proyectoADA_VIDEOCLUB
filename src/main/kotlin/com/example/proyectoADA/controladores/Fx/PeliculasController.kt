package com.example.proyectoADA.controladores.Fx

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextField
import com.example.proyectoADA.tablas.Pelicula
import org.springframework.web.client.RestTemplate


class PeliculasController {

    lateinit var fechaLanzModf: TextField
    lateinit var directorModf: TextField
    lateinit var nombreModf: TextField
    lateinit var idModf: TextField
    lateinit var textoFechaLanz: TextField
    lateinit var textoDirector: TextField
    lateinit var textoNombre: TextField
    lateinit var Modify_film: Button
    lateinit var textoPeliculaAeliminar: TextField
    lateinit var btn_Volver: Button
    lateinit var Add_Film: Button
    lateinit var Delete_Film: Button

    @FXML
    fun deleteFilm(actionEvent: ActionEvent) {
        val id = textoPeliculaAeliminar.text.toIntOrNull()
        val alert = Alert(Alert.AlertType.INFORMATION)

        if (id != null) {
            val restTemplate = RestTemplate()
            val url = "http://localhost:8081/pelicula/$id"
            restTemplate.delete(url)

            alert.title = "Completado"
            alert.headerText = "Pelicula eliminada con exito"
            alert.showAndWait()

        } else {
            alert.title = "Error"
            alert.headerText = "Ingrese un ID válido para eliminar la película"
            alert.showAndWait()
        }
    }

    @FXML
    fun addFilm(actionEvent: ActionEvent) {
        val nombre = textoNombre.text
        val director = textoDirector.text
        val fechLanzamiento = textoFechaLanz.text
        val alert = Alert(Alert.AlertType.INFORMATION)

        if (nombre.isNotEmpty() && director.isNotEmpty() && fechLanzamiento.isNotEmpty()) {
            val restTemplate = RestTemplate()
            val url = "http://localhost:8081/pelicula"

            try {
                // Crear la película con los datos ingresados
                val pelicula = Pelicula(nombre = nombre, director = director, fech_lanz = fechLanzamiento)

                // Enviar la solicitud POST para guardar la película
                restTemplate.postForObject(url, pelicula, Pelicula::class.java)

                alert.title = "Completado"
                alert.headerText = "Película guardada con éxito."
                alert.showAndWait()
            } catch (e: Exception) {
                alert.title = "Error"
                alert.headerText = "Error al guardar la película: ${e.message}"
                alert.showAndWait()
            }
        } else {
            alert.title = "Error"
            alert.headerText = "Ingrese todos los campos para guardar la película."
            alert.showAndWait()
        }
    }

    @FXML
    fun volverAtras(actionEvent: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("/archivos_javaFX/main.fxml"))
        val root: Parent = loader.load()

        // Obtener el controlador del nuevo FXML
        val gestionarUsuariosController = loader.getController<MainController>()

        // Configurar cualquier dato o lógica necesaria en el nuevo controlador

        // Obtener la escena actual y cambiar su contenido
        val scene = btn_Volver.scene
        scene.root = root
    }

    @FXML
    fun modifyFilm(actionEvent: ActionEvent) {
        val id = idModf.text.toIntOrNull()
        val nombre = nombreModf.text
        val director = directorModf.text
        val fechLanzamiento = fechaLanzModf.text
        val alert = Alert(Alert.AlertType.INFORMATION)

        if (id != null && nombre.isNotEmpty() && director.isNotEmpty() && fechLanzamiento.isNotEmpty()) {
            val restTemplate = RestTemplate()
            val url = "http://localhost:8081/pelicula/$id"

            try {
                // Crear la película con los datos modificados
                val peliculaModificada = Pelicula(id = id, nombre = nombre, director = director, fech_lanz = fechLanzamiento)

                // Enviar la solicitud PUT o PATCH para actualizar la película
                restTemplate.put(url, peliculaModificada)

                alert.title = "Completado"
                alert.headerText = "Película modificada con éxito."
                alert.showAndWait()
            } catch (e: Exception) {
                alert.title = "Error"
                alert.headerText = "Error al modificar la película: ${e.message}"
                alert.showAndWait()
            }
        } else {
            alert.title = "Error"
            alert.headerText = "Ingrese un ID válido y todos los campos para modificar la película."
            alert.showAndWait()
        }
    }
}