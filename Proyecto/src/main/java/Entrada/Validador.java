package Entrada;

import com.itextpdf.text.Image;
import com.toedter.calendar.JCalendar;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.*;

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

    public static boolean validarNombre(String nombre, JLabel lblError) {
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarError(lblError, "El campo no puede estar vacío.");
            return false;
        }
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            mostrarError(lblError, "El campo solo debe contener letras.");
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
            mostrarError(lblError, "El correo debe contener '@'.");
            return false;
        }

        lblError.setText("");
        return true;
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

    public static boolean verificarCredencial(String correo, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
        try (Connection conn = ConexionMySQL.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    public static void mostrarError(JLabel lblError, String mensaje) {
        lblError.setText(mensaje);
        lblError.setForeground(Color.RED);
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

    public static void enviarPDFPorCorreo(Usuario usuario, String rutaPDF) {
    try {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
        documento.open();
        try {
            String rutaImagen = "C:\\Users\\Hola\\Documents\\NetBeansProjects\\Proyecto\\img\\sol-ogo.png";  
            File archivoImagen = new File(rutaImagen);

            if (!archivoImagen.exists()) {
                System.out.println("ERROR: Imagen no encontrada: " + archivoImagen.getAbsolutePath());
                documento.add(new Paragraph("Imagen no disponible.", FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.RED)));
            } else {
                Image imagen = Image.getInstance(archivoImagen.getAbsolutePath());
                imagen.scaleToFit(200, 200);
                imagen.setAlignment(Element.ALIGN_CENTER);
                documento.add(imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
            documento.add(new Paragraph("Error al cargar la imagen.", FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.RED)));
        }

        Paragraph bienvenido = new Paragraph("Bienvenido", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
        bienvenido.setAlignment(Element.ALIGN_CENTER);
        documento.add(bienvenido);

        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("Datos del usuario:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        documento.add(new Paragraph("Nombre: " + usuario.getNombre()));
        documento.add(new Paragraph("Apellido paterno: " + usuario.getAPaterno()));
        documento.add(new Paragraph("Apellido materno: " + usuario.getAMaterno()));
        documento.add(new Paragraph("Correo: " + usuario.getCorreo()));
        documento.add(new Paragraph("Fecha de nacimiento: " + new SimpleDateFormat("dd/MM/yyyy").format(usuario.getFechaN())));
        documento.add(new Paragraph("Edad: " + usuario.getEdad()));
        documento.add(new Paragraph("Género: " + usuario.getGenero()));

        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Gracias por registrarte.", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12)));

        documento.close();
    } catch (Exception e) {
        e.printStackTrace();
        return;
    }

        // 2. Enviar el correo con el PDF adjunto
        final String remitente = "tucorreo@gmail.com";        // tu cuenta Gmail
        final String clave = "tu_contraseña_de_app";          // contraseña de aplicación

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getCorreo()));
            mensaje.setSubject("Confirmación de Registro");

            BodyPart texto = new MimeBodyPart();
            texto.setText("Adjunto encontrarás un PDF con los datos de tu registro. ¡Gracias!");

            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.attachFile(rutaPDF);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);
            multipart.addBodyPart(adjunto);

            mensaje.setContent(multipart);
            Transport.send(mensaje);

            System.out.println("Correo enviado a " + usuario.getCorreo());

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
    public static String enviarCodigo(String correoDestino) {
        String codigo = String.valueOf((int) (Math.random() * 9000 + 1000)); 

        final String remitente = "tucorreo@gmail.com";
        final String clave = "tu_contraseña_de_app";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            mensaje.setSubject("Código de recuperación de contraseña");
            mensaje.setText("Tu código de recuperación es: " + codigo);

            Transport.send(mensaje);
            return codigo;

        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean actualizarContrasena(String correo, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE correo = ?";

        try (Connection conn = ConexionMySQL.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevaContrasena);
            stmt.setString(2, correo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
