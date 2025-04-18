package actividad7

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class LineaDAO(private val c: Connection) {

    fun insertar(lineaPedido: LineaPedido) {
        val insertLineaPedido = """
            INSERT INTO LineaPedido (cantidad, precio, idPedido, idProducto) 
            VALUES (?, ?, ?, ?)
        """
        var statement: PreparedStatement? = null
        try {
            statement = c.prepareStatement(insertLineaPedido)
            statement.setInt(1, lineaPedido.cantidad)
            statement.setDouble(2, lineaPedido.precio)
            statement.setInt(3, lineaPedido.idPedido)
            statement.setInt(4, lineaPedido.idProducto)
            statement.executeUpdate()
            println("Linea de pedido insertada con éxito.")
        } catch (e: SQLException) {
            println("Error de BBDD al insertar la línea de pedido: ${e.message}")
        } finally {
            statement?.close()
        }
    }

    fun mostrar(idPedido: Int): MutableList<LineaPedido> {
        val query = """
            SELECT * FROM LineaPedido WHERE idPedido = ?
        """
        val lineas = mutableListOf<LineaPedido>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            statement = c.prepareStatement(query)
            statement.setInt(1, idPedido)
            resultSet = statement.executeQuery()

            while (resultSet.next()) {
                val lineaPedido = LineaPedido(
                    resultSet.getInt("id"),
                    resultSet.getInt("cantidad"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("idPedido"),
                    resultSet.getInt("idProducto")
                )
                lineas.add(lineaPedido)
            }
        } catch (e: SQLException) {
            println("Error de BBDD al mostrar las líneas de pedido: ${e.message}")
        } finally {
            resultSet?.close()
            statement?.close()
        }
        return lineas
    }

    fun eliminar(idPedido: Int) {
        val deleteLineaPedido = """
            DELETE FROM LineaPedido WHERE idPedido = ?
        """
        var statement: PreparedStatement? = null
        try {
            statement = c.prepareStatement(deleteLineaPedido)
            statement.setInt(1, idPedido)
            statement.executeUpdate()
        } catch (e: SQLException) {
            println("Error de BBDD al eliminar la línea de pedido: ${e.message}")
        } finally {
            statement?.close()
        }
    }
}