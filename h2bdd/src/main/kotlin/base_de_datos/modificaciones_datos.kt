package base_de_datos

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException


fun modificarPrecioProducto(conexion: Connection, nombreProducto: String, nuevoPrecio: Double) {
    try {
        val modificarPrecio = """
            UPDATE Producto SET precio = ? WHERE nombre = ?
        """
        val statementProducto: PreparedStatement = conexion.prepareStatement(modificarPrecio)
        statementProducto.setDouble(1, nuevoPrecio)
        statementProducto.setString(2, nombreProducto)

        val filasAfectadas = statementProducto.executeUpdate()

        if (filasAfectadas > 0) {
            println("El precio del producto '$nombreProducto' ha sido actualizado a $nuevoPrecio €.")
        } else {
            println("No se encontró un producto con nombre '$nombreProducto'.")
        }

    } catch (e: SQLException) {
        println("Error al modificar el precio del producto: ${e.message}")
    }
}

fun modificarLineaPedido(conexion: Connection, idLineaPedido: Int) {
    try {
        val obtenerPrecioAbanico = """
            SELECT precio FROM Producto WHERE nombre = 'Abanico'
        """
        val statementAbanico: PreparedStatement = conexion.prepareStatement(obtenerPrecioAbanico)
        val resultSet = statementAbanico.executeQuery()

        var precioAbanico = 0.0
        if (resultSet.next()) {
            precioAbanico = resultSet.getDouble("precio")
        }

        if (precioAbanico == 0.0) {
            println("No se encontró el producto 'Abanico'.")
            return
        }
        val nuevoPrecio = 2 * precioAbanico
        val actualizarLineaPedido = """
            UPDATE LineaPedido
            SET idProducto = 2, precio = ?
            WHERE id = ?
        """
        val statementLineaPedido: PreparedStatement = conexion.prepareStatement(actualizarLineaPedido)
        statementLineaPedido.setDouble(1, nuevoPrecio)
        statementLineaPedido.setInt(2, idLineaPedido)
        val filasAfectadas = statementLineaPedido.executeUpdate()

        if (filasAfectadas > 0) {
            println("Línea de pedido con id = $idLineaPedido actualizada con éxito.")
        } else {
            println("No se encontró una línea de pedido con id = $idLineaPedido.")
        }

    } catch (e: SQLException) {
        println("Error al modificar la línea de pedido: ${e.message}")
    }
}