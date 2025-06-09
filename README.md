# ProyectoBD/ Sistema de Gestión - Abarrotes La Soledad 

EQUIPO 8
## 📋 Descripción

Sistema de gestión integral para la tienda "Abarrotes La Soledad" desarrollado en Java con interfaz gráfica Swing con conexión a base de datos MySQL. Permite la administración completa de productos, usuarios, proveedores y ventas, además de ofrecer una experiencia de compra intuitiva para los clientes.  

Al descargar el proyecto también debes descargar todos los archivos zip de las imágenes, debes de extraer todo y en el proyecto en el paquete de entrada crear un nuevo folder donde vas a colocar todas las imágenes para un correcto funcionamiento del proyecto.

---
## ✨ Características Principales
### 🔐 Sistema de Autenticación

- **Login dual:** Acceso diferenciado para administradores y clientes
- **Encriptación de contraseñas:** Seguridad en el almacenamiento de credenciales
- **Validación de campos:** Verificación de formato de correo y campos obligatorios
---
### 👨‍💼 Panel de Administrador
El AdminFrame proporciona acceso a todas las funcionalidades administrativas:

#### 📦 Gestión de Productos

- Administración completa del inventario
- Control de stock en tiempo real
- Categorización de productos
- Gestión de precios y descripciones

#### 👥 Gestión de Usuarios

- Registro de nuevos usuarios (clientes y administradores)
- Administración de perfiles de usuario
- Control de accesos y permisos

#### 🏷️ Gestión de Proveedores

- Registro de nuevos proveedores
- Administración de información de contacto
- Gestión de relaciones comerciales

#### 🛒 Supervisión de Ventas

- Acceso al sistema de ventas para supervisión
- Monitoreo de transacciones
- Generación de reportes
  
### 🛍️ Interfaz de Cliente
El ClienteFrame ofrece una experiencia de compra completa:

#### 🔍 Catálogo de Productos

- Visualización atractiva: Tarjetas de producto con imágenes
- Búsqueda en tiempo real: Filtrado instantáneo por nombre
- Información detallada: Precios, stock disponible
- Estado de disponibilidad: Indicadores visuales de stock

#### 🛒 Carrito de Compras

- Gestión dinámica: Agregar, eliminar y modificar cantidades
- Validación de stock: Verificación automática de disponibilidad
- Cálculo automático: Subtotales y total general en tiempo real
- Interfaz intuitiva: Tabla editable para gestión de productos

#### 💳 Sistema de Checkout

- Múltiples formas de pago: Efectivo, tarjeta, transferencia
- Generación de tickets: Comprobantes detallados de compra
- Envío por correo: Tickets en formato PDF
- Actualización automática: Descuento de stock post-venta

---
## 🗄️ Base de Datos

MySQL: Sistema de gestión de base de datos
Tablas principales:

- trabajadores: Información de usuarios trabajadores
- administrador: Datos de administradores
- productos: Catálogo de productos
- proveedores: Información de proveedores

--- 
## 🎨 Diseño de Interfaz
### 🎯 Características de UX/UI

- Diseño intuitivo: Interfaces fáciles de usar
- Colores temáticos: Esquema verde que refleja la identidad de la marca
- Iconografía clara: Emojis y símbolos para mejor comprensión
- Responsive: Adaptación a diferentes tamaños de pantalla
- Feedback visual: Indicadores de estado y confirmaciones

### 🖼️ Elementos Visuales

- Tarjetas de producto: Presentación atractiva con imágenes
- Botones temáticos: Diseño consistente con colores diferenciados
- Iconos descriptivos: 📦 Productos, 👥 Usuarios, 🏷️ Proveedores, 🛒 Ventas
---
## 🔧 Funcionalidades Técnicas
### 📧 Sistema de Correo

- Generación automática de PDF: Tickets de compra profesionales
- Envío por email: Comprobantes digitales a los clientes
- Validación de correos: Verificación de formato antes del envío

### 💾 Gestión de Datos

- Conexión persistente: Manejo eficiente de conexiones MySQL
- Validación de stock: Verificación en tiempo real
- Transacciones seguras: Integridad de datos en las operaciones

### 🔒 Seguridad

- Encriptación: Contraseñas protegidas en base de datos
- Validación de sesión: Control de acceso por roles
- Manejo de errores: Gestión robusta de excepciones
---

## Módulos Principales
--- 
### 1. FormAgregarProveedor.java
Funcionalidad: Formulario para el registro de nuevos proveedores en el sistema.
#### Características:

- Interfaz gráfica simple con campos para ID y nombre del proveedor
- Validación de datos antes del registro
- Conexión directa con base de datos MySQL
- Confirmación de registro exitoso mediante mensaje emergente
- Diseño visual con colores personalizados y fuentes legibles

#### Campos del formulario:

- ID Proveedor (campo de texto)
- Nombre del Proveedor (campo de texto)
- Botón "Agregar" para confirmar el registro

### 2. FrameProductos.java
Funcionalidad: Sistema completo de gestión de productos (CRUD - Crear, Leer, Actualizar, Eliminar).
#### Características principales:

- Visualización: Tabla interactiva que muestra todos los productos registrados
- Búsqueda en tiempo real: Campo de búsqueda que filtra productos por nombre
- Formulario de gestión: Panel lateral con todos los campos necesarios para productos

#### Operaciones CRUD completas:

- Agregar nuevos productos
- Actualizar productos existentes
- Eliminar productos seleccionados
- Visualizar listado completo

---

#### Campos de producto:

- Nombre del producto
- Tipo (categoría seleccionable): Abarrotes Básicos, Pan y Galletas, Bebidas, Dulcería y Snacks, Limpieza e Higiene, Productos Perecederos, Bebés y Maternidad, Mascotas, Otros/Farmacia básica
- Precio
- URL de imagen
- Stock disponible
- ID del proveedor
--- 
#### Funciones adicionales:

- Confirmación de acciones mediante diálogos
- Limpieza automática de formularios después de operaciones
- Selección de productos desde la tabla para edición
- Botón regresar para navegación

### 3. Registro.java
Funcionalidad: Sistema completo de registro de usuarios con diferentes roles y envío automático de correos de bienvenida.
#### Características principales:

- Registro multi-rol: Permite registrar usuarios como Administrador, Gerente, Supervisor o Cajero
- Validación completa de datos: Verifica formato de correo, fortaleza de contraseña, coincidencia de contraseñas
- Seguridad: Encriptación de contraseñas antes del almacenamiento
- Generación automática de PDF: Crea documento de bienvenida personalizado
- Envío de correos: Sistema asíncrono de envío de correos electrónicos con PDF adjunto
- Interfaz visual atractiva: Diseño con iconos y colores personalizados

#### Campos del formulario:

- Correo electrónico (Gmail)
- Nombre completo (nombre, apellido paterno, apellido materno)
- Rol del usuario (ComboBox)
- Género (radio buttons: Masculino, Femenino, Otro)
- Fecha de nacimiento (selector de calendario)
- Contraseña y confirmación de contraseña
- Visualizador de contraseña (botón ojo)

#### Validaciones implementadas:

- Formato válido de correo electrónico
- Fortaleza de contraseña
- Coincidencia entre contraseña y confirmación
- Validación de nombres (solo letras)
- Verificación de fecha de nacimiento
- Verificación de usuario no duplicado

#### Funciones especiales:

- Encriptación de contraseñas
- Generación automática de PDF de bienvenida
- Envío asíncrono de correos electrónicos
- Limpieza automática de archivos temporales
- Confirmación de salida del formulario
--- 
## 📁 Archivos del Sistema
### 1. EnvioCorreo.java
Clase principal para el envío de correos electrónicos con funcionalidades avanzadas.
#### Funcionalidades:

- Envío de correos con PDFs adjuntos de forma síncrona y asíncrona
- Envío de tickets de compra por correo electrónico
- Pool de conexiones SMTP optimizado para mejor rendimiento
- Manejo de errores robusto con logging detallado
- Soporte para SSL/TLS con configuración segura
- Validaciones de destinatarios y archivos antes del envío
- Contenido HTML personalizado para diferentes tipos de correo

#### Métodos principales:
```bash
enviarCorreoConPDF() - Envío síncrono de correos con PDF
enviarCorreoAsync() - Envío asíncrono para mejor rendimiento
enviarTicketPorCorreo() - Envío específico de tickets de compra
shutdown() - Cierre ordenado del pool de hilos
```

### 2. GeneradorPDF.java
Generador de documentos PDF de bienvenida para nuevos usuarios.
#### Funcionalidades:

- Generación de PDFs personalizados para usuarios registrados
- Diseño profesional con fuentes y colores corporativos
- Información completa del usuario en formato tabla
- Mensaje de bienvenida personalizado con beneficios de la tienda
- Compresión automática para archivos más ligeros
- Manejo de directorios automático para rutas de salida

#### Contenido del PDF:

- Header con logo y título de la empresa
- Información personal del usuario (nombre, correo, edad, etc.)
- Mensaje de bienvenida personalizado
- Listado de beneficios y servicios
- Footer corporativo

### 3. GeneradorTicketPDF.java
Generador de tickets de compra en formato PDF.
#### Funcionalidades:

- Tickets de compra detallados con información completa de productos
- Tabla de productos con cantidad, precio y subtotales
- Cálculo automático de totales con formato de moneda
- Información de pago y fecha/hora de compra
- Diseño optimizado para impresión en formato ticket
- Integración con base de datos para obtener información de productos

#### Contenido del ticket:

- Header con información de la tienda y fecha
- Datos del cliente (si se proporciona)
- Tabla detallada de productos comprados
- Forma de pago utilizada
- Total de la compra destacado
- Mensaje de agradecimiento
- 
--- 
## 🔧 Configuración Técnica
--- 
### Dependencias Requeridas

- JavaMail API - Para envío de correos electrónicos
- iText PDF - Para generación de documentos PDF
- Java 8+ - Con soporte para CompletableFuture

### Configuración SMTP

- Servidor: smtp.gmail.com
- Puerto: 587 (STARTTLS)
- Autenticación: Requerida
- Protocolos SSL: TLSv1.2 y TLSv1.3
--- 
## 🚀 Casos de Uso
--- 
### Registro de Usuarios

- Se genera un PDF de bienvenida personalizado
- Se envía por correo electrónico al usuario registrado
- El PDF incluye información del usuario y beneficios de la tienda

### Procesamiento de Compras

- Se genera un ticket PDF con los detalles de la compra
- Se envía automáticamente al correo del cliente
- El ticket incluye productos, precios y total de la compra
---
## Libreria Utilizada/ConexionMySQL - Sistema de Gestión de Abarrotes
---
###📦 Conexión MySQL
Una librería Java que facilita la conexión a una base de datos MySQL, incluyendo funcionalidades para conectarse, crear/eliminar tablas y buscar registros de manera sencilla.

### 🎯 Propósito
El principal de esta librería es ayudar al usuario a implementar una base de datos, permitiendo una mejor integración con interfaces gráficas y el registro de datos, ya sea en medios locales o remotos.
link de la libreria original:
https://github.com/jpelon777/ConexionMySQL---Equipo17 
### 👤 Autor
- Jhonatan Iván Poblete Hernández 
- Omar Konk Pérez Ramírez
--- 
## Configuración de Base de Datos

- Base de datos: abarrotes_la_soledad
- Puerto: 3306
- Usuario: root
- Contraseña: 1234
- URL de conexión: jdbc:mysql://localhost:3306/abarrotes_la_soledad?useSSL=false&serverTimezone=UTC

### Funcionalidades
- Gestión de Productos
- Operaciones CRUD
--- 
- Crear tabla de productos: Crea automáticamente la tabla productos con campos: id_producto, nombre, precio, stock, id_proveedor, tipo, descripción, url
- Obtener productos: Recupera la lista completa de productos de la base de datos
- Insertar producto: Añade nuevos productos al inventario
- Buscar producto por ID: Localiza un producto específico usando su identificador
- Buscar producto por nombre: Localiza un producto usando su nombre exacto
- Eliminar producto: Remueve un producto del inventario

### Gestión de Inventario

- Verificar stock: Comprueba si hay suficiente stock para una cantidad solicitada
- Descontar stock: Reduce el stock después de una venta
- Actualizar stock: Modifica la cantidad disponible de un producto
- Obtener stock: Consulta la cantidad disponible de un producto específico

### Datos Iniciales

Cargar productos iniciales: Inserta 100 productos predefinidos con las siguientes categorías:

- Abarrotes Básicos
- Pan y Galletas
- Bebés y Maternidad
- Dulcería y Snacks
- Limpieza e Higiene
- Productos Perecederos
-Mascotas
- Bebidas
- Otros/Farmacia básica
---
## Gestión de Usuarios
### Operaciones de Usuario

- Crear tabla de usuarios: Establece la estructura para almacenar usuarios del sistema
- Insertar usuario: Registra nuevos usuarios con nombre, usuario, contraseña y rol
- Validar usuario: Autentica credenciales de acceso al sistema
- Insertar administrador: Registra usuarios con permisos administrativos
- Insertar cliente: Registra usuarios clientes con datos completos (nombre, apellidos, fecha de nacimiento, género)

### Gestión de Proveedores

- Insertar proveedor: Registra nuevos proveedores con ID y nombre

### Gestión de Ventas

- Registrar detalle de venta: Almacena los detalles de cada producto vendido incluyendo cantidad, precio unitario e importe total
--- 
### Utilidades de Base de Datos

- Buscar tabla completa: Muestra todos los registros de una tabla específica
- Buscar registro específico: Localiza registros por columna y valor específicos
---
### Categorías de Productos Disponibles

- Abarrotes Básicos - Productos fundamentales como arroz, azúcar, aceite
- Pan y Galletas - Productos de panadería y galletas
- Bebés y Maternidad - Productos para el cuidado infantil
- Dulcería y Snacks - Golosinas y botanas
- Limpieza e Higiene - Productos de aseo personal y del hogar
- Productos Perecederos - Lácteos, carnes y productos frescos
- Mascotas - Alimento y accesorios para animales
- Bebidas - Refrescos, jugos y bebidas diversas
- Otros/Farmacia básica - Medicamentos básicos y productos de salud

### Características Técnicas

- Manejo automático de conexiones con try-with-resources
- Prepared statements para prevenir inyección SQL
- Manejo de excepciones con logging de errores
- Transacciones automáticas para operaciones de venta
- Validación de stock antes de realizar ventas

### Notas de Seguridad

- Las contraseñas se almacenan en texto plano (recomendable implementar hash)
- Validación básica de usuarios sin roles diferenciados
- Conexión directa sin pool de conexiones

--- 
## Enlace al video de demostración

  https://youtu.be/8I4xctPFpkc

---

##  Requisitos mínimos

- Java 8 o superior.
- IDE como NetBeans 25, IntelliJ o Eclipse.
- Se requiere conexión a internet, librerías externas e base de datos.
- Conexión a base de datos MySQL, y su respectivo archivo en el proyecto

---

## Créditos:

Este proyecto fue desarrollado como parte de un ejercicio académico para la materia de Topicos Avanzados De Programacion, por el equipo 8, integrado por:

- Caballero Silva Dalia Montserrat
- Crespo Castañon Suyay Fernanda
- García Pérez César Miguel
