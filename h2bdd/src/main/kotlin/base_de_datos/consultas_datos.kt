import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

fun obtenerLineasDePedido(conexion: Connection, idPedido: Int) {
    val query = """
        SELECT lp.id, lp.idPedido, lp.idProducto, lp.cantidad, lp.precio
        FROM LineaPedido lp
        WHERE lp.idPedido = ?
    """

    try {
        val statement: PreparedStatement = conexion.prepareStatement(query)
        statement.setInt(1, idPedido)
        val resultSet: ResultSet = statement.executeQuery()

        println("Lineas de pedido para el pedido con id = $idPedido:")
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val idProducto = resultSet.getInt("idProducto")
            val cantidad = resultSet.getInt("cantidad")
            val precio = resultSet.getDouble("precio")

            println("ID: $id, Producto ID: $idProducto, Cantidad: $cantidad, Precio: $precio €")
        }
    } catch (e: SQLException) {
        println("Error al obtener las lineas de pedido: ${e.message}")
    }
}

fun obtenerTotalPedidosPorUsuario(conexion: Connection, usuario: String): Double {
    val query = """
        SELECT SUM(p.precioTotal) AS totalPedidos
        FROM Pedido p
        JOIN Usuario u ON p.idUsuario = u.id
        WHERE u.nombre = ?
    """

    var total = 0.0
    try {
        val statement: PreparedStatement = conexion.prepareStatement(query)
        statement.setString(1, usuario)
        val resultSet = statement.executeQuery()

        if (resultSet.next()) {
            total = resultSet.getDouble("totalPedidos")
        }

        println("Suma del importe total de los pedidos de $usuario: $total €")
    } catch (e: SQLException) {
        println("Error al obtener la suma del total de los pedidos: ${e.message}")
    }

    return total
}

fun obtenerUsuariosCompraAbanico(conexion: Connection) {
    val query = """
        SELECT DISTINCT u.nombre
        FROM Usuario u
        JOIN Pedido p ON u.id = p.idUsuario
        JOIN LineaPedido lp ON p.id = lp.idPedido
        JOIN Producto pr ON lp.idProducto = pr.id
        WHERE pr.nombre = 'Abanico';
    """

    try {
        val statement: PreparedStatement = conexion.prepareStatement(query)
        val resultSet = statement.executeQuery()

        println("Usuarios que han comprado un Abanico:")
        while (resultSet.next()) {
            val nombre = resultSet.getString("nombre")
            println(nombre)
        }
    } catch (e: SQLException) {
        println("Error al obtener los usuarios que han comprado un Abanico: ${e.message}")
    }
}