import base_de_datos.eliminarPedido
import base_de_datos.eliminarProductoPorId
import base_de_datos.eliminarUsuario
import base_de_datos.insertarDatos
import base_de_datos.obtenerConexion

fun main() {
    val conexion = obtenerConexion()

    if (conexion != null) {
        //insertarDatos(conexion)
        obtenerLineasDePedido(conexion, 1)
        obtenerTotalPedidosPorUsuario(conexion, "Ataufo Rodríguez")
        obtenerUsuariosQueCompraronAbanico(conexion)
        eliminarUsuario(conexion, "Cornelio Ramírez")
        eliminarProductoPorId(conexion, 3)
        eliminarPedido(conexion, 3)
        conexion.close()
    }
}