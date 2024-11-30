package clases;

import Modelo.BaseDeDatos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Usuario {
    private String run;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contraseña;

    public Usuario(String run, String nombre, String apellido, String nombreUsuario, String contraseña) {
        this.run = run;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }
    
    public Usuario(String run, String nombre, String apellido) {
        this.run = run;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getRun() {
        return this.run;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    private char calcularDigitoVerificador(String run) {
        int suma = 0;
        int multiplicador = 2;
        for (int i = run.length() - 1; i >= 0; i--) {
            suma += Integer.parseInt(String.valueOf(run.charAt(i))) * multiplicador;
            multiplicador = multiplicador % 7 == 0 ? 2 : multiplicador + 1;
        }
        int resto = 11 - (suma % 11);
        return (resto == 11) ? '0' : (resto == 10) ? 'K' : Character.forDigit(resto, 10);
    }

    public void insertarPersonalEnBD() throws Exception {
        if (!validarNombre(nombre) || !validarApellido(apellido)) {
        return; // No continuar si hay errores de validación
    }

    char digitoVerificador = calcularDigitoVerificador(run);
    BaseDeDatos baseDeDatos = new BaseDeDatos(); // Crear instancia de BaseDeDatos
    try (Connection conn = baseDeDatos.getConexion();
         PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuarios (run, digito_verificador, nombre, apellido, nombre_usuario, contraseña) VALUES (?, ?, ?, ?, ?, ?)")) {
         
        stmt.setString(1, run);
        stmt.setString(2, String.valueOf(digitoVerificador)); // Guardar digito verificador
        stmt.setString(3, nombre);
        stmt.setString(4, apellido);
        stmt.setString(5, nombreUsuario);
        encriptarContraseña(); // Encriptar antes de guardar
        stmt.setString(6, contraseña);

        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar personal en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NoSuchAlgorithmException e) {
        JOptionPane.showMessageDialog(null, "Error al encriptar la contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    // Método para validar el campo de nombre
    public boolean validarNombre(String nombre) {
        if (!nombre.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Método para validar el campo de apellido
    public boolean validarApellido(String apellido) {
        if (!apellido.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean existeRunEnBD() {
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Establecer la conexión a la base de datos
            baseDeDatos.getConexion();
            conn = baseDeDatos.getConexion();

            // Crear la sentencia SQL para la consulta
            String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE run = ?";

            // Crear el PreparedStatement
            stmt = conn.prepareStatement(sql);

            // Establecer el parámetro de la sentencia SQL
            stmt.setString(1, run);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Obtener el resultado de la consulta
            if (rs.next()) {
            int totalPersonal = rs.getInt("total");
            return totalPersonal > 0;
        }
        return false;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al verificar si existe el usuario en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    } finally {
        // Cerrar la conexión usando el método de BaseDeDatos
        if (baseDeDatos != null) {
            baseDeDatos.cerrarConexion();
        }
    }
    }

    public boolean existeNombrePersonalEnBD() {
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Establecer la conexión a la base de datos
            baseDeDatos.getConexion();
            conn = baseDeDatos.getConexion();

            // Crear la sentencia SQL para la consulta
            String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE nombre = ?";

            // Crear el PreparedStatement
            stmt = conn.prepareStatement(sql);

            // Establecer el parámetro de la sentencia SQL
            stmt.setString(1, nombre);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Obtener el resultado de la consulta
            if (rs.next()) {
            int totalPersonal = rs.getInt("total");
            return totalPersonal > 0;
        }
        return false;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al verificar si existe el nombre de usuario en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    } finally {
        // Cerrar la conexión usando el método de BaseDeDatos
        if (baseDeDatos != null) {
            baseDeDatos.cerrarConexion();
        }
    }
}
    public void crearUsuarioEnBD() {
    BaseDeDatos baseDeDatos = new BaseDeDatos();
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        // Establecer la conexión a la base de datos
        baseDeDatos.getConexion();
        conn = baseDeDatos.getConexion();

        // Crear la sentencia SQL para la inserción
        String sql = "INSERT INTO usuarios (run, nombre, apellido, nombre_usuario, contraseña) VALUES (?, ?, ?, ?, ?, ?)";

        // Crear el PreparedStatement
        stmt = conn.prepareStatement(sql);

        // Establecer los parámetros de la sentencia SQL
        stmt.setString(1, run);
        stmt.setString(2, nombre);
        stmt.setString(3, apellido);
        stmt.setString(4, nombreUsuario);
        stmt.setString(5, contraseña);

        // Ejecutar la inserción
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al crear usuario en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar la conexión usando el método de BaseDeDatos
        if (baseDeDatos != null) {
            baseDeDatos.cerrarConexion();
        }
    }
}
    public void modificarUsuarioEnBD() {
    BaseDeDatos baseDeDatos = new BaseDeDatos();
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        // Establecer la conexión a la base de datos
        baseDeDatos.getConexion();
        conn = baseDeDatos.getConexion();

        // Crear la sentencia SQL para la modificación
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, nombre_usuario = ?, contraseña = ? WHERE run = ?";

        // Crear el PreparedStatement
        stmt = conn.prepareStatement(sql);

        // Establecer los parámetros de la sentencia SQL
        stmt.setString(1, nombre);
        stmt.setString(2, apellido);
        stmt.setString(3, nombreUsuario);
        stmt.setString(4, contraseña);
        stmt.setString(6, run);

        // Ejecutar la modificación
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al modificar usuario en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar la conexión usando el método de BaseDeDatos
        if (baseDeDatos != null) {
            baseDeDatos.cerrarConexion();
        }
    }
}

public void eliminarUsuarioEnBD() {
    BaseDeDatos baseDeDatos = new BaseDeDatos();
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        // Establecer la conexión a la base de datos
        baseDeDatos.getConexion();
        conn = baseDeDatos.getConexion();

        // Crear la sentencia SQL para la eliminación
        String sql = "DELETE FROM usuarios WHERE run = ?";

        // Crear el PreparedStatement
        stmt = conn.prepareStatement(sql);

        // Establecer el parámetro de la sentencia SQL
        stmt.setString(1, run);

        // Ejecutar la eliminación
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar usuario en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar la conexión usando el método de BaseDeDatos
        if (baseDeDatos != null) {
            baseDeDatos.cerrarConexion();
        }
    }
}
public void encriptarContraseña() throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hashBytes = md.digest(contraseña.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : hashBytes) {
        sb.append(String.format("%02x", b));
    }
    contraseña = sb.toString();
}
}