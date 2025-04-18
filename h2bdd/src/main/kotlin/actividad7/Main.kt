package actividad7

data class Pedido(var id: Int, val precioTotal: Double, val idUsuario: Int)
data class LineaPedido(val id: Int, val cantidad: Int, val precio:Double, var idPedido: Int, val idProducto:Int)

fun main() {
    val c = Conexion()

    if (c.c.isValid(10)) {
        c.c.use { conexion ->
            val pedidoRepository = PedidoRepository(conexion)
            val pedidos = pedidoRepository.mostrar(1)

            for (pedidoMap in pedidos) {
                val pedido = pedidoMap.keys.first()
                println("Pedido ID: ${pedido.id}, Precio Total: ${pedido.precioTotal}")

                val lineas = pedidoMap.values.first()
                for (linea in lineas) {
                    println("  LineaPedido ID: ${linea.id}, Producto ID: ${linea.idProducto}, Cantidad: ${linea.cantidad}, Precio: ${linea.precio}")
                }
            }

            val nuevoPedido = Pedido(id = 0, precioTotal = 500.0, idUsuario = 1)
            val lineasDePedido = mutableListOf(
                LineaPedido(id = 0, cantidad = 1, precio = 150.0, idPedido = 0, idProducto = 1),
                LineaPedido(id = 0, cantidad = 2, precio = 175.0, idPedido = 0, idProducto = 2)
            )
            pedidoRepository.insertar(nuevoPedido, lineasDePedido)
            println("Nuevo pedido insertado con ID: ${nuevoPedido.id}, Precio Total: ${nuevoPedido.precioTotal}")

            val pedidoAEliminar = Pedido(id = 2, precioTotal = 0.0, idUsuario = 0)
            pedidoRepository.eliminar(pedidoAEliminar)
            println("Pedido con ID 2 eliminado.")
            println("Pedidos después de la eliminación:")
            val pedidosActualizados = pedidoRepository.mostrar(1)
            for (pedidoMap in pedidosActualizados) {
                val pedido = pedidoMap.keys.first()
                println("Pedido ID: ${pedido.id}, Precio Total: ${pedido.precioTotal}")

                val lineas = pedidoMap.values.first()
                for (linea in lineas) {
                    println("  LineaPedido ID: ${linea.id}, Producto ID: ${linea.idProducto}, Cantidad: ${linea.cantidad}, Precio: ${linea.precio}")
                }
            }
        }
    } else {
        println("Error de conexión")
    }
}