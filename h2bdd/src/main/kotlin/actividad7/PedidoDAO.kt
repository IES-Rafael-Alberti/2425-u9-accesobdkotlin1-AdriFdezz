package actividad7

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class PedidoDAO(private val c: Connection) {

    fun insertar(pedido: Pedido) {
        val insertPedido = """
            INSERT INTO Pedido (precioTotal, idUsuario) VALUES (?, ?)
        """
        var statement: PreparedStatement? = null
        try {
            statement = c.prepareStatement(insertPedido, Statement.RETURN_GENERATED_KEYS)
            statement.setDouble(1, pedido.precioTotal)
            statement.setInt(2, pedido.idUsuario)
            statement.executeUpdate()

            val resultSet = statement.generatedKeys
            if (resultSet.next()) {
                pedido.id = resultSet.getInt(1)
            }
        } catch (e: SQLException) {
            println("Error de BBDD al insertar el pedido: ${e.message}")
        } finally {
            statement?.close()
        }
    }

    fun mostrar(idUsuario: Int): MutableList<Pedido> {
        val query = """
            SELECT * FROM Pedido WHERE idUsuario = ?
        """
        val pedidos = mutableListOf<Pedido>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            statement = c.prepareStatement(query)
            statement.setInt(1, idUsuario)
            resultSet = statement.executeQuery()

            while (resultSet.next()) {
                val pedido = Pedido(
                    resultSet.getInt("id"),
                    resultSet.getDouble("precioTotal"),
                    resultSet.getInt("idUsuario")
                )
                pedidos.add(pedido)
            }
        } catch (e: SQLException) {
            println("Error de BBDD al mostrar los pedidos: ${e.message}")
        } finally {
            resultSet?.close()
            statement?.close()
        }
        return pedidos
    }

    fun eliminar(pedido: Pedido) {
        val deletePedido = """
            DELETE FROM Pedido WHERE id = ?
        """
        var statement: PreparedStatement? = null
        try {
            statement = c.prepareStatement(deletePedido)
            statement.setInt(1, pedido.id)
            statement.executeUpdate()
        } catch (e: SQLException) {
            println("Error de BBDD al eliminar el pedido: ${e.message}")
        } finally {
            statement?.close()
        }
    }
}