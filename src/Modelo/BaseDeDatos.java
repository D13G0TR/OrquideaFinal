package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {
    private Connection conexion;

    // Método para conectar a la base de datos
    public void conectar() throws Exception {
        String url = "jdbc:mysql://localhost:3306/orquideas"; // Cambia si tu base de datos tiene otro nombre
        String usuario = "root"; // Usuario de tu base de datos
        String contraseña = "123456"; // Contraseña de tu base de datos
        conexion = DriverManager.getConnection(url, usuario, contraseña);
        System.out.println("Conexión exitosa a la base de datos.");
    }

    // Método para verificar si la conexión está activa
    public boolean conexionActiva() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para guardar solo el nombre de la planta
    public void guardarNombrePlanta(String nombre) throws Exception {
        String query = "INSERT INTO orquidea (nombre, humedad, temperatura) VALUES (?, NULL, NULL)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            System.out.println("Planta guardada correctamente.");
        } catch (SQLException e) {
            throw new Exception("Error al guardar la planta: " + e.getMessage());
        }
    }

    // Método para obtener la lista de plantas con estado
public List<String[]> obtenerPlantas() throws Exception {
    String query = "SELECT o.id, o.nombre, r.fecha_riego, r.hora_riego, o.estado " +
                   "FROM orquidea o " +
                   "LEFT JOIN programacion_riego r ON o.id = r.planta_id";
    PreparedStatement stmt = conexion.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();

    // Lista para almacenar los resultados
    List<String[]> plantas = new ArrayList<>();
    while (rs.next()) {
        String id = String.valueOf(rs.getInt("id"));           // ID de la planta
        String nombre = rs.getString("nombre");                // Nombre de la planta
        String fechaRiego = rs.getString("fecha_riego");       // Fecha de riego (puede ser NULL)
        String horaRiego = rs.getString("hora_riego");         // Hora de riego (puede ser NULL)
        String estado = rs.getString("estado");                // Estado de la planta

        // Manejar valores NULL
        if (fechaRiego == null) fechaRiego = ""; // Mostrar en blanco si no hay fecha
        if (horaRiego == null) horaRiego = "";   // Mostrar en blanco si no hay hora
        if (estado == null) estado = "";         // Mostrar en blanco si no hay estado

        // Agregar los datos a la lista
        plantas.add(new String[] { id, nombre, fechaRiego, horaRiego, estado });
    }

    // Cerrar recursos
    rs.close();
    stmt.close();
    return plantas;
}


    // Método para guardar la programación de riego
    public void guardarRiego(int plantaId, java.sql.Date fecha, java.sql.Time hora) throws Exception {
        String query = "INSERT INTO programacion_riego (planta_id, fecha_riego, hora_riego) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, plantaId); // ID de la planta
            stmt.setDate(2, fecha);  // Fecha seleccionada
            stmt.setTime(3, hora);   // Hora seleccionada
            stmt.executeUpdate();
            System.out.println("Riego programado correctamente.");
        } catch (SQLException e) {
            throw new Exception("Error al programar el riego: " + e.getMessage());
        }
    }
    
    
public void actualizarEstado(int plantaId, String estado) throws Exception {
    String query = "UPDATE orquidea SET estado = ? WHERE id = ?";
    try (PreparedStatement stmt = conexion.prepareStatement(query)) {
        stmt.setString(1, estado);  // Nuevo estado
        stmt.setInt(2, plantaId);  // ID de la planta
        stmt.executeUpdate();
        // Elimina esta línea:
        // System.out.println("Estados de las plantas actualizados");
    } catch (SQLException e) {
        throw new Exception("Error al actualizar el estado: " + e.getMessage());
    }
}


    

    // Método para obtener la conexión
    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
