package org.example.base_de_datos

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    val url = "jdbc:h2:./base_datos/bdd_h2"
    val usuario = "sa"
    val contrasena = ""

    try {
        val conexion: Connection = DriverManager.getConnection(url, usuario, contrasena)
        println("Conexión establecida con exito")
        conexion.close()
    } catch (e: SQLException) {
        println("Error al conectar con la base de datos: ${e.message}")
    }
}