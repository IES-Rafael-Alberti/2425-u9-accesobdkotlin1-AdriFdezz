package base_de_datos

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement

fun obtenerConexion(): Connection? {
    val url = "jdbc:h2:./base_datos/bdd_h2"
    val usuario = "sa"
    val contrasena = ""

    try {
        val conexion: Connection = DriverManager.getConnection(url, usuario, contrasena)
        println("Conexión establecida con exito")
        return conexion
    } catch (e: SQLException) {
        println("Error al conectar con la base de datos: ${e.message}")
        return null
    }
}

fun crearTablas(conexion: Connection) {
    try {
        val statement: Statement = conexion.createStatement()

        val crearTablaUsuario = """
            CREATE TABLE IF NOT EXISTS Usuario (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(255) NOT NULL,
                email VARCHAR(255) UNIQUE
            );
        """

        val crearTablaProducto = """
            CREATE TABLE IF NOT EXISTS Producto (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(255) NOT NULL,
                precio DECIMAL NOT NULL,
                stock INT NOT NULL
            );
        """

        val crearTablaPedido = """
            CREATE TABLE IF NOT EXISTS Pedido (
                id INT AUTO_INCREMENT PRIMARY KEY,
                precioTotal DECIMAL NOT NULL,
                idUsuario INT,
                FOREIGN KEY (idUsuario) REFERENCES Usuario(id)
            );
        """

        val crearTablaLineaPedido = """
            CREATE TABLE IF NOT EXISTS LineaPedido (
                id INT AUTO_INCREMENT PRIMARY KEY,
                cantidad INT NOT NULL,
                precio DECIMAL NOT NULL,
                idPedido INT,
                idProducto INT,
                FOREIGN KEY (idPedido) REFERENCES Pedido(id),
                FOREIGN KEY (idProducto) REFERENCES Producto(id)
            );
        """

        statement.executeUpdate(crearTablaUsuario)
        statement.executeUpdate(crearTablaProducto)
        statement.executeUpdate(crearTablaPedido)
        statement.executeUpdate(crearTablaLineaPedido)

        println("Tablas creadas con exito")

    } catch (e: SQLException) {
        println("Error al crear las tablas: ${e.message}")
    }
}

fun insertarDatos(conexion: Connection) {
    try {
        val insertarUsuario = """
            INSERT INTO Usuario (nombre, email) VALUES (?, ?)
        """
        val statementUsuario: PreparedStatement = conexion.prepareStatement(insertarUsuario)
        statementUsuario.setString(1, "Facundo Pérez")
        statementUsuario.setString(2, "facuper@mail.com")
        statementUsuario.executeUpdate()

        statementUsuario.setString(1, "Ataufo Rodríguez")
        statementUsuario.setString(2, "ataurod@mail.com")
        statementUsuario.executeUpdate()

        statementUsuario.setString(1, "Cornelio Ramírez")
        statementUsuario.setString(2, "Cornram@mail.com")
        statementUsuario.executeUpdate()

        val insertarProducto = """
            INSERT INTO Producto (nombre, precio, stock) VALUES (?, ?, ?)
        """
        val statementProducto: PreparedStatement = conexion.prepareStatement(insertarProducto)
        statementProducto.setString(1, "Ventilador")
        statementProducto.setDouble(2, 10.0)
        statementProducto.setInt(3, 2)
        statementProducto.executeUpdate()

        statementProducto.setString(1, "Abanico")
        statementProducto.setDouble(2, 150.0)
        statementProducto.setInt(3, 47)
        statementProducto.executeUpdate()

        statementProducto.setString(1, "Estufa")
        statementProducto.setDouble(2, 24.99)
        statementProducto.setInt(3, 1)
        statementProducto.executeUpdate()

        val insertarPedido = """
            INSERT INTO Pedido (idUsuario, precioTotal) VALUES (?, ?)
        """
        val statementPedido: PreparedStatement = conexion.prepareStatement(insertarPedido)
        statementPedido.setInt(1, 1)
        statementPedido.setDouble(2, 160.0)
        statementPedido.executeUpdate()

        statementPedido.setInt(1, 2)
        statementPedido.setDouble(2, 20.0)
        statementPedido.executeUpdate()

        statementPedido.setInt(1, 3)
        statementPedido.setDouble(2, 150.0)
        statementPedido.executeUpdate()

        val insertarLineaPedido = """
            INSERT INTO LineaPedido (idPedido, idProducto, cantidad, precio) VALUES (?, ?, ?, ?)
        """
        val statementLineaPedido: PreparedStatement = conexion.prepareStatement(insertarLineaPedido)
        statementLineaPedido.setInt(1, 1)
        statementLineaPedido.setInt(2, 1)
        statementLineaPedido.setInt(3, 1)
        statementLineaPedido.setDouble(4, 10.0)
        statementLineaPedido.executeUpdate()

        statementLineaPedido.setInt(1, 1)
        statementLineaPedido.setInt(2, 2)
        statementLineaPedido.setInt(3, 1)
        statementLineaPedido.setDouble(4, 150.0)
        statementLineaPedido.executeUpdate()

        statementLineaPedido.setInt(1, 2)
        statementLineaPedido.setInt(2, 1)
        statementLineaPedido.setInt(3, 2)
        statementLineaPedido.setDouble(4, 10.0)
        statementLineaPedido.executeUpdate()

        println("Datos insertados con exito")

    } catch (e: SQLException) {
        println("Error al insertar los datos: ${e.message}")
    }
}