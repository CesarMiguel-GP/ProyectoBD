package Entrada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;

import com.toedter.calendar.JCalendar;

public class Validador {

    public static boolean validarCorreo(String correo, JLabel lblError) {
        correo = correo.toLowerCase().trim();
        String dominios = "gmail|hotmail|yahoo|outlook|itoaxaca|live|icloud|aol";
        String terminaciones = "com|mx|edu.mx|org|net|gov|info";
        String regex = "^[a-zA-Z0-9._%+-]+@(" + dominios + ")\\.(" + terminaciones + ")$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);

        if (!matcher.matches()) {
            mostrarError(lblError, "Correo inválido. Usa un dominio válido.");
            return false;
        }
        lblError.setText("");
        return true;
    }

    public static boolean validarContrasena(String contrasena, JLabel lblError) {
        if (contrasena.length() < 8) {
            mostrarError(lblError, "La contraseña debe tener al menos 8 caracteres.");
            return false;
        }
        lblError.setText("");
        return true;
    }

    public static boolean InicializarLogin(String correo, String contra, JLabel lblError) {
        if ((correo == null || correo.isEmpty()) && (contra == null || contra.isEmpty())) {
            mostrarError(lblError, "Ingrese datos.");
            return false;
        }
        if (correo == null || correo.isEmpty()) {
            mostrarError(lblError, "El correo no puede estar vacío.");
            return false;
        }
        if (contra == null || contra.isEmpty()) {
            mostrarError(lblError, "La contraseña no puede estar vacía.");
            return false;
        }
        lblError.setText("");
        return true;
    }
    
    public static boolean InicializarRegistro(
        String correo, String contrasena, String confirmarContrasena,
        String fechaNacimiento, String nombre, String apellidoPaterno,
        String apellidoMaterno, ButtonGroup genero, JLabel lblError) {

        if (correo.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty() ||
            fechaNacimiento.isEmpty() || nombre.isEmpty() || apellidoPaterno.isEmpty() ||
            apellidoMaterno.isEmpty() || genero.getSelection() == null) {

            mostrarError(lblError, "Todos los campos son obligatorios.");
            return false;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            mostrarError(lblError, "Las contraseñas no coinciden.");
            return false;
        }

        if (!correo.contains("@")) {
            mostrarError(lblError, "El correo debe tener @.");
            return false;
        }

        lblError.setText(""); 
        return true;
    }

    
    public static boolean verificarCredencial(String correo, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
        try (Connection conn = ConexionMySQL.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true si encuentra un usuario válido

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellido_paterno, apellido_materno, correo, contrasena, fecha_nacimiento, edad, genero) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getAPaterno());
            stmt.setString(3, usuario.getAMaterno());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getContraseña());
            stmt.setDate(6, new java.sql.Date(usuario.getFechaN().getTime()));
            stmt.setInt(7, usuario.getEdad());
            stmt.setString(8, usuario.getGenero());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validarNombre(String nombre, JLabel lblError) {
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarError(lblError, "El nombre no puede estar vacío.");
            return false;
        }
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            mostrarError(lblError, "El nombre solo debe contener letras.");
            return false;
        }
        lblError.setText("");
        return true;
    }

    public static void mostrarError(JLabel lblError, String mensaje) {
        lblError.setText(mensaje);
        lblError.setForeground(Color.RED);
    }

    public static int calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) return -1;

        java.util.Calendar fechaNac = java.util.Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);

        java.util.Calendar hoy = java.util.Calendar.getInstance();

        int edad = hoy.get(java.util.Calendar.YEAR) - fechaNac.get(java.util.Calendar.YEAR);

        if (hoy.get(java.util.Calendar.DAY_OF_YEAR) < fechaNac.get(java.util.Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        return edad;
    }

    public static Date convertirFecha(String fechaStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(fechaStr);
        } catch (Exception e) {
            return null;
        }
    }
    public static void confirmarSalida(JFrame frame) {
        int opcion = JOptionPane.showConfirmDialog(
            frame,
            "¿Deseas salir del programa?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); 
        }
    }
    // Clase para seleccionar fecha con JCalendar (ya integrada)
    public static class FechaSelector extends JDialog {
        private JCalendar calendario;
        private JButton btnAceptar;
        private JTextField txtFechaN;

        public FechaSelector(JFrame parent, JTextField txtFechaN) {
            super(parent, "Seleccionar Fecha", true);
            this.txtFechaN = txtFechaN;

            calendario = new JCalendar();
            calendario.setPreferredSize(new Dimension(400, 300));

            btnAceptar = new JButton("Aceptar");
            btnAceptar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Date fechaSeleccionada = calendario.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaStr = sdf.format(fechaSeleccionada);
                    txtFechaN.setText(fechaStr);
                    dispose();
                }
            });

            setLayout(new BorderLayout());
            add(calendario, BorderLayout.CENTER);
            add(btnAceptar, BorderLayout.SOUTH);

            setSize(400, 400);
            setLocationRelativeTo(parent);
        }
    }
}
