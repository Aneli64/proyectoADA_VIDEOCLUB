package com.example.proyectoADA.controladores.Fx

import com.example.proyectoADA.tablas.Usuario
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextField
import org.springframework.web.client.RestTemplate

class UsuariosController {

    lateinit var direccionModfUser: TextField
    lateinit var apellidoModfUser: TextField
    lateinit var nombreModfUser: TextField
    lateinit var textoDireccion: TextField
    lateinit var textoApellido: TextField
    lateinit var textoNombreUsuario: TextField
    lateinit var Modify_user: Button
    lateinit var textoUsuarioAeliminar: TextField
    lateinit var btn_Volver: Button
    lateinit var Add_User: Button
    lateinit var Delete_User: Button
    lateinit var idModfUser: TextField

    @FXML
    fun deleteUser(actionEvent: ActionEvent) {
        val id = textoUsuarioAeliminar.text.toIntOrNull()
        val alert = Alert(Alert.AlertType.INFORMATION)

        if (id != null) {
            val restTemplate = RestTemplate()
            val url = "http://localhost:8081/usuario/$id"
            restTemplate.delete(url)

            alert.title = "Completado"
            alert.headerText = "Usuario eliminado con exito"
            alert.showAndWait()

        } else {
            alert.title = "Error"
            alert.headerText = "Ingrese un ID válido para eliminar el usuario"
            alert.showAndWait()
        }
    }

    @FXML
    fun addUser(actionEvent: ActionEvent) {
        val nombre = textoNombreUsuario.text
        val apellido = textoApellido.text
        val direccion = textoDireccion.text
        val alert = Alert(Alert.AlertType.INFORMATION)

        if (nombre.isNotEmpty() && apellido.isNotEmpty() && direccion.isNotEmpty()) {
            val restTemplate = RestTemplate()
            val url = "http://localhost:8081/usuario"

            try {
                // Crear el usuario con los datos ingresados
                val usuario = Usuario(nombre = nombre, apellido = apellido, direccion = direccion)

                // Enviar la solicitud POST para guardar el usuario
                restTemplate.postForObject(url, usuario, Usuario::class.java)

                alert.title = "Completado"
                alert.headerText = "Usuario guardado con éxito."
                alert.showAndWait()
            } catch (e: Exception) {
                alert.title = "Error"
                alert.headerText = "Error al guardar el usuario: ${e.message}"
                alert.showAndWait()
            }
        } else {
            alert.title = "Error"
            alert.headerText = "Ingrese todos los campos para guardar el usuario."
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
    fun modifyUser(actionEvent: ActionEvent) {
        val id = idModfUser.text.toIntOrNull()
        val nombre = nombreModfUser.text
        val apellido = apellidoModfUser.text
        val direccion = direccionModfUser.text
        val alert = Alert(Alert.AlertType.INFORMATION)

        if (id != null && nombre.isNotEmpty() && apellido.isNotEmpty() && direccion.isNotEmpty()) {
            val restTemplate = RestTemplate()
            val url = "http://localhost:8081/usuario/$id"

            try {
                // Crear el usuario con los datos modificados
                val usuarioModificado = Usuario(id = id, nombre = nombre, apellido = apellido, direccion = direccion)

                // Enviar la solicitud PUT o PATCH para actualizar el usuario
                restTemplate.put(url, usuarioModificado)

                alert.title = "Completado"
                alert.headerText = "usuario modificada con éxito."
                alert.showAndWait()
            } catch (e: Exception) {
                alert.title = "Error"
                alert.headerText = "Error al modificar el usuario: ${e.message}"
                alert.showAndWait()
            }
        } else {
            alert.title = "Error"
            alert.headerText = "Ingrese un ID válido y todos los campos para modificar el usuario."
            alert.showAndWait()
        }
    }
}