package actividad7

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.SQLException

class Conexion {
    lateinit var c: Connection
    private val url = "jdbc:h2:./base_datos2/bdd_h2_nuevo;AUTO_SERVER=TRUE"
    private val usuario = "sa"
    private val contrasena = ""

    init {
        try {
            val config = HikariConfig().apply {
                jdbcUrl = url
                username = usuario
                password = contrasena
                driverClassName = "org.h2.Driver"
                maximumPoolSize = 10
            }
            val dataSource = HikariDataSource(config)
            c = dataSource.connection
            println("Conexión exitosa a la base de datos.")
        } catch (e: SQLException) {
            println("Error al conectar con la base de datos: ${e.message}")
        }
    }
}