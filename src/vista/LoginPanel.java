package vista;

import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(ControladorPrincipal controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Título
        JLabel titulo = new JLabel("LOGIN");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titulo, gbc);

        // Email
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Botón Login
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            controlador.intentarLogin(email, password);
        });
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnLogin, gbc);

        // Botón Registrarse
        JButton btnRegister = new JButton("¿No tienes cuenta? Regístrate");
        btnRegister.addActionListener(e -> controlador.mostrarRegister());
        gbc.gridy = 4;
        add(btnRegister, gbc);
    }

    public void limpiarCampos() {
        emailField.setText("");
        passwordField.setText("");
    }
}