package vista;

import controlador.ControladorPrincipal;
import modelo.Usuario;
import vista.componentes.Cabecera;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel avatarLabel;

    public AccountPanel(ControladorPrincipal controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Usar la NavBar reutilizable CON logout
        add(new Cabecera(controlador, true), BorderLayout.NORTH);

        // Panel central (datos)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Avatar por defecto
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        avatarLabel = new JLabel();
        avatarLabel.setPreferredSize(new Dimension(100, 100));
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ImageIcon icon = new ImageIcon("recursos/imagenes/userIcon.png");
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        avatarLabel.setIcon(new ImageIcon(img));
        centerPanel.add(avatarLabel, gbc);

        // TODO: Implementar funcionalidad para cambiar avatar (JFileChooser)

        // Email
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(new JLabel("Email:"), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        centerPanel.add(emailField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 3;
        centerPanel.add(new JLabel("Nueva Password:"), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        // Botón actualizar
        JButton btnActualizar = new JButton("Actualizar Datos");
        btnActualizar.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            controlador.actualizarCuenta(email, password, null);
        });
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        centerPanel.add(btnActualizar, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void cargarDatosUsuario(Usuario usuario) {
        emailField.setText(usuario.getEmail());
        passwordField.setText("");
    }
}