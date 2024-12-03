package Controlador;

import Modelo.BaseDeDatos;
import Vista.VistaPrincipal;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Random;
import javax.swing.Timer;

public class ControladorOrquideas {
    private BaseDeDatos db;
    private VistaPrincipal vista;

    public ControladorOrquideas(BaseDeDatos db, VistaPrincipal vista) {
        this.db = db;
        this.vista = vista;

        // Vincular eventos
        vista.btnGuardarPlanta.addActionListener(e -> guardarPlanta());
        vista.btnGuardarRiego.addActionListener(e -> programarRiego());

        configurarHorasRiego();
        
        cargarPlantasComboBox();
        
        // Cargar los datos en la tabla al iniciar
        cargarTablaPlantas();
        
        Timer timerSensores = new Timer(5000, e -> simularSensores());
        timerSensores.start();
        
        
        new javax.swing.Timer(60000, e -> actualizarEstadosPlantas()).start();
    }

    private void guardarPlanta() {
    try {
        String nombre = vista.txtNombrePlanta.getText();

        if (nombre.isEmpty()) {
            vista.mostrarMensaje("El nombre de la planta no puede estar vacío.");
            return;
        }

        // Guardar la planta en la base de datos
        db.guardarNombrePlanta(nombre);
        vista.mostrarMensaje("Planta guardada correctamente.");
        vista.txtNombrePlanta.setText("");

        // Actualizar la tabla de plantas
        cargarTablaPlantas();

        // Actualizar el ComboBox de programación de riego
        cargarPlantasComboBox();

    } catch (Exception e) {
        vista.mostrarMensaje("Error al guardar la planta: " + e.getMessage());
    }
}


    private void cargarTablaPlantas() {
    try {
        // Obtener los datos desde la base de datos
        var plantas = db.obtenerPlantas();

        // Crear el modelo de la tabla con los encabezados correctos
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha de Riego");
        modelo.addColumn("Hora de Riego");
        modelo.addColumn("Humedad");
        modelo.addColumn("Temperatura");
        modelo.addColumn("Estado");

        // Agregar las filas al modelo asegurando el orden correcto
        for (String[] planta : plantas) {
            // Imprimir para depuración (puedes eliminar esta línea si no es necesaria)
            System.out.println("Planta cargada: " + java.util.Arrays.toString(planta));

            // Validar que el arreglo tiene al menos 7 elementos y manejar valores faltantes
            String id = planta.length > 0 ? planta[0] : "";
            String nombre = planta.length > 1 ? planta[1] : "";
            String fechaRiego = planta.length > 2 ? planta[2] : "";
            String horaRiego = planta.length > 3 ? planta[3] : "";
            String humedad = planta.length > 4 ? planta[4] : "";
            String temperatura = planta.length > 5 ? planta[5] : "";
            String estado = planta.length > 6 ? planta[6] : "";

            // Agregar la fila con los valores procesados
            modelo.addRow(new Object[] { id, nombre, fechaRiego, horaRiego, humedad, temperatura, estado });
        }

        // Asignar el modelo a la tabla
        vista.tblPlantas.setModel(modelo);
    } catch (Exception e) {
        vista.mostrarMensaje("Error al cargar los datos: " + e.getMessage());
    }
}




    private void programarRiego() {
        try {
            // Obtener la planta seleccionada desde el ComboBox
            String plantaSeleccionada = (String) vista.cmbPlantas.getSelectedItem();
            if (plantaSeleccionada == null) {
                vista.mostrarMensaje("Seleccione una planta.");
                return;
            }

            // Obtener el ID de la planta desde el nombre
            int plantaId = obtenerIdPlanta(plantaSeleccionada);

            // Obtener la fecha seleccionada desde el JDateChooser
            java.util.Date fechaSeleccionada = vista.calendarioRiego.getDate();
            if (fechaSeleccionada == null) {
                vista.mostrarMensaje("Seleccione una fecha.");
                return;
            }
            java.sql.Date fechaSql = new java.sql.Date(fechaSeleccionada.getTime());

            // Obtener la hora seleccionada desde el ComboBox
            String horaTexto = (String) vista.cmbHoraRiego.getSelectedItem();
            if (horaTexto == null) {
                vista.mostrarMensaje("Seleccione una hora.");
                return;
            }
            java.sql.Time horaSql = java.sql.Time.valueOf(horaTexto + ":00");

            // Guardar en la base de datos
            db.guardarRiego(plantaId, fechaSql, horaSql);
            vista.mostrarMensaje("Riego programado correctamente.");

            // Actualizar la tabla automáticamente después de guardar
            cargarTablaPlantas();
        } catch (Exception e) {
            vista.mostrarMensaje("Error al programar el riego: " + e.getMessage());
        }
    }

    private int obtenerIdPlanta(String nombrePlanta) throws Exception {
        // Consulta SQL para obtener el ID de la planta
        String query = "SELECT id FROM orquidea WHERE nombre = ?";

        // Preparar la consulta
        PreparedStatement stmt = db.getConexion().prepareStatement(query);
        stmt.setString(1, nombrePlanta);

        // Ejecutar la consulta
        ResultSet rs = stmt.executeQuery();

        // Si hay un resultado, devolver el ID
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new Exception("Planta no encontrada.");
        }
    }

    private void configurarHorasRiego() {
        vista.cmbHoraRiego.addItem("08:00");
        vista.cmbHoraRiego.addItem("08:30");
        vista.cmbHoraRiego.addItem("09:00");
        vista.cmbHoraRiego.addItem("09:30");
        vista.cmbHoraRiego.addItem("10:00");
        vista.cmbHoraRiego.addItem("10:30");
        vista.cmbHoraRiego.addItem("11:00");
        vista.cmbHoraRiego.addItem("11:30");
        vista.cmbHoraRiego.addItem("12:00");
        vista.cmbHoraRiego.addItem("12:30");
        vista.cmbHoraRiego.addItem("13:00");
        vista.cmbHoraRiego.addItem("13:30");
        vista.cmbHoraRiego.addItem("14:00");
        vista.cmbHoraRiego.addItem("14:30");
        vista.cmbHoraRiego.addItem("15:00");
        vista.cmbHoraRiego.addItem("15:30");
        vista.cmbHoraRiego.addItem("16:00");
        vista.cmbHoraRiego.addItem("16:30");
        vista.cmbHoraRiego.addItem("17:00");
        vista.cmbHoraRiego.addItem("17:30");
        vista.cmbHoraRiego.addItem("18:00");
        vista.cmbHoraRiego.addItem("18:30");
        vista.cmbHoraRiego.addItem("19:00");
        vista.cmbHoraRiego.addItem("19:30");
        vista.cmbHoraRiego.addItem("20:00");
        vista.cmbHoraRiego.addItem("20:30");
        vista.cmbHoraRiego.addItem("21:00");
        vista.cmbHoraRiego.addItem("21:30");
        vista.cmbHoraRiego.addItem("22:00");
        vista.cmbHoraRiego.addItem("22:30");
        vista.cmbHoraRiego.addItem("23:00");
        vista.cmbHoraRiego.addItem("23:30");
        vista.cmbHoraRiego.addItem("00:00");

    }
    
    
    
    // Método para gestionar el cambio de estado según la hora actual
private void actualizarEstadosPlantas() {
    try {
        var plantas = db.obtenerPlantas();
        LocalDateTime ahora = LocalDateTime.now();

        for (String[] planta : plantas) {
            int plantaId = Integer.parseInt(planta[0]);
            String fechaRiego = planta[2];
            String horaRiego = planta[3];

            if (!fechaRiego.isEmpty() && !horaRiego.isEmpty()) {
                LocalDateTime fechaHoraRiego = LocalDateTime.parse(fechaRiego + "T" + horaRiego);

                if (ahora.isBefore(fechaHoraRiego)) {
                    db.actualizarEstado(plantaId, "Esperando Riego");
                } else if (ahora.isAfter(fechaHoraRiego) && ahora.isBefore(fechaHoraRiego.plusMinutes(2))) {
                    db.actualizarEstado(plantaId, "Regando");
                } else if (ahora.isAfter(fechaHoraRiego.plusMinutes(2)) && ahora.isBefore(fechaHoraRiego.plusMinutes(30))) {
                    db.actualizarEstado(plantaId, "Regado");
                } else if (ahora.isAfter(fechaHoraRiego.plusMinutes(30))) {
                    db.actualizarEstado(plantaId, "Esperando Riego");
                }
            }
        }

        cargarTablaPlantas(); // Refresca la tabla
        // Mueve el mensaje aquí:
        System.out.println("Estados de las plantas actualizados.");
    } catch (Exception e) {
        vista.mostrarMensaje("Error al actualizar estados: " + e.getMessage());
    }
}



    private void cargarPlantasComboBox() {
    try {
        // Obtener la lista de plantas desde la base de datos
        var plantas = db.obtenerPlantas();

        // Limpiar el ComboBox antes de agregar nuevos elementos
        vista.cmbPlantas.removeAllItems();

        // Llenar el ComboBox con los nombres de las plantas
        for (String[] planta : plantas) {
            vista.cmbPlantas.addItem(planta[1]); // Agrega solo el nombre de la planta
        }
    } catch (Exception e) {
        vista.mostrarMensaje("Error al cargar las plantas: " + e.getMessage());
    }
    }
    
    private void simularSensores() {
    Random random = new Random();
    try {
        // Obtener la lista de plantas desde la base de datos
        var plantas = db.obtenerPlantas();

        // Simular datos para cada planta
        for (String[] planta : plantas) {
            int plantaId = Integer.parseInt(planta[0]); 
            int humedad = random.nextInt(101); 
            double temperatura = 20 + random.nextDouble() * 10; 

            // Actualizar los valores en la base de datos
            db.actualizarSensores(plantaId, humedad, temperatura);
        }

        // Actualizar la tabla
        cargarTablaPlantas(); 

    } catch (Exception e) {
        vista.mostrarMensaje("Error al simular sensores: " + e.getMessage());
    }
}
    
    
}

