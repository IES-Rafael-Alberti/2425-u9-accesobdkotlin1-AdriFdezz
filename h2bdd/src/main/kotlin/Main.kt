import base_de_datos.eliminarPedido
import base_de_datos.eliminarProductoPorId
import base_de_datos.eliminarUsuario
import base_de_datos.insertarDatos
import base_de_datos.modificarLineaPedido
import base_de_datos.modificarPrecioProducto
import base_de_datos.obtenerConexion

// Para probar las distintas cosas que piden los ejercicios comentar y des-comentar según se necesite.
fun main() {
    val conexion = obtenerConexion()

    if (conexion != null) {
        //insertarDatos(conexion)
        obtenerLineasDePedido(conexion, 1)
        obtenerTotalPedidosPorUsuario(conexion, "Ataufo Rodríguez")
        obtenerUsuariosCompraAbanico(conexion)
        //eliminarUsuario(conexion, "Cornelio Ramírez")
        //eliminarProductoPorId(conexion, 3)
        //eliminarPedido(conexion, 3)
        modificarPrecioProducto(conexion, "Abanico", 100.0)
        modificarLineaPedido(conexion, 3)
        conexion.close()
    }
}