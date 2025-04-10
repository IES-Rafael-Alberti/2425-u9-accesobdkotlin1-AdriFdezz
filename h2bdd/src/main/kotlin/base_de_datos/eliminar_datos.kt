package base_de_datos

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

fun eliminarUsuario(conexion: Connection, nombreUsuario: String) {
    try {
        val eliminarUsuario = """
            DELETE FROM Usuario WHERE nombre = ?
        """
        val statementUsuario: PreparedStatement = conexion.prepareStatement(eliminarUsuario)
        statementUsuario.setString(1, nombreUsuario)
        val rowsAffected = statementUsuario.executeUpdate()

        if (rowsAffected > 0) {
            println("Usuario $nombreUsuario eliminado con éxito.")
        } else {
            println("No se encontró un usuario con nombre $nombreUsuario.")
        }
    } catch (e: SQLException) {
        println("Error al eliminar el usuario: ${e.message}")
    }
}

fun eliminarProductoPorId(conexion: Connection, idProducto: Int) {
    try {
        val eliminarProducto = """
            DELETE FROM Producto WHERE id = ?
        """
        val statementProducto: PreparedStatement = conexion.prepareStatement(eliminarProducto)
        statementProducto.setInt(1, idProducto)
        val rowsAffected = statementProducto.executeUpdate()

        if (rowsAffected > 0) {
            println("Producto con id = $idProducto eliminado con éxito.")
        } else {
            println("No se encontró un producto con id = $idProducto.")
        }

    } catch (e: SQLException) {
        println("Error al eliminar el producto: ${e.message}")
    }
}


fun eliminarPedido(conexion: Connection, idPedido: Int) {
    try {
        val eliminarPedido = """
            DELETE FROM Pedido WHERE id = ?
        """
        val statementPedido: PreparedStatement = conexion.prepareStatement(eliminarPedido)
        statementPedido.setInt(1, idPedido)

        val rowsAffected = statementPedido.executeUpdate()

        if (rowsAffected > 0) {
            println("Pedido con id = $idPedido eliminado con éxito.")
        } else {
            println("No se encontró un pedido con id = $idPedido.")
        }

    } catch (e: SQLException) {
        println("Error al eliminar el pedido: ${e.message}")
    }
}
