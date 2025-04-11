package base_de_datos

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

fun obtenerDataSource(): HikariDataSource {
    val configuracion = HikariConfig().apply {
        jdbcUrl = "jdbc:h2:./base_datos/bdd_h2;AUTO_SERVER=TRUE"
        username = "sa"
        password = ""
        driverClassName = "org.h2.Driver"
        maximumPoolSize = 10
    }
    return HikariDataSource(configuracion)
}

fun insertarUsuario(conexion: Connection, id: Int, nombre: String, email: String) {
    val query = """
        INSERT INTO Usuario (id, nombre, email) 
        VALUES (?, ?, ?)
    """
    try {
        val statement: PreparedStatement = conexion.prepareStatement(query)
        statement.setInt(1, id)
        statement.setString(2, nombre)
        statement.setString(3, email)
        statement.executeUpdate()
        println("Usuario $nombre insertado con éxito.")
    } catch (e: SQLException) {
        println("Error al insertar el usuario: ${e.message}")
    }
}

fun obtenerPedidosPorUsuario(conexion: Connection, nombreUsuario: String) {
    val query = """
        SELECT p.id, p.precioTotal
        FROM Pedido p
        JOIN Usuario u ON p.idUsuario = u.id
        WHERE u.nombre = ?
    """

    try {
        val statement: PreparedStatement = conexion.prepareStatement(query)
        statement.setString(1, nombreUsuario)
        val resultSet: ResultSet = statement.executeQuery()

        if (resultSet.next()) {
            do {
                val idPedido = resultSet.getInt("id")
                val precioTotal = resultSet.getDouble("precioTotal")
                println("Pedido ID: $idPedido, Precio Total: $precioTotal")
            } while (resultSet.next())
        } else {
            println("No se encontraron pedidos para el usuario: $nombreUsuario")
        }

    } catch (e: SQLException) {
        println("Error al obtener los pedidos: ${e.message}")
    }
}