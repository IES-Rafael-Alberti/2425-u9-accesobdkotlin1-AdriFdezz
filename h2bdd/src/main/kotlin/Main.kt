import base_de_datos.eliminarPedido
import base_de_datos.eliminarProductoPorId
import base_de_datos.eliminarUsuario
import base_de_datos.insertarDatos
import base_de_datos.insertarUsuario
import base_de_datos.obtenerPedidosPorUsuario
import base_de_datos.modificarLineaPedido
import base_de_datos.modificarPrecioProducto
import base_de_datos.obtenerConexion
import base_de_datos.obtenerDataSource

// Para probar las distintas cosas que piden los ejercicios comentar y des-comentar según se necesite.
fun main() {
    //val conexion = obtenerConexion()
    val dataSource = obtenerDataSource() //DataSource para HikariCP
    val conexion = dataSource.connection //Conectar al pool para HikariCP

    if (conexion != null) {
        insertarUsuario(conexion, 4, "Reinaldo Girúndez", "reingir@mail.com")
        //insertarDatos(conexion)
        obtenerPedidosPorUsuario(conexion, "Facundo Pérez")
        obtenerLineasDePedido(conexion, 1)
        obtenerTotalPedidosPorUsuario(conexion, "Ataufo Rodríguez")
        obtenerUsuariosCompraAbanico(conexion)
        //eliminarUsuario(conexion, "Cornelio Ramírez")
        //eliminarProductoPorId(conexion, 3)
        //eliminarPedido(conexion, 3)
        //modificarPrecioProducto(conexion, "Abanico", 100.0)
        //modificarLineaPedido(conexion, 3)
        conexion.close()
    }
}