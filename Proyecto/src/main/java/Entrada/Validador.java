/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entrada;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Calendar;

public class Validador {
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    
    public static boolean validarCorreo(String correo, JLabel lblError) {
        correo = correo.toLowerCase().trim();
        String dominios = "gmail|hotmail|yahoo|outlook|itoaxaca|live|icloud|aol";
        String terminaciones = "com|mx|edu.mx|org|net|gov|info";
        String regex = "^[a-zA-Z0-9._%+-]+@(" + dominios + ")\\.(" + terminaciones + ")$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);

        if (!matcher.matches()) {
            mostrarError(lblError, "Correo inválido.Usa un dominio y terminación válida.");
            return false;
        }

        lblError.setText(""); 
        return true;
    }

    public static boolean validarContrasena(String contrasena, JLabel lblError) {
        if ( contrasena.length() < 8) {
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
    
    public static boolean InicializarRegistro(
        String correo, String contrasena, String confirmarContrasena,
        String fechaNacimiento, String nombre, String apellidoPaterno, 
        String apellidoMaterno, ButtonGroup genero, JLabel lblError) {

        if (correo.isEmpty() && contrasena.isEmpty() && confirmarContrasena.isEmpty() &&
            fechaNacimiento.isEmpty() && nombre.isEmpty() && apellidoPaterno.isEmpty() && 
            apellidoMaterno.isEmpty() && genero.getSelection() == null) {

            mostrarError(lblError, "Ingrese todos los datos.");
            return false;
        }

        if (correo.isEmpty()) {
            mostrarError(lblError, "El correo no puede estar vacío.");
            return false;
        }
        if (contrasena.isEmpty()) {
            mostrarError(lblError, "La contraseña no puede estar vacía.");
            return false;
        }
        if (confirmarContrasena.isEmpty()) {
            mostrarError(lblError, "Debe confirmar la contraseña.");
            return false;
        }
        if (!contrasena.equals(confirmarContrasena)) {
            mostrarError(lblError, "Las contraseñas no coinciden.");
            return false;
        }
        if (fechaNacimiento.isEmpty()) {
            mostrarError(lblError, "Seleccione una fecha de nacimiento.");
            return false;
        }
        if (nombre.isEmpty()) {
            mostrarError(lblError, "El nombre no puede estar vacío.");
            return false;
        }
        if (apellidoPaterno.isEmpty()) {
            mostrarError(lblError, "El apellido paterno no puede estar vacío.");
            return false;
        }
        if (apellidoMaterno.isEmpty()) {
            mostrarError(lblError, "El apellido materno no puede estar vacío.");
            return false;
        }
        if (genero.getSelection() == null) {
            mostrarError(lblError, "Debe seleccionar un género.");
            return false;
        }

        lblError.setText("");
        return true;
    }

    public static boolean validarNombre(String nombre, JLabel lblError) {
        if (nombre == null || nombre.trim().isEmpty()) {
            lblError.setText("El nombre no puede estar vacío.");
            return false;
        }

        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            lblError.setText("El nombre solo debe contener letras.");
            return false;
        }

        lblError.setText("");
        return true;
    }

    
    public static void mostrarError(JLabel lblError, String mensaje) {
        lblError.setText(mensaje);
        lblError.setForeground(Color.RED);
    }
    

    public static void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }
    public static boolean verificarCredencial(String correo, String contrasena) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getContraseña().equals(contrasena)) {
                return true; 
            }
        }
        return false; 
    }
    public static String obtenerUsuariosRegistrados() {
        if (listaUsuarios.isEmpty()) {
            return "No hay usuarios registrados.";
        }

        StringBuilder info = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Usuario usuario : listaUsuarios) {
            info.append("Nombre: ").append(usuario.getNombre()).append(" ").append(usuario.getAPaterno()).append(" ").append(usuario.getAMaterno()).append("\n")
                .append("Correo: ").append(usuario.getCorreo()).append("\n")
                .append("Fecha de Nacimiento: ").append(sdf.format(usuario.getFechaN())).append("\n")
                .append("Edad: ").append(usuario.getEdad()).append("\n")
                .append("Género: ").append(usuario.getGenero()).append("\n")
                .append("Contraseña: ").append(usuario.getContraseña()).append("\n")   
                .append("-------------------------\n");
        }

        return info.toString();
    }
    public static int calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) return -1; 

        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);

        Calendar hoy = Calendar.getInstance();
        
        int edad = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        if (hoy.get(Calendar.DAY_OF_YEAR) < fechaNac.get(Calendar.DAY_OF_YEAR)) {
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

     public static class FechaSelector extends JDialog {
        private JCalendar calendario;
        private JButton btnAceptar;
        private JTextField txtFechaN;

        public FechaSelector(JFrame parent, JTextField txtFechaN) {
            super(parent, "Seleccionar Fecha", true);
            this.txtFechaN = txtFechaN;

            calendario = new JCalendar();
            calendario.setPreferredSize(new Dimension(400, 300));

            // Botón Aceptar
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


