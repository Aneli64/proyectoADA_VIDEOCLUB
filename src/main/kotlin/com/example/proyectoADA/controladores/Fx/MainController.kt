package com.example.proyectoADA.controladores.Fx


import com.example.proyectoADA.tablas.Pelicula
import com.example.proyectoADA.tablas.Usuario
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.control.Button
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class MainController {

    lateinit var inf_usuarios: Button
    lateinit var btnGestUsers: Button
    lateinit var inf_peliculas: Button
    lateinit var btnGestFilms: Button

    @FXML
    fun gestionar_peliculas(actionEvent: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("/archivos_javaFX/peliculas.fxml"))
        val root: Parent = loader.load()

        // Obtener el controlador del nuevo FXML
        val gestionarPeliculasController = loader.getController<PeliculasController>()

        // Configurar cualquier dato o lógica necesaria en el nuevo controlador

        // Obtener la escena actual y cambiar su contenido
        val scene = btnGestFilms.scene
        scene.root = root
    }


    @FXML
    fun inf_peliculas(actionEvent: ActionEvent) {
        val restTemplate = RestTemplate()
        val url = "http://localhost:8081/pelicula/obtenerPeliculas"

        val responseType = object : ParameterizedTypeReference<List<Pelicula>>() {}
        val response: ResponseEntity<List<Pelicula>> = restTemplate.exchange(url, HttpMethod.GET, null, responseType)

        val peliculas: List<Pelicula> = response.body ?: emptyList()

        // Procesar las películas obtenidas, puedes mostrarlas en una tabla o en un cuadro de diálogo, etc.
        mostrarPeliculas(peliculas)
    }

    private fun mostrarPeliculas(peliculas: List<Pelicula>) {
        // Implementa la lógica para mostrar las películas en tu interfaz gráfica
        // Puedes usar una tabla u otro componente de JavaFX
        // Por ejemplo, mostrar las películas en un cuadro de diálogo
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Películas"
        alert.headerText = "Lista de Películas"

        if (peliculas.isNotEmpty()) {
            val peliculasStr = peliculas.joinToString("\n") {  "${it.id} ||| ${it.nombre} || ${it.director} || ${it.fech_lanz}" }
            alert.contentText = peliculasStr
        } else {
            alert.contentText = "Sin peliculas disponibles"
        }

        alert.showAndWait()
    }

    @FXML
    fun gestionar_usuarios(actionEvent: ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("/archivos_javaFX/usuarios.fxml"))
        val root: Parent = loader.load()

        // Obtener el controlador del nuevo FXML
        val gestionarUsuariosController = loader.getController<UsuariosController>()

        // Configurar cualquier dato o lógica necesaria en el nuevo controlador

        // Obtener la escena actual y cambiar su contenido
        val scene = btnGestFilms.scene
        scene.root = root
    }

    @FXML
    fun inf_usuarios(actionEvent: ActionEvent) {
        val restTemplate = RestTemplate()
        val url = "http://localhost:8081/usuario/obtenerUsuarios"

        val responseType = object : ParameterizedTypeReference<List<Usuario>>() {}
        val response: ResponseEntity<List<Usuario>> = restTemplate.exchange(url, HttpMethod.GET, null, responseType)

        val usuarios: List<Usuario> = response.body ?: emptyList()

        // Procesar las películas obtenidas, puedes mostrarlas en una tabla o en un cuadro de diálogo, etc.
        mostrarUsuarios(usuarios)
    }

    private fun mostrarUsuarios(usuarios: List<Usuario>) {
        // Implementa la lógica para mostrar las películas en tu interfaz gráfica
        // Puedes usar una tabla u otro componente de JavaFX
        // Por ejemplo, mostrar las películas en un cuadro de diálogo
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Usuarios"
        alert.headerText = "Usuarios del videoclub"

        if (usuarios.isNotEmpty()) {
            val usuariosStr = usuarios.joinToString("\n") { "${it.id} || ${it.nombre}, ${it.apellido}, ${it.direccion}" }
            alert.contentText = usuariosStr
        } else {
            alert.contentText = "Sin usuarios registrados"
        }

        alert.showAndWait()
    }
}
