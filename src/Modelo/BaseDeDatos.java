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
        String contraseña = ""; // Contraseña de tu base de datos
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
        String query = "INSERT INTO orquidea (nombre, humedad, temperatura) VALUES (?, 0, 0)";
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
    String query = "SELECT o.id, o.nombre, r.fecha_riego, r.hora_riego, o.humedad, o.temperatura, o.estado " +
               "FROM orquidea o " +
               "LEFT JOIN programacion_riego r ON o.id = r.planta_id;";


    PreparedStatement stmt = conexion.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();

    // Lista para almacenar los resultados
    List<String[]> plantas = new ArrayList<>();
        while (rs.next()) {
            String id = String.valueOf(rs.getInt("id"));
            String nombre = rs.getString("nombre");
            String fechaRiego = rs.getString("fecha_riego");
            String horaRiego = rs.getString("hora_riego");
            String humedad = rs.getString("humedad");
            String temperatura = rs.getString("temperatura");
            String estado = rs.getString("estado");

            // Manejar valores NULL (si es necesario)
            if (fechaRiego == null) fechaRiego = "";
            if (horaRiego == null) horaRiego = "";
            if (humedad == null) humedad = "";
            if (temperatura == null) temperatura = "";
            if (estado == null) estado = "";

            plantas.add(new String[] { id, nombre, fechaRiego, horaRiego, humedad, temperatura, estado });
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
    String query = "UPDATE orquidea SET estado = ?  WHERE id = ?";
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
    
    
     public void actualizarSensores(int plantaId, int humedad, double temperatura) throws Exception {
    String query = "UPDATE orquidea SET humedad = ?, temperatura = ? WHERE id = ?";
    try (PreparedStatement stmt = conexion.prepareStatement(query)) {
        stmt.setInt(1, humedad);
        stmt.setDouble(2, temperatura);
        stmt.setInt(3, plantaId);
        stmt.executeUpdate();
        System.out.println("Sensores actualizados para la planta ID: " + plantaId);
    } catch (SQLException e) {
        throw new Exception("Error al actualizar sensores: " + e.getMessage());
    }
}
     
    public boolean verificarUsuario(String nombreUsuario, String contraseñaEncriptada) {
    if (conexion == null) return false;

    try (PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?")) {
        ps.setString(1, nombreUsuario);
        ps.setString(2, contraseñaEncriptada);

        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // Retorna true si el usuario fue encontrado
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public void registrarActividad(int plantaId, String tipoActividad, String valorAnterior, String valorNuevo) throws Exception {
    String query = "INSERT INTO tblHistorial  (planta_id, fecha_hora, tipo_actividad, valor_anterior, valor_nuevo) VALUES (?, NOW(), ?, ?, ?)";
    try (PreparedStatement stmt = conexion.prepareStatement(query)) {
        stmt.setInt(1, plantaId);
        stmt.setString(2, tipoActividad);
        stmt.setString(3, valorAnterior);
        stmt.setString(4, valorNuevo);
        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new Exception("Error al registrar actividad: " + e.getMessage());
    }
}
    
    
    public int obtenerHumedadPlanta(int plantaId) throws Exception {
    String query = "SELECT humedad FROM orquidea WHERE id = ?";
    try (PreparedStatement stmt = conexion.prepareStatement(query)) {
        stmt.setInt(1, plantaId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("humedad");
            } else {
                throw new Exception("Planta no encontrada.");
            }
        }
    }
}

public double obtenerTemperaturaPlanta(int plantaId) throws Exception {
    String query = "SELECT temperatura FROM orquidea WHERE id = ?";
    try (PreparedStatement stmt = conexion.prepareStatement(query)) {
        stmt.setInt(1, plantaId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("temperatura");
            } else {
                throw new Exception("Planta no encontrada.");
            }
        }
    }
}


public List<String[]> obtenerHistorial() throws Exception {
    String query = "SELECT * FROM tblHistorial ORDER BY fecha_hora DESC";
    List<String[]> historial = new ArrayList<>();
    try (PreparedStatement stmt = conexion.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String id = String.valueOf(rs.getInt("id"));
            String plantaIdStr = String.valueOf(rs.getInt("planta_id"));
            String fechaHora = rs.getString("fecha_hora");
            String tipoActividad = rs.getString("tipo_actividad");
            String valorAnterior = rs.getString("valor_anterior");
            String valorNuevo = rs.getString("valor_nuevo");
            historial.add(new String[] { id, plantaIdStr, fechaHora, tipoActividad, valorAnterior, valorNuevo });
        }
    } catch (SQLException e) {
        throw new Exception("Error al obtener el historial: " + e.getMessage());
    }
    return historial;
}
    
    
    
    
    
    
}