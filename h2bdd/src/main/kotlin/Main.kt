import base_de_datos.insertarDatos
import base_de_datos.obtenerConexion

fun main() {
    val conexion = obtenerConexion()

    if (conexion != null) {
        insertarDatos(conexion)
        obtenerLineasDePedido(conexion, 1)
        obtenerTotalPedidosPorUsuario(conexion, "Ataufo Rodríguez")
        obtenerUsuariosQueCompraronAbanico(conexion)
        conexion.close()
    }
}