/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entrada;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

public class Productos extends javax.swing.JFrame {
    private JPanel panelProductos;
    private ArrayList<Producto> carrito = new ArrayList<>();
    private ArrayList<ProductoCompleto> listaProductos = new ArrayList<>();
    private JPanel panelCarrito;
    private JPopupMenu popupMenu;
    private JPanel productoSeleccionado; // Panel del producto actualmente seleccionado
    private ProductoCompleto productoActual; // Producto actualmente seleccionado
    
    /**
     * Creates new form Productos
     */
    public Productos() {
        setTitle("Catálogo de Productos - Abarrotes La Soledad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Crear el menú contextual
        crearPopupMenu();

        // Fuente personalizada
        Font fuenteTitulo = new Font("SansSerif", Font.BOLD, 24);

        // Panel superior con logo, título y carrito
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(255, 255, 255));

        // Logo
        try {
            URL logoURL = new URL("https://www.abarroteslasoledad.com.mx/images/sol-ogo.png");
            ImageIcon logoIcon = new ImageIcon(logoURL);
            JLabel logo = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
            panelSuperior.add(logo, BorderLayout.WEST);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Título
        JLabel titulo = new JLabel("Bienvenido al catálogo de productos");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(new Color(34, 139, 34));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelSuperior.add(titulo, BorderLayout.CENTER);

        // Carrito
        try {
            URL carritoURL = new URL("https://img.freepik.com/vector-premium/cesta-compra-carro-forma-abstracta-ilustracion-vectorial_573942-1535.jpg");
            ImageIcon carritoIcon = new ImageIcon(carritoURL);
            JLabel iconCarrito = new JLabel(new ImageIcon(carritoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            iconCarrito.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconCarrito.setToolTipText("Ver carrito");

            iconCarrito.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    mostrarCarrito();
                }
            });

            panelSuperior.add(iconCarrito, BorderLayout.EAST);
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(panelSuperior, BorderLayout.NORTH);

        // Panel de productos con scroll
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 4, 15, 15));
        panelProductos.setBackground(Color.WHITE);
        
        // Agregar el listener para el menú contextual en el panel de productos
        panelProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(panelProductos);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Lista de URLs
        String[] urls = {
            "https://www.centralenlinea.com/images/thumbs/001/0011202_palomitas-para-microondas-act-ii-sabor-natural-3-bolsas-de-80g-cu_550.jpeg",
            "https://calimaxmx.vtexassets.com/arquivos/ids/202300/8076809523714.jpg?v=638050233816770000",
            "https://quaker.lat/mx/sites/default/files/2023-07/750047802766_720x840.png",
            "https://i5.walmartimages.com.mx/gr/images/product-images/img_large/00750107931001L.jpg",
            "https://images.ctfassets.net/ypovj6i6xh7f/36DQug0XPyv5gzc5KbYX3Y/06eef671762d680caace1fb1336a65e8/80812742_Ehub_PI_Thalia_NATURELLA_NOCTURNA_T5_C-A__32_10_X3IT.png",
            "https://hebmx.vtexassets.com/arquivos/ids/608363/469122_622577895.jpg?v=638521586338400000",
            "https://arteli.vtexassets.com/arquivos/ids/266118/7501060500071_00.jpg?v=638635835122900000",
            "https://chedrauimx.vtexassets.com/arquivos/ids/46782826/7501060500019_00.jpg?v=638798522780530000",
            "https://lagranbodega.vteximg.com.br/arquivos/ids/279431-1000-1000/7501943496101.jpg?v=637254352932470000",
            "https://chedrauimx.vtexassets.com/arquivos/ids/46794947/7501017002115_00.jpg?v=638798554527900000",
            "https://chedrauimx.vtexassets.com/arquivos/ids/45935773/7501144990026_00.jpg?v=638787865167270000",
            "https://i5.walmartimages.com.mx/gr/images/product-images/img_large/00750100310547L.jpg",
            "https://i5.walmartimages.com.mx/gr/images/product-images/img_large/00750100310548L.jpg",
            "https://i5.walmartimages.com.mx/gr/images/product-images/img_large/00750105380001L.jpg",
            "https://i5.walmartimages.com.mx/gr/images/product-images/img_large/00750101700561L.jpg",
            "https://www.superaki.mx/cdn/shop/products/7501003305831_200721.png?v=1633366697",
            "https://m.media-amazon.com/images/I/716TUvbOJZL._AC_UF1000,1000_QL80_.jpg",
            "https://www.azucardulcerias.com/cdn/shop/files/5e6a7521ad05ab0e84446a6cb451ab92.png?v=1743630844",
            "https://m.media-amazon.com/images/I/61yiVElQCJL._AC_UF1000,1000_QL80_.jpg"
        };

        for (String url : urls) {
            agregarProductoExistente(url);
        }

        setVisible(true);
    }

    private void crearPopupMenu() {
        popupMenu = new JPopupMenu();
        
        JMenuItem menuAgregar = new JMenuItem("Agregar");
        JMenuItem menuEditar = new JMenuItem("Editar");
        JMenuItem menuEliminar = new JMenuItem("Eliminar");
        JMenuItem menuVisualizar = new JMenuItem("Visualizar");
        
        // Agregar acción para el botón "Agregar"
        menuAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoProducto();
            }
        });
        
        // Agregar acción para el botón "Editar"
        menuEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productoActual != null) {
                    editarProducto(productoActual);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para editar", 
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        // Agregar acción para el botón "Eliminar"
        menuEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productoActual != null && productoSeleccionado != null) {
                    eliminarProducto(productoActual, productoSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar", 
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        // Agregar acción para el botón "Visualizar"
        menuVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productoActual != null) {
                    visualizarProducto(productoActual);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para visualizar", 
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        // Agregar los elementos al menú
        popupMenu.add(menuAgregar);
        popupMenu.add(menuEditar);
        popupMenu.add(menuEliminar);
        popupMenu.add(menuVisualizar);
    }
    
    private void agregarNuevoProducto() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JTextField idField = new JTextField(10);
        JTextField nombreField = new JTextField(20);
        JTextField tipoField = new JTextField(15);
        JTextField descripcionField = new JTextField(30);
        JTextField precioField = new JTextField(10);
        JTextField urlField = new JTextField(50);
        
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nombre del producto:"));
        panel.add(nombreField);
        panel.add(new JLabel("Tipo de producto:"));
        panel.add(tipoField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descripcionField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("URL de la imagen:"));
        panel.add(urlField);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Nuevo Producto", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText();
                String nombre = nombreField.getText();
                String tipo = tipoField.getText();
                String descripcion = descripcionField.getText();
                double precio = Double.parseDouble(precioField.getText());
                String url = urlField.getText();
                
                // Verificar que la URL es válida
                new URL(url);
                
                // Crear un nuevo producto y agregarlo a la lista
                ProductoCompleto nuevoProducto = new ProductoCompleto(id, nombre, tipo, descripcion, precio, url, 0);
                listaProductos.add(nuevoProducto);
                
                // Agregar el producto al panel
                JPanel nuevoPanel = agregarProductoAlPanel(url, nombre, precio);
                
                // Asociar el producto con su panel
                asociarProductoConPanel(nuevoPanel, nuevoProducto);
                
                JOptionPane.showMessageDialog(null, "Producto registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Consulta base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private void editarProducto(ProductoCompleto producto) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JTextField idField = new JTextField(producto.id, 10);
        JTextField nombreField = new JTextField(producto.nombre, 20);
        JTextField tipoField = new JTextField(producto.tipo, 15);
        JTextField descripcionField = new JTextField(producto.descripcion, 30);
        JTextField precioField = new JTextField(String.valueOf(producto.precio), 10);
        JTextField urlField = new JTextField(producto.url, 50);
        
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nombre del producto:"));
        panel.add(nombreField);
        panel.add(new JLabel("Tipo de producto:"));
        panel.add(tipoField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descripcionField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("URL de la imagen:"));
        panel.add(urlField);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Producto", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText();
                String nombre = nombreField.getText();
                String tipo = tipoField.getText();
                String descripcion = descripcionField.getText();
                double precio = Double.parseDouble(precioField.getText());
                String url = urlField.getText();
                
                // Verificar que la URL es válida
                new URL(url);
                
                // Actualizar datos del producto
                producto.id = id;
                producto.nombre = nombre;
                producto.tipo = tipo;
                producto.descripcion = descripcion;
                producto.precio = precio;
                producto.url = url;
                
                // Actualizar el panel del producto
                if (productoSeleccionado != null) {
                    productoSeleccionado.removeAll();
                    
                    // Re-crear el panel con los nuevos datos
                    ImageIcon icono = new ImageIcon(new URL(url));
                    Image imagen = icono.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
                    JLabel etiqueta = new JLabel(new ImageIcon(imagen));
                    productoSeleccionado.add(etiqueta, BorderLayout.CENTER);
                    
                    // Agregar etiqueta de nombre y precio
                    JLabel nombreLabel = new JLabel(nombre);
                    nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
                    
                    JLabel precioLabel = new JLabel("$" + String.format("%.2f", precio));
                    precioLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    precioLabel.setForeground(new Color(34, 139, 34));
                    precioLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
                    
                    JPanel infoPanel = new JPanel(new GridLayout(2, 1));
                    infoPanel.setBackground(new Color(240, 255, 240));
                    infoPanel.add(nombreLabel);
                    infoPanel.add(precioLabel);
                    
                    productoSeleccionado.add(infoPanel, BorderLayout.SOUTH);
                    
                    productoSeleccionado.revalidate();
                    productoSeleccionado.repaint();
                }
                
                JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Consulta base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private void eliminarProducto(ProductoCompleto producto, JPanel panel) {
        int confirmacion = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de que desea eliminar este producto?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);
                
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Eliminar de la lista de productos
                listaProductos.remove(producto);
                
                // Eliminar el panel del producto
                panelProductos.remove(panel);
                panelProductos.revalidate();
                panelProductos.repaint();
                
                // Resetear las referencias actuales
                productoActual = null;
                productoSeleccionado = null;
                
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Consulta base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private void visualizarProducto(ProductoCompleto producto) {
        try {
            JFrame ventanaDetalle = new JFrame("Detalle del Producto");
            ventanaDetalle.setSize(500, 300);
            ventanaDetalle.setLocationRelativeTo(null);
            
            Color colorFondo = new Color(88, 167, 114);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(10, 10));
            panel.setBackground(colorFondo);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JPanel mainPanel = new JPanel(new BorderLayout(15, 0));
            mainPanel.setBackground(colorFondo);
            
            // Panel para la imagen (izquierda)
            JPanel panelImagen = new JPanel();
            panelImagen.setBackground(Color.WHITE); 
            panelImagen.setPreferredSize(new Dimension(180, 180));
            panelImagen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            panelImagen.setLayout(new BorderLayout());
            
            ImageIcon icono = new ImageIcon(new URL(producto.url));
            Image imagen = icono.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            JLabel etiquetaImagen = new JLabel(" ");
            etiquetaImagen.setIcon(new ImageIcon(imagen));
            etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
            etiquetaImagen.setVerticalAlignment(SwingConstants.CENTER);
            etiquetaImagen.setHorizontalTextPosition(SwingConstants.CENTER);
            etiquetaImagen.setVerticalTextPosition(SwingConstants.BOTTOM);
            panelImagen.add(etiquetaImagen, BorderLayout.CENTER);
            
            // Panel para la información del producto (derecha)
            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new GridLayout(4, 1, 0, 5));
            panelInfo.setBackground(colorFondo);
            
            JLabel nombreLabel = new JLabel(producto.nombre);
            nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            nombreLabel.setForeground(Color.BLACK);
            
            JLabel tipoLabel = new JLabel("Tipo: " + producto.tipo);
            tipoLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            
            JLabel descLabel = new JLabel("<html><div style='width:200px'>" + producto.descripcion + "</div></html>");
            descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            
            JLabel precioLabel = new JLabel("Precio: $" + String.format("%.2f", producto.precio));
            precioLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            precioLabel.setForeground(new Color(34, 139, 34));
            
            panelInfo.add(nombreLabel);
            panelInfo.add(tipoLabel);
            panelInfo.add(descLabel);
            panelInfo.add(precioLabel);
            
            mainPanel.add(panelImagen, BorderLayout.WEST);
            mainPanel.add(panelInfo, BorderLayout.CENTER);
            
            panel.add(mainPanel, BorderLayout.CENTER);
            
            JButton cerrarBtn = new JButton("Cerrar");
            cerrarBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ventanaDetalle.dispose();
                }
            });
            
            JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelBoton.setBackground(colorFondo);
            panelBoton.add(cerrarBtn);
            
            panel.add(panelBoton, BorderLayout.SOUTH);
            
            ventanaDetalle.add(panel);
            ventanaDetalle.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al visualizar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private JPanel agregarProductoAlPanel(String urlImagen, String nombre, double precio) {
        try {
            JPanel panel = crearPanelProducto(urlImagen);
            
            // Agregar etiqueta de nombre y precio
            JLabel nombreLabel = new JLabel(nombre);
            nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
            
            JLabel precioLabel = new JLabel("$" + String.format("%.2f", precio));
            precioLabel.setHorizontalAlignment(SwingConstants.CENTER);
            precioLabel.setForeground(new Color(34, 139, 34));
            precioLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            
            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.setBackground(new Color(240, 255, 240));
            infoPanel.add(nombreLabel);
            infoPanel.add(precioLabel);
            
            panel.add(infoPanel, BorderLayout.SOUTH);
            
            panelProductos.add(panel);
            panelProductos.revalidate();
            panelProductos.repaint();
            
            return panel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void agregarProductoExistente(String urlImagen) {
        try {
            JPanel panel = crearPanelProducto(urlImagen);
            
            // Para productos existentes, crear un ProductoCompleto genérico ya que no tenemos los datos completos
            String idGenerico = "P" + (listaProductos.size() + 1);
            String nombreGenerico = "Producto " + (listaProductos.size() + 1);
            ProductoCompleto producto = new ProductoCompleto(
                idGenerico, 
                nombreGenerico, 
                "General", 
                "Descripción genérica del producto.", 
                0.0, 
                urlImagen, 
                0
            );
            
            listaProductos.add(producto);
            asociarProductoConPanel(panel, producto);
            
            panelProductos.add(panel);
            panelProductos.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void asociarProductoConPanel(JPanel panel, ProductoCompleto producto) {
        // Remover listeners existentes
        MouseListener[] listeners = panel.getMouseListeners();
        for (MouseListener listener : listeners) {
            panel.removeMouseListener(listener);
        }
        
        // Agregar nuevo listener
        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                panel.setBackground(new Color(255, 255, 204));
            }

            public void mouseExited(MouseEvent e) {
                panel.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 0), 2));
                panel.setBackground(new Color(240, 255, 240));
            }

            public void mouseClicked(MouseEvent e) {
                // Guardar referencia al panel y producto seleccionados
                productoSeleccionado = panel;
                productoActual = producto;
                
                if (SwingUtilities.isLeftMouseButton(e)) {
                    String cantidadStr = JOptionPane.showInputDialog(null, "¿Cuántas unidades deseas agregar?", "Agregar al carrito", JOptionPane.PLAIN_MESSAGE);
                    try {
                        int cantidad = Integer.parseInt(cantidadStr);
                        carrito.add(new Producto(producto.url, cantidad));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Cantidad inválida.");
                    }
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // Guardar referencia al panel y producto seleccionados
                productoSeleccionado = panel;
                productoActual = producto;
                
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // Guardar referencia al panel y producto seleccionados
                productoSeleccionado = panel;
                productoActual = producto;
                
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    
    private JPanel crearPanelProducto(String urlImagen) throws Exception {
        ImageIcon icono = new ImageIcon(new URL(urlImagen));
        Image imagen = icono.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel etiqueta = new JLabel(new ImageIcon(imagen));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 255, 240));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 0), 2));
        panel.add(etiqueta, BorderLayout.CENTER);
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return panel;
    }

    private void mostrarCarrito() {
        JFrame ventanaCarrito = new JFrame("Carrito de Compras");
        ventanaCarrito.setSize(400, 500);
        ventanaCarrito.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 255, 240));

        for (Producto p : carrito) {
            try {
                ImageIcon icono = new ImageIcon(new URL(p.url));
                Image imagen = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(imagen));
                JLabel cantidad = new JLabel("Cantidad: " + p.cantidad);
                cantidad.setFont(new Font("SansSerif", Font.PLAIN, 14));
                cantidad.setForeground(new Color(34, 139, 34));

                JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT));
                item.setBackground(new Color(255, 255, 255));
                item.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 100), 1));
                item.add(label);
                item.add(cantidad);
                panel.add(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JScrollPane scroll = new JScrollPane(panel);
        ventanaCarrito.add(scroll);
        ventanaCarrito.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

   public static void main(String[] args) {
        SwingUtilities.invokeLater(Productos::new);
    }
   
   class Producto {
        String url;
        int cantidad;

        public Producto(String url, int cantidad) {
            this.url = url;
            this.cantidad = cantidad;
        }
    }
   
   class ProductoCompleto extends Producto {
        String id;
        String nombre;
        String tipo;
        String descripcion;
        double precio;
        
        public ProductoCompleto(String id, String nombre, String tipo, String descripcion, double precio, String url, int cantidad) {
            super(url, cantidad);
            this.id = id;
            this.nombre = nombre;
            this.tipo = tipo;
            this.descripcion = descripcion;
            this.precio = precio;
        }
    }                                 
}