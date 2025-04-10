package base_de_datos

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

fun obtenerConexion(): Connection? {
    val url = "jdbc:h2:./base_datos/bdd_h2"
    val usuario = "sa"
    val contrasena = ""

    try {
        val conexion: Connection = DriverManager.getConnection(url, usuario, contrasena)
        println("Conexión establecida con exito")
        return conexion
    } catch (e: SQLException) {
        println("Error al conectar con la base de datos: ${e.message}")
        return null
    }
}

fun insertarDatos(conexion: Connection) {
    try {
        val insertarUsuario = """
            INSERT INTO Usuario (nombre, email) VALUES (?, ?)
        """
        val usuarios = listOf(
            "Facundo Pérez" to "facuper@mail.com",
            "Ataufo Rodríguez" to "ataurod@mail.com",
            "Cornelio Ramírez" to "cornram@mail.com"
        )

        for ((nombre, email) in usuarios) {
            val statementUsuario: PreparedStatement = conexion.prepareStatement(insertarUsuario)
            statementUsuario.setString(1, nombre)
            statementUsuario.setString(2, email)
            statementUsuario.executeUpdate()
            println("Usuario insertado: $nombre")
        }

        val insertarProducto = """
            INSERT INTO Producto (nombre, precio, stock) VALUES (?, ?, ?)
        """
        val statementProducto: PreparedStatement = conexion.prepareStatement(insertarProducto)
        statementProducto.setString(1, "Ventilador")
        statementProducto.setDouble(2, 10.0)
        statementProducto.setInt(3, 2)
        statementProducto.executeUpdate()

        statementProducto.setString(1, "Abanico")
        statementProducto.setDouble(2, 150.0)
        statementProducto.setInt(3, 47)
        statementProducto.executeUpdate()

        statementProducto.setString(1, "Estufa")
        statementProducto.setDouble(2, 24.99)
        statementProducto.setInt(3, 1)
        statementProducto.executeUpdate()
        println("Productos insertados con éxito.")

        val insertarPedido = """
            INSERT INTO Pedido (idUsuario, precioTotal) VALUES (?, ?)
        """
        val statementPedido: PreparedStatement = conexion.prepareStatement(insertarPedido)

        statementPedido.setInt(1, 2)
        statementPedido.setDouble(2, 160.0)
        statementPedido.executeUpdate()

        statementPedido.setInt(1, 1)
        statementPedido.setDouble(2, 20.0)
        statementPedido.executeUpdate()

        statementPedido.setInt(1, 2)
        statementPedido.setDouble(2, 150.0)
        statementPedido.executeUpdate()
        println("Pedidos insertados con éxito.")

        val insertarLineaPedido = """
            INSERT INTO LineaPedido (idPedido, idProducto, cantidad, precio) VALUES (?, ?, ?, ?)
        """
        val statementLineaPedido: PreparedStatement = conexion.prepareStatement(insertarLineaPedido)
        statementLineaPedido.setInt(1, 1)
        statementLineaPedido.setInt(2, 1)
        statementLineaPedido.setInt(3, 1)
        statementLineaPedido.setDouble(4, 10.0)
        statementLineaPedido.executeUpdate()

        statementLineaPedido.setInt(1, 1)
        statementLineaPedido.setInt(2, 2)
        statementLineaPedido.setInt(3, 1)
        statementLineaPedido.setDouble(4, 150.0)
        statementLineaPedido.executeUpdate()

        statementLineaPedido.setInt(1, 2)
        statementLineaPedido.setInt(2, 1)
        statementLineaPedido.setInt(3, 2)
        statementLineaPedido.setDouble(4, 10.0)
        statementLineaPedido.executeUpdate()

        statementLineaPedido.setInt(1, 3)
        statementLineaPedido.setInt(2, 2)
        statementLineaPedido.setInt(3, 1)
        statementLineaPedido.setDouble(4, 150.0)
        statementLineaPedido.executeUpdate()
        println("Líneas de pedido insertadas con éxito.")

    } catch (e: SQLException) {
        println("Error al insertar los datos: ${e.message}")
    }
}