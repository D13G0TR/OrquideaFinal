package principal;

import Controlador.ControladorOrquideas;
import Modelo.BaseDeDatos;
import Vista.VistaLogin;
import Vista.VistaPrincipal;
import Vista.VistaRegistro;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class main {
    public static void main(String[] args) {
        try {
            // Inicializar la base de datos
            BaseDeDatos db = new BaseDeDatos();
            db.conectar();

            // Crear instancia de VistaRegistro
            VistaRegistro vistaRegistro = new VistaRegistro();

            // Crear y mostrar la vista de login, pasando la vista de registro
            VistaLogin login = new VistaLogin(vistaRegistro);
            login.setVisible(true);

            // Agregar un WindowListener para detectar cuando se cierre la ventana
            login.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Verificar el estado del login
                    if (login.fueLoginExitoso()) {
                        // Login exitoso, proceder con la vista principal
                        VistaPrincipal vista = new VistaPrincipal();

                        // Inicializar el controlador
                        new ControladorOrquideas(db, vista);

                        // Mostrar la vista principal
                        vista.setVisible(true);
                    } else {
                        // Login fallido, cerrar aplicación
                        System.out.println("Login fallido. Cerrando aplicación.");
                        System.exit(0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}