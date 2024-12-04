package Vista;

import Modelo.BaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VistaRegistro extends javax.swing.JFrame {

    private javax.swing.JLabel jlabel10;

    public VistaRegistro() {
        initComponents();
        jLabel10 = new javax.swing.JLabel();
        getContentPane().add(jLabel10);
        jLabel10.setBounds(355, 57, 200, 20);
        this.setTitle("monitoreo de plantas");
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        Registrar = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        VolverLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("RUN");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Usuario");

        jLabel5.setText("Contraseña");

        jTextField1.setActionCommand("<Not Set>");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        Registrar.setText("Registrar");
        Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarActionPerformed(evt);
            }
        });

        jLabel6.setText("REGISTRATE");

        jLabel7.setText("-");
        jLabel7.setMaximumSize(new java.awt.Dimension(5, 20));

        VolverLogin.setText("Volver");
        VolverLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel6)
                                .addGap(85, 85, 85))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(VolverLogin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(288, 288, 288))))
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(Registrar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Registrar)
                    .addComponent(VolverLogin))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed
    String run = jTextField1.getText().trim();
    String nombre = jTextField2.getText().trim();
    String apellido = jTextField3.getText().trim();
    String nombreUsuario = jTextField4.getText().trim();
    String contraseña = new String(jPasswordField1.getPassword()).trim();

    // Validación de datos
    if (run.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || nombreUsuario.isEmpty() || contraseña.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Crear instancia de BaseDeDatos
    BaseDeDatos baseDeDatos = new BaseDeDatos();
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        baseDeDatos.conectar(); // Establece la conexión
        conn = baseDeDatos.getConexion(); // Obtiene la conexión

        stmt = conn.prepareStatement("INSERT INTO usuarios (run, digito_verificador, nombre, apellido, nombre_usuario, contraseña) VALUES (?, ?, ?, ?, ?, ?)");
        
        // Calcular el dígito verificador
        char digitoVerificador = calcularDigitoVerificador(run);
        
        // Uso de parámetros
        stmt.setString(1, run);
        stmt.setString(2, String.valueOf(digitoVerificador));
        stmt.setString(3, nombre);
        stmt.setString(4, apellido);
        stmt.setString(5, nombreUsuario);
        stmt.setString(6, encriptarContraseña(contraseña));
        
        // Uso de transacciones
        conn.setAutoCommit(false);
        stmt.executeUpdate();
        conn.commit();
        
        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");

        // Cerrar la ventana de registro
        this.dispose(); // Cierra la ventana actual

        // Iniciar la ventana de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaLogin().setVisible(true);
            }
        });
        
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); } catch (SQLException rollbackEx) {
                JOptionPane.showMessageDialog(this, "Error al revertir cambios: " + rollbackEx.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar el PreparedStatement, pero no la conexión
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cerrar el PreparedStatement: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
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

private String encriptarContraseña(String contraseña) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(contraseña.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
    }//GEN-LAST:event_RegistrarActionPerformed
    
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // Método para calcular y mostrar el dígito verificador                                     
        String run = jTextField1.getText().trim();
    
        // Verificar si el RUN tiene exactamente 8 caracteres y son todos números
        if (!validarRun(run)) {
            jLabel10.setText("No valido");
        } else {
            jLabel10.setText("");
    }
 
        // Calcular el dígito verificador
        int sum = 0;
        int factor = 2;
        for (int i = run.length() - 1; i >= 0; i--) {
        sum += Integer.parseInt(String.valueOf(run.charAt(i))) * factor;
        factor = factor == 7 ? 2 : factor + 1;
    }
        int remainder = sum % 11;
        int verificador = 11 - remainder;
    
        // Mostrar el dígito verificador en el JLabel
        if (verificador == 11) {
            jLabel9.setText("0");
        } else if (verificador == 10) {
            jLabel9.setText("K");
        } else {
            jLabel9.setText(String.valueOf(verificador));
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void VolverLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverLoginActionPerformed
     // Ocultar la vista actual (VistaRegistro)
    this.setVisible(false);
    
    // Crear una instancia de VistaLogin y pasarle la instancia de VistaRegistro
    VistaLogin vistaLogin = new VistaLogin(this);
    
    // Hacer visible la vista de inicio de sesión (vistaLogin)
    vistaLogin.setVisible(true);
    }//GEN-LAST:event_VolverLoginActionPerformed
    // Método para validar el RUN
    private boolean validarRun(String run) {
    return run.matches("[0-9]{8}");
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Crear una instancia de VistaRegistro
            VistaRegistro vistaRegistro = new VistaRegistro();
            
            // Pasar la instancia de VistaRegistro al constructor de VistaLogin
            VistaLogin vistaLogin = new VistaLogin(vistaRegistro);
            
            // Hacer visible la instancia de VistaLogin
            vistaLogin.setVisible(true);
            }
        });
    } 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Registrar;
    private javax.swing.JButton VolverLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}