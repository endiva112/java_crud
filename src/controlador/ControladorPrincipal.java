package controlador;

import modelo.*;
import vista.*;
import javax.swing.*;

/**
 * Controlador único que gestiona toda la lógica y navegación
 */
public class ControladorPrincipal {
    // Modelo
    private GestorDatos gestorDatos;
    private Usuario usuarioActual;

    // Vista
    private VentanaPrincipal ventana;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private HomePanel homePanel;
    private AccountPanel accountPanel;
    private MyListPanel myListPanel;
    private ProductPanel productPanel;

    public ControladorPrincipal() {
        // 1. Inicializar modelo
        gestorDatos = new GestorDatos();
        usuarioActual = null;

        // 2. Inicializar ventana
        ventana = new VentanaPrincipal();

        // 3. Inicializar paneles (todos reciben this)
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        homePanel = new HomePanel(this);
        accountPanel = new AccountPanel(this);
        myListPanel = new MyListPanel(this);
        productPanel = new ProductPanel(this);

        // 4. Iniciar aplicación
        iniciarAplicacion();
    }

    private void iniciarAplicacion() {
        mostrarLogin();
        ventana.setVisible(true);
    }

    // ==================== NAVEGACIÓN ====================

    public void mostrarLogin() {
        ventana.mostrarPanel(loginPanel);
        loginPanel.limpiarCampos();
    }

    public void mostrarRegister() {
        ventana.mostrarPanel(registerPanel);
        registerPanel.limpiarCampos();
    }

    public void mostrarHome() {
        if (usuarioActual == null) {
            mostrarLogin();
            return;
        }
        homePanel.cargarJuegos(gestorDatos.getCatalogoJuegos());
        ventana.mostrarPanel(homePanel);
    }

    public void mostrarAccount() {
        if (usuarioActual == null) {
            mostrarLogin();
            return;
        }
        accountPanel.cargarDatosUsuario(usuarioActual);
        ventana.mostrarPanel(accountPanel);
    }

    public void mostrarMyList() {
        if (usuarioActual == null) {
            mostrarLogin();
            return;
        }
        myListPanel.cargarLista(usuarioActual.getMiListaDeJuegos());
        ventana.mostrarPanel(myListPanel);
    }

    public void mostrarProduct(Juego juego) {
        if (usuarioActual == null) {
            mostrarLogin();
            return;
        }
        boolean enLista = usuarioActual.tieneJuegoEnLista(juego);
        productPanel.cargarProducto(juego, enLista);
        ventana.mostrarPanel(productPanel);
    }

    // ==================== LÓGICA DE LOGIN ====================

    public void intentarLogin(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Por favor, completa todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = gestorDatos.buscarUsuario(email, password);
        if (usuario != null) {
            usuarioActual = usuario;
            mostrarHome();
        } else {
            JOptionPane.showMessageDialog(ventana,
                    "Email o contraseña incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ==================== LÓGICA DE REGISTRO ====================

    public void intentarRegistro(String email, String password, boolean acceptTerms) {
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Por favor, completa todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!acceptTerms) {
            JOptionPane.showMessageDialog(ventana,
                    "Debes aceptar los términos y condiciones",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (gestorDatos.existeUsuario(email)) {
            JOptionPane.showMessageDialog(ventana,
                    "Ya existe un usuario con ese email",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(email, password);
        gestorDatos.registrarUsuario(nuevoUsuario);
        usuarioActual = nuevoUsuario;

        JOptionPane.showMessageDialog(ventana,
                "¡Registro exitoso!",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        mostrarHome();
    }

    // ==================== LÓGICA DE CUENTA ====================

    public void actualizarCuenta(String nuevoEmail, String nuevaPassword, String avatar) {
        if (usuarioActual == null) return;

        if (!nuevoEmail.isEmpty()) {
            usuarioActual.setEmail(nuevoEmail);
        }

        if (!nuevaPassword.isEmpty()) {
            usuarioActual.setPassword(nuevaPassword);
        }

        if (avatar != null) {
            usuarioActual.setAvatar(avatar);
        }

        gestorDatos.actualizarUsuario(usuarioActual);

        JOptionPane.showMessageDialog(ventana,
                "Datos actualizados correctamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cerrarSesion() {
        usuarioActual = null;
        mostrarLogin();
    }

    // ==================== LÓGICA DE LISTA ====================

    public void toggleJuegoEnLista(Juego juego) {
        if (usuarioActual == null) return;

        if (usuarioActual.tieneJuegoEnLista(juego)) {
            usuarioActual.eliminarJuegoDeLista(juego);
        } else {
            usuarioActual.agregarJuegoALista(juego);
        }

        gestorDatos.actualizarUsuario(usuarioActual);
    }

    public void eliminarJuegoDeLista(int index) {
        if (usuarioActual == null) return;

        usuarioActual.eliminarJuegoDeLista(index);
        gestorDatos.actualizarUsuario(usuarioActual);

        // Recargar la vista
        mostrarMyList();
    }
}