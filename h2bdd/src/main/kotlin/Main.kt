import base_de_datos.crearTablas
import base_de_datos.insertarDatos
import base_de_datos.obtenerConexion

fun main() {
    val conexion = obtenerConexion()

    if (conexion != null) {
        crearTablas(conexion)
        insertarDatos(conexion)
        conexion.close()
    }
}