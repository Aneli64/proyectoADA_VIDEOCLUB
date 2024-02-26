package com.example.proyectoADA

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProyectoAdaApplication

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("/archivos_javaFX/main.fxml"))
        val scene = Scene(fxmlLoader.load(), 1400.0, 800.0)
        stage.title = "Proyecto ADA"
        stage.scene = scene
        stage.show()
    }
}

fun main(args: Array<String>)  {
    runApplication<ProyectoAdaApplication>(*args)
    Application.launch(HelloApplication::class.java)
}