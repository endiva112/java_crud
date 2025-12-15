package vista;

import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JCheckBox acceptTermsCheckBox;

    public RegisterPanel(ControladorPrincipal controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Título
        JLabel titulo = new JLabel("REGISTRO");
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

        // Accept Terms
        acceptTermsCheckBox = new JCheckBox("Acepto términos y condiciones");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(acceptTermsCheckBox, gbc);

        // Botón Registrar
        JButton btnRegister = new JButton("Registrarse");
        btnRegister.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            boolean acceptTerms = acceptTermsCheckBox.isSelected();
            controlador.intentarRegistro(email, password, acceptTerms);
        });
        gbc.gridy = 4;
        add(btnRegister, gbc);

        // Botón Ya tengo cuenta
        JButton btnLogin = new JButton("Ya tengo cuenta");
        btnLogin.addActionListener(e -> controlador.mostrarLogin());
        gbc.gridy = 5;
        add(btnLogin, gbc);
    }

    public void limpiarCampos() {
        emailField.setText("");
        passwordField.setText("");
        acceptTermsCheckBox.setSelected(false);
    }
}