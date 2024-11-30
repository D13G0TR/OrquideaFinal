package principal;

import Controlador.ControladorOrquideas;
import Modelo.BaseDeDatos;
import Vista.VistaPrincipal;

public class main {
    public static void main(String[] args) {
        try {
            // Inicializar la base de datos
            BaseDeDatos db = new BaseDeDatos();
            db.conectar();

            // Inicializar la vista
            VistaPrincipal vista = new VistaPrincipal();

            // Inicializar el controlador
            new ControladorOrquideas(db, vista);

            // Mostrar la interfaz gráfica
            vista.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
