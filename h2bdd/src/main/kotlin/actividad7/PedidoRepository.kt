package actividad7

import java.sql.Connection

class PedidoRepository(c: Connection) : IPedidoRepositoy {
    private val pedidoDAO = PedidoDAO(c)
    private val lineaPedidoDAO = LineaDAO(c)

    override fun insertar(pedido: Pedido, lineas: MutableList<LineaPedido>) {
        pedidoDAO.insertar(pedido)

        for (linea in lineas) {
            linea.idPedido = pedido.id
            lineaPedidoDAO.insertar(linea)
        }
    }

    override fun mostrar(idUsuario: Int): MutableList<Map<Pedido, MutableList<LineaPedido>>> {
        val pedidos = pedidoDAO.mostrar(idUsuario)
        val pedidosCompletos = mutableListOf<Map<Pedido, MutableList<LineaPedido>>>()

        for (p in pedidos) {
            val lineas = lineaPedidoDAO.mostrar(p.id)
            val asociacion = mapOf(Pair(p, lineas))
            pedidosCompletos.add(asociacion)
        }
        return pedidosCompletos
    }

    override fun eliminar(pedido: Pedido) {
        lineaPedidoDAO.eliminar(pedido.id)
        pedidoDAO.eliminar(pedido)
    }
}