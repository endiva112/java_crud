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
    private Usuario currentUser;

    // Vista
    private VentanaPrincipal mainFrame;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private HomePanel homePanel;
    private AccountPanel accountPanel;
    private MyListPanel myListPanel;
    private ProductPanel productPanel;

    /**
     * Constructor, aquí se lanzan Modelo, Vista y se le pasa el Controlador a las vistas
     */
    public ControladorPrincipal() {
        // 1. Inicializar modelo
        gestorDatos = new GestorDatos();
        currentUser = null;

        // 2. Inicializar ventana
        mainFrame = new VentanaPrincipal();

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
        mainFrame.setVisible(true);
    }

    //region NAVEGACIÓN
    public void mostrarLogin() {
        mainFrame.mostrarPanel(loginPanel);
        loginPanel.limpiarCampos();
    }

    public void mostrarRegister() {
        mainFrame.mostrarPanel(registerPanel);
        registerPanel.limpiarCampos();
    }

    public void mostrarHome() {
        if (currentUser == null) {
            mostrarLogin();
            return;
        }
        homePanel.cargarJuegos(gestorDatos.getGamesList());
        mainFrame.mostrarPanel(homePanel);
    }

    public void mostrarAccount() {
        if (currentUser == null) {
            mostrarLogin();
            return;
        }
        accountPanel.cargarDatosUsuario(currentUser);
        mainFrame.mostrarPanel(accountPanel);
    }

    public void mostrarMyList() {
        if (currentUser == null) {
            mostrarLogin();
            return;
        }
        myListPanel.cargarLista(currentUser.getMyShoppingList());
        mainFrame.mostrarPanel(myListPanel);
    }

    public void mostrarProduct(Juego juego) {
        if (currentUser == null) {
            mostrarLogin();
            return;
        }
        boolean enLista = currentUser.tieneJuegoEnLista(juego);
        productPanel.cargarProducto(juego, enLista);
        mainFrame.mostrarPanel(productPanel);
    }
    //endregion

    //region LÓGICA DE LOGIN
    public void intentarLogin(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Por favor, completa todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = gestorDatos.buscarUsuario(email, password);
        if (usuario != null) {
            currentUser = usuario;
            mostrarHome();
        } else {
            JOptionPane.showMessageDialog(mainFrame,
                    "Email o contraseña incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //endregion

    //region LÓGICA DE REGISTRO
    public void intentarRegistro(String email, String password, boolean acceptTerms) {
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Por favor, completa todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!acceptTerms) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Debes aceptar los términos y condiciones",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (gestorDatos.existeUsuario(email)) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Ya existe un usuario con ese email",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(email, password);
        gestorDatos.registrarUsuario(nuevoUsuario);
        currentUser = nuevoUsuario;

        JOptionPane.showMessageDialog(mainFrame,
                "¡Registro exitoso!",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        mostrarHome();
    }
    //endregion

    //region LÓGICA DE CUENTA / SESIÓN
    public void actualizarCuenta(String nuevoEmail, String nuevaPassword, String avatar) {
        if (currentUser == null) return;

        if (!nuevoEmail.isEmpty()) {
            currentUser.setEmail(nuevoEmail);
        }

        if (!nuevaPassword.isEmpty()) {
            currentUser.setPassword(nuevaPassword);
        }

        if (avatar != null) {//TODO implementar, no voy a hacerlo por falta de tiempo y de ganas
            currentUser.setAvatar(avatar);
        }

        gestorDatos.actualizarUsuario(currentUser);

        JOptionPane.showMessageDialog(mainFrame,
                "Datos actualizados correctamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cerrarSesion() {
        currentUser = null;
        mostrarLogin();
    }
    //endregion

    //region LÓGICA DE LISTA
    public void toggleJuegoEnLista(Juego juego) {
        if (currentUser == null) return;

        if (currentUser.tieneJuegoEnLista(juego)) {
            currentUser.eliminarJuegoDeLista(juego);
        } else {
            currentUser.agregarJuegoALista(juego);
        }

        gestorDatos.actualizarUsuario(currentUser);
    }

    public void eliminarJuegoDeLista(int index) {
        if (currentUser == null) return;

        currentUser.eliminarJuegoDeLista(index);
        gestorDatos.actualizarUsuario(currentUser);

        // Recargar la vista
        mostrarMyList();
    }
    //endregion
}