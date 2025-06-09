# ProyectoBD/ Sistema de Gestión - Abarrotes La Soledad 
## 📋 Descripción

Sistema de gestión integral para la tienda "Abarrotes La Soledad" desarrollado en Java con interfaz gráfica Swing con conexión a base de datos MySQL. Permite la administración completa de productos, usuarios, proveedores y ventas, además de ofrecer una experiencia de compra intuitiva para los clientes.  

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
